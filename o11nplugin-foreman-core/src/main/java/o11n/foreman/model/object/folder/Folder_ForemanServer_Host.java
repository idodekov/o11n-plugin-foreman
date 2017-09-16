package o11n.foreman.model.object.folder;

import java.util.HashMap;

import org.apache.log4j.Logger;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import o11n.foreman.model.object.ForemanServer;
import o11n.foreman.model.object.Host;
import o11n.foreman.model.object.base.ForemanBaseObject;
import o11n.foreman.model.object.base.ForemanFolder;
import o11n.foreman.model.object.iterator.HostIterator;
import o11n.foreman.rest.ForemanRestException;
import o11n.foreman.rest.RequestType;
import o11n.foreman.rest.ResourcePaths;
import o11n.foreman.rest.RestRequest;
import o11n.foreman.rest.RestResponse;
import o11n.foreman.util.BuilderUtils;

public class Folder_ForemanServer_Host extends ForemanFolder {
	private static final Logger log = Logger.getLogger(Folder_ForemanServer_Host.class);
	public static final String FINDER_TYPE = "Folder_ForemanServer_Host";
	public static final String FOLDER_NAME = "Hosts";

	public Folder_ForemanServer_Host(ForemanServer server) {
		super(server, null);
		setName(FOLDER_NAME);
	}
	
	@Override
	public String getFinderType() {
		return FINDER_TYPE;
	}
	
	@Override
	public ForemanBaseObject buildObject(JSONObject json) {
		if(json.getAsNumber("id") == null) {
			return null;
		}
		
		String foremanId = json.getAsString("id");
		Host host = new Host(foremanId, this.getForemanServer());
		host.setForemanId("" + foremanId);
		host.setName(json.getAsString("name"));
		host.setIp(json.getAsString("ip"));
	    host.setEnvironment_id(json.getAsString("environment_id"));
	    host.setEnvironment_name(json.getAsString("environment_name"));
	    host.setLast_report(json.getAsString("last_report"));
	    host.setMac(json.getAsString("mac"));
	    host.setRealm_id(json.getAsString("realm_id"));
	    host.setRealm_name(json.getAsString("realm_name"));
	    host.setSp_mac(json.getAsString("sp_mac"));
	    host.setSp_ip(json.getAsString("sp_ip"));
	    host.setSp_name(json.getAsString("sp_name"));
	    host.setDomain_id(json.getAsString("domain_id"));
	    host.setDomain_name(json.getAsString("domain_name"));
	    host.setArchitecture_id(json.getAsString("architecture_id"));
	    host.setArchitecture_name(json.getAsString("architecture_name"));
	    host.setOperatingsystem_id(json.getAsString("operatingsystem_id"));
	    host.setOperatingsystem_name(json.getAsString("operatingsystem_name"));
	    host.setSubnet_id(json.getAsString("subnet_id"));
	    host.setSubnet_name(json.getAsString("subnet_name"));
	    host.setSp_subnet_id(json.getAsString("sp_subnet_id"));
	    host.setPtable_id(json.getAsString("ptable_id"));
	    host.setPtable_name(json.getAsString("ptable_name"));
	    host.setMedium_id(json.getAsString("medium_id"));
	    host.setMedium_name(json.getAsString("medium_name"));
	    host.setBuild(json.getAsString("build"));
	    host.setComment(json.getAsString("comment"));
	    host.setDisk(json.getAsString("disk"));
	    host.setInstalled_at(json.getAsString("installed_at"));
	    host.setModel_id(json.getAsString("model_id"));
	    host.setHostgroup_id(json.getAsString("hostgroup_id"));
	    host.setHostgroup_name(json.getAsString("hostgroup_name"));
	    host.setOwner_id(json.getAsString("owner_id"));
	    host.setOwner_type(json.getAsString("owner_type"));
	    host.setEnabled(json.getAsString("enabled"));
	    host.setPuppet_ca_proxy_id(json.getAsString("puppet_ca_proxy_id"));
	    host.setManaged(json.getAsString("managed"));
	    host.setUse_image(json.getAsString("use_image"));
	    host.setImage_file(json.getAsString("image_file"));
	    host.setUuid(json.getAsString("uuid"));
	    host.setCompute_resource_id(json.getAsString("compute_resource_id"));
	    host.setCompute_resource_name(json.getAsString("compute_resource_name"));
	    host.setCompute_profile_id(json.getAsString("compute_profile_id"));
	    host.setCompute_profile_name(json.getAsString("compute_profile_name"));
	    host.setProvision_method(json.getAsString("provision_method"));
	    host.setPuppet_proxy_id(json.getAsString("puppet_proxy_id"));
	    host.setCertname(json.getAsString("certname"));
	    host.setImage_id(json.getAsString("image_id"));
	    host.setImage_name(json.getAsString("image_name"));
	    host.setCreated_at(json.getAsString("created_at"));
	    host.setUpdated_at(json.getAsString("updated_at"));
	    host.setLast_compile(json.getAsString("last_compile"));
	    host.setGlobal_status(json.getAsString("global_status"));
	    host.setGlobal_status_label(json.getAsString("global_status_label"));
	    host.setPuppet_status(json.getAsString("puppet_status"));
	    host.setModel_name(json.getAsString("model_name"));
	    host.setBuild_status(json.getAsString("build_status"));
	    host.setBuild_status_label(json.getAsString("build_status_label"));
	    host.setLocation_id(json.getAsString("location_id"));
	    host.setOrganization_id(json.getAsString("organization_id"));
	    host.setLocation_name(json.getAsString("location_name"));
	    host.setOrganization_name(json.getAsString("organization_name"));
	    
    	host.setCapabilities(BuilderUtils.buildArray(json.getAsString("capabilities")));
    	
    	host.setAll_parameters(BuilderUtils.buildPropertiesArray(json.getAsString("all_parameters")));
    	host.setInterfaces(BuilderUtils.buildPropertiesArray(json.getAsString("interfaces")));
    	host.setParameters(BuilderUtils.buildPropertiesArray(json.getAsString("parameters")));
    	host.setPuppetclasses(BuilderUtils.buildPropertiesArray(json.getAsString("puppetclasses")));
    	host.setConfig_groups(BuilderUtils.buildPropertiesArray(json.getAsString("config_groups")));
    	host.setAll_puppetclasses(BuilderUtils.buildPropertiesArray(json.getAsString("all_puppetclasses")));
    	host.setPermissions(BuilderUtils.buildProperties(json.getAsString("permissions")));
	    
		return host;
	}
	
