package o11n.foreman.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import ch.dunes.vso.sdk.IServiceRegistry;
import ch.dunes.vso.sdk.endpoints.IEndpointConfiguration;
import ch.dunes.vso.sdk.endpoints.IEndpointConfigurationService;

public class ForemanConfiguraionPersister {
	private static Logger log = Logger.getLogger(ForemanConfiguraionPersister.class);
	private IEndpointConfigurationService endpointConfigurationService;
	 
	public void setRegistry(IServiceRegistry registry) {
		this.endpointConfigurationService = ((IEndpointConfigurationService)registry.getService("ch.dunes.vso.sdk.endpoints.IEndpointConfigurationService"));
	}
	
	public String create(ForemanServerConfiguration info) throws IOException {
		IEndpointConfiguration config = this.endpointConfigurationService.newEndpointConfiguration();
		info.setId(config.getId());
		toEndpointConfig(config, info);
		this.endpointConfigurationService.saveEndpointConfiguration(config);
		return config.getId();
	}
			  
	public String update(String cfgId, ForemanServerConfiguration info) throws IOException {
		IEndpointConfiguration config = this.endpointConfigurationService.getEndpointConfiguration(cfgId);
		if (config == null) {
			throw new IllegalArgumentException("Active Directory configuration with provided ID does not exists. Id " + cfgId + ".");
		}
		toEndpointConfig(config, info);
		this.endpointConfigurationService.saveEndpointConfiguration(config);
		return config.getId();
	}
			  
	public void delete(String id) throws IOException {
		this.endpointConfigurationService.deleteEndpointConfiguration(id);
	}
			  
	public Collection<ForemanServerConfiguration> findAll() throws IOException {
		Collection<IEndpointConfiguration> endPoints = this.endpointConfigurationService.getEndpointConfigurations();
		Collection<ForemanServerConfiguration> hostConfigurations = new ArrayList<ForemanServerConfiguration>(endPoints.size());
		for (IEndpointConfiguration endPoint : endPoints) {
			if (!"PluginOptions".equals(endPoint.getId())) {
				try {
					hostConfigurations.add(fromEndpointConfiguration(endPoint));
				} catch (Exception e) {
					log.error("Active Direcotry server configuration can not be loaded. Configuration id " + endPoint.getId(), e);
				}
			}
		}
		return hostConfigurations;
	}
			  
	public ForemanServerConfiguration find(String id) throws IOException {
		IEndpointConfiguration config = this.endpointConfigurationService.getEndpointConfiguration(id);
		return fromEndpointConfiguration(config);
	}
	
	private void toEndpointConfig(IEndpointConfiguration config, ForemanServerConfiguration info) {
		config.setString(ForemanServerConfiguration.NAME, info.getName());
		config.setString(ForemanServerConfiguration.HOST, info.getHost());
		config.setInt(ForemanServerConfiguration.PORT, info.getPort());
		config.setBoolean(ForemanServerConfiguration.SSL, info.getSsl());
		config.setString(ForemanServerConfiguration.USERNAME, info.getUsername());
		config.setPassword(ForemanServerConfiguration.PASSWORD, info.getPassword());
		config.setBoolean(ForemanServerConfiguration.SHARED_SESSION, info.getSharedSession());
	}
	  
	private ForemanServerConfiguration fromEndpointConfiguration(IEndpointConfiguration config) {
		ForemanServerConfiguration info = new ForemanServerConfiguration();
		
		info.setId(config.getId());
		info.setName(config.getString(ForemanServerConfiguration.NAME));
		info.setHost(config.getString(ForemanServerConfiguration.HOST));
		info.setPort(config.getAsInteger(ForemanServerConfiguration.PORT).intValue());
		info.setSsl(config.getAsBoolean(ForemanServerConfiguration.SSL).booleanValue());
		info.setUsername(config.getString(ForemanServerConfiguration.USERNAME));
		info.setPassword(config.getPassword(ForemanServerConfiguration.PASSWORD));
		info.setSharedSession(config.getAsBoolean(ForemanServerConfiguration.SHARED_SESSION).booleanValue());

		return info;
	}
	
	public String getVersion() {
		try {
			return this.endpointConfigurationService.getVersion();
		} catch (IOException e) {
			log.error("Unable to resovle configuration version", e);
		}
		return null;
	}
}
