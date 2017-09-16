package o11n.foreman.configuration;

public interface IForemanConfigurationListener {
	void onConfigurationChanged(ForemanServerConfiguration configuration);
	  
	void onConfigurationDeleted(String paramString);
	  
	void onConfigurationCreated(ForemanServerConfiguration configuration);
}