	@Override
	public JSONObject buildJson(ForemanBaseObject object) {
		Host host = (Host) object;
		JSONObject json = JSONValue.parse("{}", JSONObject.class);
		JSONObject hostJson = JSONValue.parse("{}", JSONObject.class);
		
		BuilderUtils.setJsonAttribute(hostJson, "id", host.getForemanId());
		BuilderUtils.setJsonAttribute(hostJson, "name", host.getName());
		BuilderUtils.setJsonAttribute(hostJson, "ip", host.getIp());
	    BuilderUtils.setJsonAttribute(hostJson, "environment_id", host.getEnvironment_id());
	    BuilderUtils.setJsonAttribute(hostJson, "environment_name", host.getEnvironment_name());
	    BuilderUtils.setJsonAttribute(hostJson, "last_report", host.getLast_report());
	    BuilderUtils.setJsonAttribute(hostJson, "mac", host.getMac());
	    BuilderUtils.setJsonAttribute(hostJson, "realm_id", host.getRealm_id());
	    BuilderUtils.setJsonAttribute(hostJson, "realm_name", host.getRealm_name());
	    BuilderUtils.setJsonAttribute(hostJson, "sp_mac", host.getSp_mac());
	    BuilderUtils.setJsonAttribute(hostJson, "sp_ip", host.getSp_ip());
	    BuilderUtils.setJsonAttribute(hostJson, "sp_name", host.getSp_name());
	    BuilderUtils.setJsonAttribute(hostJson, "domain_id", host.getDomain_id());
	    BuilderUtils.setJsonAttribute(hostJson, "domain_name", host.getDomain_name());
	    BuilderUtils.setJsonAttribute(hostJson, "architecture_id", host.getArchitecture_id());
	    BuilderUtils.setJsonAttribute(hostJson, "architecture_name", host.getArchitecture_name());
	    BuilderUtils.setJsonAttribute(hostJson, "operatingsystem_id", host.getOperatingsystem_id());
	    BuilderUtils.setJsonAttribute(hostJson, "operatingsystem_name", host.getOperatingsystem_name());
	    BuilderUtils.setJsonAttribute(hostJson, "subnet_id", host.getSubnet_id());
	    BuilderUtils.setJsonAttribute(hostJson, "subnet_name", host.getSubnet_name());
	    BuilderUtils.setJsonAttribute(hostJson, "sp_subnet_id", host.getSp_subnet_id());
	    BuilderUtils.setJsonAttribute(hostJson, "ptable_id", host.getPtable_id());
	    BuilderUtils.setJsonAttribute(hostJson, "ptable_name", host.getPtable_name());
	    BuilderUtils.setJsonAttribute(hostJson, "medium_id", host.getMedium_id());
	    BuilderUtils.setJsonAttribute(hostJson, "medium_name", host.getMedium_name());
	    BuilderUtils.setJsonAttribute(hostJson, "build", host.getBuild());
	    BuilderUtils.setJsonAttribute(hostJson, "comment", host.getComment());
	    BuilderUtils.setJsonAttribute(hostJson, "disk", host.getDisk());
	    BuilderUtils.setJsonAttribute(hostJson, "installed_at", host.getInstalled_at());
	    BuilderUtils.setJsonAttribute(hostJson, "model_id", host.getModel_id());
	    BuilderUtils.setJsonAttribute(hostJson, "hostgroup_id", host.getHostgroup_id());
	    BuilderUtils.setJsonAttribute(hostJson, "hostgroup_name", host.getHostgroup_name());
	    BuilderUtils.setJsonAttribute(hostJson, "owner_id", host.getOwner_id());
	    BuilderUtils.setJsonAttribute(hostJson, "owner_type", host.getOwner_type());
	    BuilderUtils.setJsonAttribute(hostJson, "enabled", host.getEnabled());
	    BuilderUtils.setJsonAttribute(hostJson, "puppet_ca_proxy_id", host.getPuppet_ca_proxy_id());
	    BuilderUtils.setJsonAttribute(hostJson, "managed", host.getManaged());
	    BuilderUtils.setJsonAttribute(hostJson, "use_image", host.getUse_image());
	    BuilderUtils.setJsonAttribute(hostJson, "image_file", host.getImage_file());
	    BuilderUtils.setJsonAttribute(hostJson, "uuid", host.getUuid());
	    BuilderUtils.setJsonAttribute(hostJson, "compute_resource_id", host.getCompute_resource_id());
	    BuilderUtils.setJsonAttribute(hostJson, "compute_resource_name", host.getCompute_resource_name());
	    BuilderUtils.setJsonAttribute(hostJson, "compute_profile_id", host.getCompute_profile_id());
	    BuilderUtils.setJsonAttribute(hostJson, "compute_profile_name", host.getCompute_profile_name());
	    BuilderUtils.setJsonAttribute(hostJson, "provision_method", host.getProvision_method());
	    BuilderUtils.setJsonAttribute(hostJson, "puppet_proxy_id", host.getPuppet_proxy_id());
	    BuilderUtils.setJsonAttribute(hostJson, "certname", host.getCertname());
	    BuilderUtils.setJsonAttribute(hostJson, "image_id", host.getImage_id());
	    BuilderUtils.setJsonAttribute(hostJson, "image_name", host.getImage_name());
	    BuilderUtils.setJsonAttribute(hostJson, "created_at", host.getCreated_at());
	    BuilderUtils.setJsonAttribute(hostJson, "updated_at", host.getUpdated_at());
	    BuilderUtils.setJsonAttribute(hostJson, "last_compile", host.getLast_compile());
	    BuilderUtils.setJsonAttribute(hostJson, "global_status", host.getGlobal_status());
	    BuilderUtils.setJsonAttribute(hostJson, "global_status_label", host.getGlobal_status_label());
	    BuilderUtils.setJsonAttribute(hostJson, "puppet_status", host.getPuppet_status());
	    BuilderUtils.setJsonAttribute(hostJson, "model_name", host.getModel_name());
	    BuilderUtils.setJsonAttribute(hostJson, "build_status", host.getBuild_status());
	    BuilderUtils.setJsonAttribute(hostJson, "build_status_label", host.getBuild_status_label());
	    BuilderUtils.setJsonAttribute(hostJson, "location_id", host.getLocation_id());
	    BuilderUtils.setJsonAttribute(hostJson, "organization_id", host.getOrganization_id());
	    BuilderUtils.setJsonAttribute(hostJson, "location_name", host.getLocation_name());
	    BuilderUtils.setJsonAttribute(hostJson, "organization_name", host.getOrganization_name());
	    
	    BuilderUtils.setJsonPropertiesArray(hostJson, "interfaces_attributes", host.getInterfaces());
	    
	    json.put("host", hostJson);
	    
		return json;
	}
	
