package o11n.foreman.configuration;

import java.util.Collection;

public interface IForemanConfigurationManager {
	ForemanServerConfiguration getConfiguration(String configurationId);
	Collection<ForemanServerConfiguration> getConfigurations();
	void deleteConfiguration(String configurationId);
	String persistConfiguration(ForemanServerConfiguration paramServerConfiguration);
	void reload();
	ForemanGlobalSettings getPluginOptions();
	void setPluginOptions(ForemanGlobalSettings paramPluginOptions);
	ForemanConfiguraionPersister getPersister();
	void setPersister(ForemanConfiguraionPersister persister);
	public IForemanConfigurationListener getConfigurationListener();
	public void setConfigurationListener(IForemanConfigurationListener listener);
}
