package o11n.foreman.model;

import ch.dunes.vso.sdk.api.IPluginFactory;
import o11n.foreman.ForemanPluginFactory;
import o11n.foreman.configuration.ForemanConfigurationManagerImpl;
import o11n.foreman.configuration.ForemanGlobalSettings;
import o11n.foreman.configuration.ForemanServerConfiguration;
import o11n.foreman.configuration.IForemanConfigurationManager;

public class ServerManager {
	private IForemanConfigurationManager configManager = ForemanConfigurationManagerImpl.getInstance();
	private ForemanPluginFactory factory;
	
	public ServerManager(ForemanPluginFactory factory) {
		this.factory = factory;
	}
	
	public static ServerManager createScriptingSingleton(IPluginFactory factory) {
		return new ServerManager((ForemanPluginFactory)factory);
	}

	public ForemanServerConfiguration getConfiguration(String configurationId) {
		return configManager.getConfiguration(configurationId);
	}

	public void deleteConfiguration(String configurationId) {
		configManager.deleteConfiguration(configurationId);
	}

	public String persistConfiguration(ForemanServerConfiguration paramServerConfiguration) {
		return configManager.persistConfiguration(paramServerConfiguration);
	}

	public ForemanGlobalSettings getPluginOptions() {
		return configManager.getPluginOptions();
	}

	public void setPluginOptions(ForemanGlobalSettings paramPluginOptions) {
		configManager.setPluginOptions(paramPluginOptions);
	}
}
