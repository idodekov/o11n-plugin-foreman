package o11n.foreman.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class BuilderUtils {
	private BuilderUtils() {}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object>[] buildPropertiesArray(String json) {
		if(json == null) {
			return null;
		}
			
		int counter = 0;
		JSONArray resultsArray = JSONValue.parse(json, JSONArray.class);
	    Iterator<Object> it = resultsArray.iterator();
	    HashMap<String, Object>[] propertiesList =(HashMap<String, Object>[]) new HashMap[resultsArray.size()];
	    while(it.hasNext()) {
	    	JSONObject propJson = (JSONObject) it.next();
	    	HashMap<String, Object> props = buildProperties(propJson.toJSONString());
	    		
	    	propertiesList[counter] = props;
	    	counter++;
	    }
	    	
	    return propertiesList;
	}
	
	public static HashMap<String, Object> buildProperties(String json) {
		if(json == null) {
			return null;
		}
		
		HashMap<String, Object> props = new HashMap<String, Object>();
		JSONObject propJson = JSONValue.parse(json, JSONObject.class);
		Set<String> keySet = propJson.keySet();
		Iterator<String> keysIt = keySet.iterator();
		while(keysIt.hasNext()) {
			String key = keysIt.next();
			Object value = propJson.get(key);
			
			if(value != null) { 
				props.put(key, value);
			}
		}
		
		return props;
	}
	
	public static String[] buildArray(String json) {
		JSONArray resultsArray = JSONValue.parse(json, JSONArray.class);
    	Iterator<Object> it = resultsArray.iterator();
    	ArrayList<String> list = new ArrayList<String>();
    	while(it.hasNext()) {
    		list.add((String) it.next());
    	}
    	
    	return list.toArray(new String[0]);
	}
	
	public static void setJsonAttribute(JSONObject json, String key, String value) {
		if(value != null) {
			json.put(key, value);
		}
	}
	
	public static void setJsonPropertiesArray(JSONObject json, String key, 
			HashMap<String, Object>[] propArray) {
		JSONArray arrayJson = JSONValue.parse("[]", JSONArray.class);
		if(propArray != null) {
			for(HashMap<String, Object> props : propArray) {
				JSONObject propsJson = JSONValue.parse("{}", JSONObject.class);
				Set<String> keySet = props.keySet();
				Iterator<String> keysIt = keySet.iterator();
				while(keysIt.hasNext()) {
					String propKey = keysIt.next();
					Object value = props.get(propKey);
					
					if(value != null) { 
						propsJson.put(propKey, value);
					}
				}
				
				arrayJson.add(propsJson);
			}
			
			json.put(key, arrayJson);
		}
	}
	
	public static void setJsonProperties(JSONObject json, String key, 
			HashMap<String, Object> props) {
		if(props != null) {
			JSONObject propsJson = JSONValue.parse("{}", JSONObject.class);
			Set<String> keySet = props.keySet();
			Iterator<String> keysIt = keySet.iterator();
			while(keysIt.hasNext()) {
				String propKey = keysIt.next();
				Object value = props.get(propKey);
					
				if(value != null) { 
					propsJson.put(propKey, value);
				}
			}
			
			json.put(key, propsJson);
		}
	}
}
