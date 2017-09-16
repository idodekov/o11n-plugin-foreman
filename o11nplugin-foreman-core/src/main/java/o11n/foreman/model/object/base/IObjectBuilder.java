package o11n.foreman.model.object.base;

import net.minidev.json.JSONObject;

public interface IObjectBuilder {
	ForemanBaseObject buildObject(JSONObject json);
	JSONObject buildJson(ForemanBaseObject object);
	String getResourcePathForIterator();
}
