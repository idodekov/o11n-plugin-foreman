package o11n.foreman.rest;

public class ResourcePaths {
	private ResourcePaths() {};
	
	public static final String API_PREFIX = "/api/v2";
	
	public static final String HOSTS_ALL = API_PREFIX + "/hosts?";
	public static final String HOSTS_BY_ID = API_PREFIX + "/hosts/{0}?";
	public static final String HOSTS_GET_CONFIG_STATUS = API_PREFIX + "/hosts/{0}/status?";
	public static final String HOSTS_GET_STATUS = API_PREFIX + "/hosts/{0}/status/{1}?";
	public static final String HOSTS_GET_VM_COMPUTE_ATTRIBUTES = API_PREFIX + "/hosts/{0}/vm_compute_attributes?";
	public static final String HOSTS_DISASSOCIATE = API_PREFIX + "/hosts/{0}/disassociate?";
	public static final String HOSTS_POWER_ACTION = API_PREFIX + "/hosts/{0}/power?";
	public static final String HOSTS_BOOT = API_PREFIX + "/hosts/{0}/boot?";

	
	public static String buildResourcePath(String path, String[] parameters) {
		String result = path;
		
		for(int i = 0; i < parameters.length ; i++) {
			result = result.replace("{" + i + "}", parameters[i]);
		}
		
		return result;
	}
}
