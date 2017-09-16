package o11n.foreman.model.object;

import java.util.Arrays;
import java.util.HashMap;

import o11n.foreman.model.object.base.ForemanBaseObject;

public class Host extends ForemanBaseObject {
	public static final String FINDER_TYPE = "ForemanHost";
	private String name;
	private String ip;
    private String environment_id;
    private String environment_name;
    private String last_report;
    private String mac;
    private String realm_id;
    private String realm_name;
    private String sp_mac;
    private String sp_ip;
    private String sp_name;
    private String domain_id;
    private String domain_name;
    private String architecture_id;
    private String architecture_name;
    private String operatingsystem_id;
    private String operatingsystem_name;
    private String subnet_id;
    private String subnet_name;
    private String sp_subnet_id;
    private String ptable_id;
    private String ptable_name;
    private String medium_id;
    private String medium_name;
    private String build;
    private String comment;
    private String disk;
    private String installed_at;
    private String model_id;
    private String hostgroup_id;
    private String hostgroup_name;
    private String owner_id;
    private String owner_type;
    private String enabled;
    private String puppet_ca_proxy_id;
    private String managed;
    private String use_image;
    private String image_file;
    private String uuid;
    private String compute_resource_id;
    private String compute_resource_name;
    private String compute_profile_id;
    private String compute_profile_name;
    private String[] capabilities;
    private String provision_method;
    private String puppet_proxy_id;
    private String certname;
    private String image_id;
    private String image_name;
    private String created_at;
    private String updated_at;
    private String last_compile;
    private String global_status;
    private String global_status_label;
    private String puppet_status;
    private String model_name;
    private String build_status;
    private String build_status_label;
    private String progress_report_id;
    private String root_pass;
    private String location_id;
    private String location_name;
	private String organization_id;
    private String organization_name;
    
	private HashMap<String, Object>[] parameters;
    private HashMap<String, Object>[] interfaces;
    private HashMap<String, Object>[] puppetclasses;
    private HashMap<String, Object>[] config_groups;
    private HashMap<String, Object>[] all_parameters;
    private HashMap<String, Object>[] all_puppetclasses;
    private HashMap<String, Object> permissions;
    
    public Host() {
    	super();
    }
    
	public Host(String objectId, ForemanServer server) {
		super(objectId, server);
	}
	
	@Override
	public String getFinderType() {
		return FINDER_TYPE;
	}