	@Override
	public String getResourcePathForIterator() {
		return ResourcePaths.HOSTS_ALL;
	}
	
	public HostIterator getAllHosts() {
		return new HostIterator(getForemanServer(), this, getNotificationHandler());
	}
	
	public HostIterator getHosts(String search, String sortBy, String sortOrder) {
		return new HostIterator(getForemanServer(), this, getNotificationHandler(), 
				search, sortBy, sortOrder);
	}
	
	public void deleteHostById(String id) {
		super.deleteById(id, 
				ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_BY_ID, new String[]{id}));
	}
	
	public Host getHostById(String id) {
		ForemanBaseObject object = super.getById(id, 
				ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_BY_ID, new String[]{id}));
		
		if(object != null) {
			return (Host) object;
		} else {
			return null;
		}
	}
	
	public Host updateHost(Host host) {
		host.setProvision_method(null);
		ForemanBaseObject object = super.updateObject(host, 
				ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_BY_ID, new String[]{host.getForemanId()}));
		
		if(object != null) {
			return (Host) object;
		} else {
			return null;
		}
	}
	
	public Host createHost(Host host) {
		ForemanBaseObject object = super.createObject(host, ResourcePaths.HOSTS_ALL);
		
		if(object != null) {
			return (Host) object;
		} else {
			return null;
		}
	}
	
	public HashMap<String, Object> getHostConfigurationStatus(String id) {
		String url = ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_GET_CONFIG_STATUS, new String[]{id});
		RestRequest request = new RestRequest(RequestType.GET, 
				getForemanServer().getConnection(), url);
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			JSONObject json = response.getJson();
			return BuilderUtils.buildProperties(json.toJSONString());
		} catch (ForemanRestException e) {
			log.error("An error has occured while getting host configuration status for host "
					+ "with by Id [" + id + "]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public HashMap<String, Object> getHostStatus(String id, String type) {
		if(!type.equals("global") && !type.equals("configuration") && !type.equals("build")) {
			throw new IllegalArgumentException("Type [" + type + 
					"] is incorrect. Must be one of the following: global, configuration, build.");
		}
		
		String url = ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_GET_STATUS, new String[]{id, type});
		RestRequest request = new RestRequest(RequestType.GET, 
				getForemanServer().getConnection(), url);
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			JSONObject json = response.getJson();
			return BuilderUtils.buildProperties(json.toJSONString());
		} catch (ForemanRestException e) {
			log.error("An error has occured while getting host status for host with Id [" + id + 
					"]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public HashMap<String, Object> getHostVmComputeAttributes(String id) {
		String url = ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_GET_VM_COMPUTE_ATTRIBUTES, new String[]{id});
		RestRequest request = new RestRequest(RequestType.GET, 
				getForemanServer().getConnection(), url);
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			JSONObject json = response.getJson();
			return BuilderUtils.buildProperties(json.toJSONString());
		} catch (ForemanRestException e) {
			log.error("An error has occured while getting vm compute attributes for host with Id [" + 
					id + "]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public void disassociateHost(String id) {
		String url = ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_DISASSOCIATE, new String[]{id});
		RestRequest request = new RestRequest(RequestType.PUT, 
				getForemanServer().getConnection(), url);
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
		} catch (ForemanRestException e) {
			log.error("An error has occured while dissassociating host with id [" + id + 
					"]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public HashMap<String, Object> runHostPowerOperation(String id, String powerAction) {
		String url = ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_POWER_ACTION, new String[]{id, powerAction});
		
		JSONObject requestJson = JSONValue.parse("{}", JSONObject.class);
		BuilderUtils.setJsonAttribute(requestJson, "power_action", powerAction);
		
		RestRequest request = new RestRequest(RequestType.PUT, 
				getForemanServer().getConnection(), url, requestJson.toJSONString());
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			JSONObject json = response.getJson();
			return BuilderUtils.buildProperties(json.toJSONString());
		} catch (ForemanRestException e) {
			log.error("An error has occured while running host power operation for host with Id [" + id + 
					"]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public void bootHostFromDevice(String id, String device) {
		String url = ResourcePaths.buildResourcePath(ResourcePaths.HOSTS_BOOT, new String[]{id});
		
		JSONObject requestJson = JSONValue.parse("{}", JSONObject.class);
		BuilderUtils.setJsonAttribute(requestJson, "device", device);
		
		RestRequest request = new RestRequest(RequestType.PUT, 
				getForemanServer().getConnection(), url, requestJson.toJSONString());
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
		} catch (ForemanRestException e) {
			log.error("An error has occured while booting from device for host with id [" + id + 
					"]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
}
