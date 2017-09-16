package o11n.foreman.model.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import o11n.foreman.configuration.ForemanServerConfiguration;
import o11n.foreman.configuration.IForemanServerConfigurationHolder;
import o11n.foreman.model.object.base.ForemanBaseObject;
import o11n.foreman.model.object.folder.Folder_ForemanServer_Host;
import o11n.foreman.rest.ForemanConnection;

public class ForemanServer extends ForemanBaseObject implements IForemanServerConfigurationHolder {
	public static final String FINDER_TYPE = "ForemanServer";
	private ForemanServerConfiguration config;
	private static final Logger log = LoggerFactory.getLogger(ForemanServer.class);
	private ForemanConnection connection;
	
	/* Folders */
	private Folder_ForemanServer_Host folder_ForemanServer_Host;
	
	public ForemanServer(IPluginNotificationHandler notificationHandler, ForemanServerConfiguration config, 
			String sessionUsername, String sessionPassword) {
		super(config.getId(), notificationHandler);
		this.config = config;
		this.connection = new ForemanConnection(config, sessionUsername, sessionPassword);
	
		folder_ForemanServer_Host = new Folder_ForemanServer_Host(this);
	}
	
	@Override
	public String getFinderType() {
		return FINDER_TYPE;
	}

	public ForemanServerConfiguration getConfig() {
		return config;
	}
	
	public void setConfig(ForemanServerConfiguration config) {
		this.config = config;
	}

	@Override
	public String getHost() {
		return config.getHost();
	}

	@Override
	public int getPort() {
		return config.getPort();
	}

	@Override
	public boolean getSsl() {
		return config.getSsl();
	}

	@Override
	public String getUsername() {
		return config.getUsername();
	}

	@Override
	public String getPassword() {
		return config.getPassword();
	}

	@Override
	public String getAttribute(String key) {
		return config.getAttribute(key);
	}

	@Override
	public boolean getSharedSession() {
		return config.getSharedSession();
	}

	@Override
	public String getName() {
		return config.getName();
	}
	
	public ForemanConnection getConnection() {
		return connection;
	}

	@Override
	public void invalidate() {
		folder_ForemanServer_Host.invalidate();
	}

	public Folder_ForemanServer_Host getFolder_ForemanServer_Host() {
		return folder_ForemanServer_Host;
	}
	
}
