package o11n.foreman.model.object.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.minidev.json.JSONObject;
import o11n.foreman.model.object.ForemanServer;
import o11n.foreman.rest.ForemanRestException;
import o11n.foreman.rest.RequestType;
import o11n.foreman.rest.RestRequest;
import o11n.foreman.rest.RestResponse;

public abstract class ForemanFolder extends ForemanBaseObject 
		implements ICacheContainer, IObjectBuilder {
	private static final Logger log = Logger.getLogger(ForemanFolder.class);
	private Map<String, ForemanBaseObject> cachedChildren;
	private String name;
	
	private static final int MAX_CACHE_SIZE = 20;

	public ForemanFolder(ForemanServer server, String objectId) {
		super(objectId, server);
		cachedChildren = new HashMap<String, ForemanBaseObject>();
		reloadCache();
	}
	
	@Override
	public void invalidate() {
		for (Map.Entry<String, ForemanBaseObject> entry : cachedChildren.entrySet()) {
		    ForemanBaseObject object = entry.getValue();
		    object.invalidate();
		}
		
		cachedChildren.clear();
		reloadCache();
	}

	@Override
	public ForemanBaseObject getCachedChild(String id) {
		return cachedChildren.get(id);
	}
	
	@Override
	public void addCachedChild(ForemanBaseObject object) {
		cachedChildren.put(object.getId(), object);
	}

	@Override
	public List<ForemanBaseObject> getCachedChildren() {
		return new ArrayList<ForemanBaseObject>(cachedChildren.values());
	}

	@Override
	public void reloadCache() {
		int count = 0;

		ForemanIterator it = getAll();
		while(it.hasNext() && count < MAX_CACHE_SIZE) {
			addCachedChild(it.next());
			count++;
		}
	}
	
	public ForemanIterator getAll() {
		return new ForemanIterator(getForemanServer(), this, getNotificationHandler());
	}
	
	public ForemanIterator getAll(String search, String sortBy, String sortOrder) {
		return new ForemanIterator(getForemanServer(), this, getNotificationHandler(),
				search, sortBy, sortOrder);
	}
	
	public ForemanBaseObject getById(String id, String url) {
		RestRequest request = new RestRequest(RequestType.GET, 
				getForemanServer().getConnection(), url);
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			JSONObject json = response.getJson();
			return buildObject(json);
		} catch (ForemanRestException e) {
			log.error("An error has occured while getting object by Id [" + id + 
					"]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public void deleteById(String id, String url) {
		RestRequest request = new RestRequest(RequestType.DELETE, 
				getForemanServer().getConnection(), url);
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
		} catch (ForemanRestException e) {
			log.error("An error has occured while deleting object by Id [" + id + 
					"]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public ForemanBaseObject createObject(ForemanBaseObject object, String url) {
		JSONObject json = buildJson(object);
		
		RestRequest request = new RestRequest(RequestType.POST, 
				getForemanServer().getConnection(), url, json.toJSONString());
		
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			//notifyElementInvalidate();
			
			JSONObject responseJson = response.getJson();
			return buildObject(responseJson);
		} catch (ForemanRestException e) {
			log.error("An error has occured while creating object. REST url is [" + 
					url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public ForemanBaseObject updateObject(ForemanBaseObject object, String url) {
		if(object.getForemanId() == null) {
			throw new IllegalArgumentException("Can't update object with no associated Foreman ID.");
		}
		
		JSONObject json = buildJson(object);
		
		RestRequest request = new RestRequest(RequestType.PUT, 
				getForemanServer().getConnection(), url, json.toJSONString());
		
		try {
			RestResponse response = request.execute();
			if(response.getStatusCode() > 299 || response.getStatusCode() < 200) {
				throw new ForemanRestException("Received a non-200 response. " + response);
			}
			
			JSONObject responseJson = response.getJson();
			return buildObject(responseJson);
		} catch (ForemanRestException e) {
			log.error("An error has occured while updating object by Id [" + object.getForemanId() + 
					"]. REST url is [" + url + "]" + e.getMessage(), e);
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
