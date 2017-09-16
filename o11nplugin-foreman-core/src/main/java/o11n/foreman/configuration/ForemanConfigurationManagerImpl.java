package o11n.foreman.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public class ForemanConfigurationManagerImpl implements IForemanConfigurationManager {
	private static ForemanConfigurationManagerImpl sInstance;
	private static final Object LOCK = new Object();
	private ForemanConfiguraionPersister persister;
	private IForemanConfigurationListener listener;
	private static Logger log = Logger.getLogger(ForemanConfigurationManagerImpl.class);
	
	private Map<String, ForemanServerConfiguration> serverConfigurations;
	
	private ForemanConfigurationManagerImpl() {
		serverConfigurations = new ConcurrentHashMap<String, ForemanServerConfiguration>();
	}
	
	public static ForemanConfigurationManagerImpl getInstance() {
		if(sInstance == null) {
			synchronized(LOCK) {
				if(sInstance == null) {
					sInstance = new ForemanConfigurationManagerImpl();
				}
			}
		}
		
		return sInstance;
	}
	@Override
	public ForemanServerConfiguration getConfiguration(String configurationId) {
		if (configurationId == null || !serverConfigurations.containsKey(configurationId)) {
			throw new IllegalArgumentException("Invalid configuration id. Provided configuration is null or doesn't exist.");
		}
		
		return serverConfigurations.get(configurationId);
	}

	@Override
	public Collection<ForemanServerConfiguration> getConfigurations() {
		return serverConfigurations.values();
	}

	@Override
	public synchronized void deleteConfiguration(String configurationId) {
		if (configurationId == null || !serverConfigurations.containsKey(configurationId)) {
			throw new IllegalArgumentException("Invalid configuration id. Provided configuration is null or doesn't exist.");
		}
		
		try {
			persister.delete(configurationId);
			serverConfigurations.remove(configurationId);
			sendConfigurationDeleted(configurationId);
		} catch (IOException e) {
			throw new RuntimeException("Could not delete Foreman server configuration with id " + configurationId, e);
		}
	}

	@Override
	public synchronized String persistConfiguration(ForemanServerConfiguration paramServerConfiguration) {
		try {
			String cfgId;
			if (paramServerConfiguration.getId() == null) {
				cfgId = this.persister.create(paramServerConfiguration);
				serverConfigurations.put(cfgId, paramServerConfiguration);
				sendConfigurationCreated(paramServerConfiguration);
			} else {
				cfgId = this.persister.update(paramServerConfiguration.getId(), paramServerConfiguration);
				serverConfigurations.put(cfgId, paramServerConfiguration);
				sendConfigurationChanged(paramServerConfiguration);
			}
			
			return cfgId;
		} catch (IOException e) {
			throw new RuntimeException("Foreman configuration can't be saved.", e);
		}
	}

	@Override
	public synchronized void reload() {
		serverConfigurations.clear();
		try {
			Collection<ForemanServerConfiguration> configs = persister.findAll();
			
			for (ForemanServerConfiguration cfg : configs) {
				serverConfigurations.put(cfg.getId(), cfg);
		    }
		} catch (IOException e) {
			log.error("Unable to reload configuration. " + e.getMessage(), e);
		}
	}

	@Override
	public ForemanGlobalSettings getPluginOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void setPluginOptions(ForemanGlobalSettings paramPluginOptions) {
		// TODO Auto-generated method stub

	}
	
	private void sendConfigurationCreated(ForemanServerConfiguration newCfg) {
		if (listener != null) {
			try {
				listener.onConfigurationCreated(newCfg);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
	  
	private void sendConfigurationDeleted(String cfgId) {
		if (listener != null) {
			try {
				listener.onConfigurationDeleted(cfgId);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
	  
	private void sendConfigurationChanged(ForemanServerConfiguration newCfg) {
		if (listener != null) {
			try {
				listener.onConfigurationChanged(newCfg);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	@Override
	public ForemanConfiguraionPersister getPersister() {
		return persister;
	}

	@Override
	public void setPersister(ForemanConfiguraionPersister persister) {
		this.persister = persister;
	}

	@Override
	public IForemanConfigurationListener getConfigurationListener() {
		return listener;
	}

	@Override
	public void setConfigurationListener(IForemanConfigurationListener listener) {
		this.listener = listener;
	}

}
