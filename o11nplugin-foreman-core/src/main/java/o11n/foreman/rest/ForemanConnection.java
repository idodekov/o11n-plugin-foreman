package o11n.foreman.rest;

import o11n.foreman.configuration.ForemanServerConfiguration;

public class ForemanConnection {
	private String url;
	private String username;
	private String password;
	
	public ForemanConnection(ForemanServerConfiguration config, 
			String sessionUsername, String sessionPassword) {
		super();
		
		if(config.getSsl()) {
			url = "https://";
		} else {
			url = "http://";
		}
		
		url += config.getHost() + ":" + config.getPort();
		
		if(config.getSharedSession()) {
			this.username = config.getUsername();
			this.password = config.getPassword();
		} else {
			this.username = sessionUsername;
			this.password = sessionPassword;
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String buildRestUrl(String resourceLocation) {
		return getUrl() + resourceLocation;
	}
}
