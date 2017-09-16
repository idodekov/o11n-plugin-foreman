package o11n.foreman.rest;

import net.minidev.json.JSONObject;

public class RestResponse {
	private int statusCode;
	private String reasonPhase;
	private JSONObject json;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getReasonPhase() {
		return reasonPhase;
	}
	public void setReasonPhase(String reasonPhase) {
		this.reasonPhase = reasonPhase;
	}
	public JSONObject getJson() {
		return json;
	}
	public void setJson(JSONObject json) {
		this.json = json;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Status code: " + statusCode + "\r\n");
		sb.append("Reason phase: " + reasonPhase + "\r\n");
		sb.append("JSON: " + json.toJSONString() + "\r\n");
		return sb.toString();
	}
}