	@Override
	public void invalidate() {
		// Does nothing
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEnvironment_id() {
		return environment_id;
	}

	public void setEnvironment_id(String environment_id) {
		this.environment_id = environment_id;
	}

	public String getEnvironment_name() {
		return environment_name;
	}

	public void setEnvironment_name(String environment_name) {
		this.environment_name = environment_name;
	}

	public String getLast_report() {
		return last_report;
	}

	public void setLast_report(String last_report) {
		this.last_report = last_report;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRealm_id() {
		return realm_id;
	}

	public void setRealm_id(String realm_id) {
		this.realm_id = realm_id;
	}

	public String getRealm_name() {
		return realm_name;
	}

	public void setRealm_name(String realm_name) {
		this.realm_name = realm_name;
	}

	public String getSp_mac() {
		return sp_mac;
	}

	public void setSp_mac(String sp_mac) {
		this.sp_mac = sp_mac;
	}

	public String getSp_ip() {
		return sp_ip;
	}

	public void setSp_ip(String sp_ip) {
		this.sp_ip = sp_ip;
	}

	public String getSp_name() {
		return sp_name;
	}

	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}

	public String getDomain_id() {
		return domain_id;
	}

	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getArchitecture_id() {
		return architecture_id;
	}

	public void setArchitecture_id(String architecture_id) {
		this.architecture_id = architecture_id;
	}

	public String getArchitecture_name() {
		return architecture_name;
	}

	public void setArchitecture_name(String architecture_name) {
		this.architecture_name = architecture_name;
	}

	public String getOperatingsystem_id() {
		return operatingsystem_id;
	}

	public void setOperatingsystem_id(String operatingsystem_id) {
		this.operatingsystem_id = operatingsystem_id;
	}

	public String getOperatingsystem_name() {
		return operatingsystem_name;
	}

	public void setOperatingsystem_name(String operatingsystem_name) {
		this.operatingsystem_name = operatingsystem_name;
	}

	public String getSubnet_id() {
		return subnet_id;
	}

	public void setSubnet_id(String subnet_id) {
		this.subnet_id = subnet_id;
	}

	public String getSubnet_name() {
		return subnet_name;
	}

	public void setSubnet_name(String subnet_name) {
		this.subnet_name = subnet_name;
	}

	public String getSp_subnet_id() {
		return sp_subnet_id;
	}

	public void setSp_subnet_id(String sp_subnet_id) {
		this.sp_subnet_id = sp_subnet_id;
	}

	public String getPtable_id() {
		return ptable_id;
	}

	public void setPtable_id(String ptable_id) {
		this.ptable_id = ptable_id;
	}

	public String getPtable_name() {
		return ptable_name;
	}

	public void setPtable_name(String ptable_name) {
		this.ptable_name = ptable_name;
	}

	public String getMedium_id() {
		return medium_id;
	}

	public void setMedium_id(String medium_id) {
		this.medium_id = medium_id;
	}

	public String getMedium_name() {
		return medium_name;
	}

	public void setMedium_name(String medium_name) {
		this.medium_name = medium_name;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getInstalled_at() {
		return installed_at;
	}

	public void setInstalled_at(String installed_at) {
		this.installed_at = installed_at;
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public String getHostgroup_id() {
		return hostgroup_id;
	}

	public void setHostgroup_id(String hostgroup_id) {
		this.hostgroup_id = hostgroup_id;
	}

	public String getHostgroup_name() {
		return hostgroup_name;
	}

	public void setHostgroup_name(String hostgroup_name) {
		this.hostgroup_name = hostgroup_name;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getOwner_type() {
		return owner_type;
	}

	public void setOwner_type(String owner_type) {
		this.owner_type = owner_type;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getPuppet_ca_proxy_id() {
		return puppet_ca_proxy_id;
	}

	public void setPuppet_ca_proxy_id(String puppet_ca_proxy_id) {
		this.puppet_ca_proxy_id = puppet_ca_proxy_id;
	}

	public String getManaged() {
		return managed;
	}

	public void setManaged(String managed) {
		this.managed = managed;
	}

	public String getUse_image() {
		return use_image;
	}

	public void setUse_image(String use_image) {
		this.use_image = use_image;
	}

	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCompute_resource_id() {
		return compute_resource_id;
	}

	public void setCompute_resource_id(String compute_resource_id) {
		this.compute_resource_id = compute_resource_id;
	}

	public String getCompute_resource_name() {
		return compute_resource_name;
	}

	public void setCompute_resource_name(String compute_resource_name) {
		this.compute_resource_name = compute_resource_name;
	}

	public String getCompute_profile_id() {
		return compute_profile_id;
	}

	public void setCompute_profile_id(String compute_profile_id) {
		this.compute_profile_id = compute_profile_id;
	}

	public String getCompute_profile_name() {
		return compute_profile_name;
	}

	public void setCompute_profile_name(String compute_profile_name) {
		this.compute_profile_name = compute_profile_name;
	}

	public String[] getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String[] capabilities) {
		this.capabilities = capabilities;
	}
	
	public String getCapabilitiesAsString() {
		return Arrays.toString(capabilities);
	}

	public String getProvision_method() {
		return provision_method;
	}

	public void setProvision_method(String provision_method) {
		this.provision_method = provision_method;
	}

	public String getPuppet_proxy_id() {
		return puppet_proxy_id;
	}

	public void setPuppet_proxy_id(String puppet_proxy_id) {
		this.puppet_proxy_id = puppet_proxy_id;
	}

	public String getCertname() {
		return certname;
	}

	public void setCertname(String certname) {
		this.certname = certname;
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getLast_compile() {
		return last_compile;
	}

	public void setLast_compile(String last_compile) {
		this.last_compile = last_compile;
	}

	public String getGlobal_status() {
		return global_status;
	}

	public void setGlobal_status(String global_status) {
		this.global_status = global_status;
	}

	public String getGlobal_status_label() {
		return global_status_label;
	}

	public void setGlobal_status_label(String global_status_label) {
		this.global_status_label = global_status_label;
	}

	public String getPuppet_status() {
		return puppet_status;
	}

	public void setPuppet_status(String puppet_status) {
		this.puppet_status = puppet_status;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getBuild_status() {
		return build_status;
	}

	public void setBuild_status(String build_status) {
		this.build_status = build_status;
	}

	public String getBuild_status_label() {
		return build_status_label;
	}

	public void setBuild_status_label(String build_status_label) {
		this.build_status_label = build_status_label;
	}

	public String getRoot_pass() {
		return root_pass;
	}

	public void setRoot_pass(String root_pass) {
		this.root_pass = root_pass;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public String getProgress_report_id() {
		return progress_report_id;
	}

	public void setProgress_report_id(String progress_report_id) {
		this.progress_report_id = progress_report_id;
	}
	
	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public HashMap<String, Object>[] getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, Object>[] parameters) {
		this.parameters = parameters;
	}
	
	public HashMap<String, Object>[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(HashMap<String, Object>[] interfaces) {
		this.interfaces = (HashMap<String, Object>[]) interfaces;
	}

	public HashMap<String, Object>[] getPuppetclasses() {
		return puppetclasses;
	}

	public void setPuppetclasses(HashMap<String, Object>[] puppetclasses) {
		this.puppetclasses = puppetclasses;
	}

	public HashMap<String, Object>[] getConfig_groups() {
		return config_groups;
	}

	public void setConfig_groups(HashMap<String, Object>[] config_groups) {
		this.config_groups = config_groups;
	}

	public HashMap<String, Object>[] getAll_parameters() {
		return all_parameters;
	}

	public void setAll_parameters(HashMap<String, Object>[] all_parameters) {
		this.all_parameters = all_parameters;
	}

	public HashMap<String, Object>[] getAll_puppetclasses() {
		return all_puppetclasses;
	}

	public void setAll_puppetclasses(HashMap<String, Object>[] all_puppetclasses) {
		this.all_puppetclasses = all_puppetclasses;
	}

	public HashMap<String, Object> getPermissions() {
		return permissions;
	}

	public void setPermissions(HashMap<String, Object> permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public String toString() {
		return "Host [name=" + name + ", ip=" + ip + ", environment_id=" + environment_id + ", environment_name="
				+ environment_name + ", last_report=" + last_report + ", mac=" + mac + ", realm_id=" + realm_id
				+ ", realm_name=" + realm_name + ", sp_mac=" + sp_mac + ", sp_ip=" + sp_ip + ", sp_name=" + sp_name
				+ ", domain_id=" + domain_id + ", domain_name=" + domain_name + ", architecture_id=" + architecture_id
				+ ", architecture_name=" + architecture_name + ", operatingsystem_id=" + operatingsystem_id
				+ ", operatingsystem_name=" + operatingsystem_name + ", subnet_id=" + subnet_id + ", subnet_name="
				+ subnet_name + ", sp_subnet_id=" + sp_subnet_id + ", ptable_id=" + ptable_id + ", ptable_name="
				+ ptable_name + ", medium_id=" + medium_id + ", medium_name=" + medium_name + ", build=" + build
				+ ", comment=" + comment + ", disk=" + disk + ", installed_at=" + installed_at + ", model_id="
				+ model_id + ", hostgroup_id=" + hostgroup_id + ", hostgroup_name=" + hostgroup_name + ", owner_id="
				+ owner_id + ", owner_type=" + owner_type + ", enabled=" + enabled + ", puppet_ca_proxy_id="
				+ puppet_ca_proxy_id + ", managed=" + managed + ", use_image=" + use_image + ", image_file="
				+ image_file + ", uuid=" + uuid + ", compute_resource_id=" + compute_resource_id
				+ ", compute_resource_name=" + compute_resource_name + ", compute_profile_id=" + compute_profile_id
				+ ", compute_profile_name=" + compute_profile_name + ", capabilities=" + Arrays.toString(capabilities)
				+ ", provision_method=" + provision_method + ", puppet_proxy_id=" + puppet_proxy_id + ", certname="
				+ certname + ", image_id=" + image_id + ", image_name=" + image_name + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", last_compile=" + last_compile + ", global_status=" + global_status
				+ ", global_status_label=" + global_status_label + ", puppet_status=" + puppet_status + ", model_name="
				+ model_name + ", build_status=" + build_status + ", build_status_label=" + build_status_label
				+ ", progress_report_id=" + progress_report_id + ", root_pass=" + root_pass + ", location_id="
				+ location_id + ", location_name=" + location_name + ", organization_id=" + organization_id
				+ ", organization_name=" + organization_name + ", parameters=" + Arrays.toString(parameters)
				+ ", interfaces=" + Arrays.toString(interfaces) + ", puppetclasses=" + Arrays.toString(puppetclasses)
				+ ", config_groups=" + Arrays.toString(config_groups) + ", all_parameters="
				+ Arrays.toString(all_parameters) + ", all_puppetclasses=" + Arrays.toString(all_puppetclasses)
				+ ", permissions=" + permissions + ", id=" + getId() + ", foremanId=" + getForemanId() + "]";
	}
}
