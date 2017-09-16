package o11n.foreman.configuration;

import java.util.HashMap;

public class ForemanServerConfiguration extends HashMap<String, Object> implements IForemanServerConfigurationHolder {
	private static final long serialVersionUID = -6607223023169211363L;
	public static final String NAME = "Name";
	public static final String HOST = "Host";
	public static final String PORT = "Port";
	public static final String SSL = "Ssl";
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	public static final String SHARED_SESSION = "SharedSession";
	
	private String id;
	
	public ForemanServerConfiguration() {}
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return (String) this.get(NAME);
	}

	public void setName(String name) {
		this.put(NAME, name);
	}

	@Override
	public String getHost() {
		return (String) this.get(HOST);
	}

	public void setHost(String host) {
		this.put(HOST, host);
	}

	@Override
	public int getPort() {
		return (Integer) this.get(PORT);
	}

	public void setPort(int port) {
		this.put(PORT, port);
	}

	@Override
	public boolean getSsl() {
		return (Boolean) this.get(SSL);
	}

	public void setSsl(boolean ssl) {
		this.put(SSL, ssl);
	}

	@Override
	public String getUsername() {
		return (String) this.get(USERNAME);
	}

	public void setUsername(String username) {
		this.put(USERNAME, username);
	}

	@Override
	public String getPassword() {
		return (String) this.get(PASSWORD);
	}

	public void setPassword(String password) {
		this.put(PASSWORD, password);
	}
	
	@Override
	public String getAttribute(String key) {
		return (String) this.get(key);
	}
	
	public void setAttribute(String key, String value) {
		this.put(key, value);
	}

	@Override
	public boolean getSharedSession() {
		return (Boolean) this.get(SHARED_SESSION);
	}
	
	public void setSharedSession(boolean sharedSession) {
		this.put(SHARED_SESSION, sharedSession);
	}
}
