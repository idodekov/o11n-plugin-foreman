package o11n.foreman;

import java.util.HashMap;
import java.util.Iterator;

import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;

import ch.dunes.vso.sdk.IServiceRegistry;
import ch.dunes.vso.sdk.IServiceRegistryAdaptor;
import ch.dunes.vso.sdk.api.IPluginAdaptor;
import ch.dunes.vso.sdk.api.IPluginEventPublisher;
import ch.dunes.vso.sdk.api.IPluginFactory;
import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import ch.dunes.vso.sdk.api.IPluginPublisher;
import ch.dunes.vso.sdk.api.PluginLicense;
import ch.dunes.vso.sdk.api.PluginLicenseException;
import ch.dunes.vso.sdk.api.PluginWatcher;
import o11n.foreman.configuration.ForemanConfiguraionPersister;
import o11n.foreman.configuration.ForemanConfigurationManagerImpl;
import o11n.foreman.configuration.ForemanServerConfiguration;
import o11n.foreman.configuration.IForemanConfigurationListener;
import o11n.foreman.configuration.IForemanConfigurationManager;

/**
 */
public class ForemanPluginAdaptor implements IPluginAdaptor, IServiceRegistryAdaptor, IForemanConfigurationListener {
	private static HashMap<String, ForemanPluginFactory> factories = new HashMap<String, ForemanPluginFactory>();
    private static final Logger log = Logger.getLogger(ForemanPluginAdaptor.class);
    private ForemanConfiguraionPersister persister = new ForemanConfiguraionPersister();
    private IForemanConfigurationManager configManager = ForemanConfigurationManagerImpl.getInstance();

    private String pluginName;
    
    private IPluginPublisher pluginPublisher;
    
    private static final Object LOCK = new Object();

    public IPluginFactory createPluginFactory(String sessionId, String username, String password, IPluginNotificationHandler pluginNotificationHandler) 
    		throws SecurityException, LoginException, PluginLicenseException {
        log.debug("createPluginFactory() --> sessionId: " + sessionId + ", username: " + username);
        
		if (factories.containsKey(sessionId)) {
			return (IPluginFactory)factories.get(sessionId);
		}
		try {
			ForemanPluginFactory factory = new ForemanPluginFactory(username, password, this.pluginName, sessionId, pluginNotificationHandler);
			factories.put(sessionId, factory);
			return factory;
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
    }

    @Override
    public void setPluginName(String pluginName) {
        log.debug("setPluginName() --> pluginName: " + pluginName);
        this.pluginName = pluginName;
    }

    @Override
    public void setPluginPublisher(IPluginPublisher pluginPublisher) {
        log.debug("setPluginPublisher()");
        this.pluginPublisher = pluginPublisher;
    }

    @Override
    public void addWatcher(PluginWatcher pluginWatcher) {
        log.debug("addWatcher() --> pluginWatcher: " + pluginWatcher.getId());
    }

    @Override
    public void removeWatcher(String pluginWatcherId) {
        log.debug("removeWatcher() --> pluginWatcherId: " + pluginWatcherId);
    }

    @Override
    public void registerEventPublisher(String type, String id, IPluginEventPublisher pluginEventPublisher) {
        log.debug("registerEventPublisher() --> type: " + type + ", id: " + id);
    }

    @Override
    public void unregisterEventPublisher(String type, String id, IPluginEventPublisher pluginEventPublisher) {
        log.debug("unregisterEventPublisher() --> type: " + type + ", id: " + id);
    }

    @Override
    public void installLicenses(PluginLicense[] licenses) throws PluginLicenseException {
        log.debug("installLicenses()");
    }

    @Override
    public void uninstallPluginFactory(IPluginFactory pluginFactory) {
        log.debug("uninstallPluginFactory()");
    }

	@Override
	public void setServiceRegistry(IServiceRegistry serviceRegistry) {
		persister.setRegistry(serviceRegistry);
		configManager.setPersister(persister);
	    
		configManager.reload();
		configManager.setConfigurationListener(this);
	}

	@Override
	public void onConfigurationChanged(ForemanServerConfiguration configuration) {
		synchronized(LOCK) {
			Iterator<ForemanPluginFactory> it = factories.values().iterator();
			while(it.hasNext()) {
				ForemanPluginFactory factory = it.next();
				factory.updateServer(configuration);
			}
		}
	}

	@Override
	public void onConfigurationDeleted(String paramString) {
		synchronized(LOCK) {
			Iterator<ForemanPluginFactory> it = factories.values().iterator();
			while(it.hasNext()) {
				ForemanPluginFactory factory = it.next();
				factory.deleteServer(paramString);
			}
		}
	}

	@Override
	public void onConfigurationCreated(ForemanServerConfiguration configuration) {
		synchronized(LOCK) {
			Iterator<ForemanPluginFactory> it = factories.values().iterator();
			while(it.hasNext()) {
				ForemanPluginFactory factory = it.next();
				factory.createServer(configuration);
			}
		}
	}
}
