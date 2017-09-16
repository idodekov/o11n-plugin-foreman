package o11n.foreman.model.object.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import o11n.foreman.model.object.ForemanServer;
import o11n.foreman.rest.ForemanRestException;
import o11n.foreman.rest.RequestType;
import o11n.foreman.rest.RestRequest;
import o11n.foreman.rest.RestResponse;

public class ForemanIterator implements IForemanIterator {
	private static final Logger log = Logger.getLogger(ForemanIterator.class);
	private String iteratorId;
	
    private int remoteTotal;
    private int remoteSubtotal;
    private int remotePage;
    private int remotePerPage;
    
    private int localCurrent;
    private int localCacheCounter;
    
    private String search;
    private String sortBy;
    private String sortOrder;
    
	private ForemanServer server;
	private IObjectBuilder builder;
	private IPluginNotificationHandler notificationHandler;
	
	private List<ForemanBaseObject> cache;
	
	public static final String FINDER_TYPE = "Iterator_Base";
    
	public ForemanIterator(ForemanServer server, IObjectBuilder builder,
    		IPluginNotificationHandler notificationHandler) {
		this(server, builder, notificationHandler, null, null, null);
	}
	
    public ForemanIterator(ForemanServer server, IObjectBuilder builder,
    		IPluginNotificationHandler notificationHandler, String search, 
    		String sortBy, String sortOrder) {
    	remoteTotal = 0;
    	remoteSubtotal = 0;
    	remotePage = 0;
    	remotePerPage = 0;
    	localCurrent = 0;
    	localCacheCounter = 0;
    	
    	this.search = search;
    	this.sortBy = sortBy;
    	this.sortOrder = sortOrder;
    	
		this.server = server;
		this.notificationHandler = notificationHandler;
		this.builder = builder;
		
		generateId(search, sortBy, sortOrder);
		
		cache = new ArrayList<ForemanBaseObject>();
		getNextBatch();
		
		localCurrent = 0;
    	localCacheCounter = 0;
    }
    
    private void generateId(String search, String sortBy, String sortOrder) {
    	String searchParam = "";
    	String sortByParam = "";
    	String sortOrderParam = "";
    	
    	if(search != null) {
    		searchParam = search;
    	}
    	
    	if(sortBy != null) {
    		sortByParam = sortBy;
    	}
    	
    	if(sortOrder != null) {
    		sortOrderParam = sortOrder;
    	}
    	
    	int hashCode = getFinderType().hashCode();
		if(hashCode < 0) {
			hashCode = hashCode * -1;
		}
    	
    	iteratorId = server.getConfig().getId() + "#" + hashCode + "#0#" + 
    			searchParam + "#" + sortByParam + "#" + sortOrderParam;
    }
    
	@Override
	public boolean hasNext() {
		if(localCurrent < remoteSubtotal) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean hasNextInCache() {
		if(localCacheCounter < remotePerPage) {
			return true;
		} else {
			return false;
		}
	}
	
	private void getNextBatch() {
		cache.clear();
		localCacheCounter = 0;
		remotePage++;
		
		RestRequest request = new RestRequest(RequestType.GET, 
				server.getConnection(), 
				builder.getResourcePathForIterator() + 
				getUrlParameters());
		try {
			RestResponse response = request.execute();
			
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			JSONObject json = response.getJson();
			remoteTotal = json.getAsNumber("total").intValue();
	    	remoteSubtotal = json.getAsNumber("subtotal").intValue();
	    	remotePage = json.getAsNumber("page").intValue();
	    	remotePerPage = json.getAsNumber("per_page").intValue();
			
	    	String results = json.getAsString("results");
	    	JSONArray resultsArray = JSONValue.parse(results, JSONArray.class);
	    	Iterator<Object> it = resultsArray.iterator();
	    	while(it.hasNext()) {
	    		JSONObject object = (JSONObject) it.next();
	    		ForemanBaseObject foremanObject = builder.buildObject(object);
	    		cache.add(foremanObject);
	    	}
		} catch (Exception e) {
			log.error("An error has occured while obtaining next batch of objects from Foreman. " + 
					e.getMessage(), e);
		}
	}

	@Override
	public ForemanBaseObject next() {
		if(!hasNext()) {
			return null;
		}
		
		if(!hasNextInCache()) {
			getNextBatch();
		}
		
		ForemanBaseObject nextObject = cache.get(localCacheCounter);
		
		localCurrent++;
		localCacheCounter++;
		return nextObject;
	}
	
	private String getUrlParameters() {
		String result = "&page=" + remotePage;
		
		if(search != null) {
			result += "&search=" + getUrlEncodedSearch();
		}
		
		if(sortBy != null) {
			result += "&order=" + sortBy;
			
			if(sortOrder != null) {
				result += "+" + sortOrder;
			}
		}
		
		return result;
	}

	@Override
	public ForemanServer getServer() {
		return server;
	}

	@Override
	public IPluginNotificationHandler getNotificationHandler() {
		return notificationHandler;
	}

	@Override
	public String getSearch() {
		return search;
	}
	
	public String getUrlEncodedSearch() {
		try {
			return URLEncoder.encode(search, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Unable to encode search filter. " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public String getSortBy() {
		return sortBy;
	}

	@Override
	public String getSortOrder() {
		return sortOrder;
	}

	@Override
	public String getFinderType() {
		return FINDER_TYPE;
	}

	@Override
	public String getId() {
		return iteratorId;
	}
	
	@Override
	public int getObjectCount() {
		return remoteSubtotal;
	}
}
