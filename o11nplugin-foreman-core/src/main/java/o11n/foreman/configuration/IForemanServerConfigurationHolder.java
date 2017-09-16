package o11n.foreman.configuration;

public interface IForemanServerConfigurationHolder {
	String getId();

	String getName();

	String getHost();

	int getPort();

	boolean getSsl();
	
	boolean getSharedSession();

	String getUsername();

	String getPassword();

	String getAttribute(String key);
}
