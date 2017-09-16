package o11n.foreman.model.object.base;

import org.apache.log4j.Logger;

import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import o11n.foreman.model.object.ForemanServer;

public abstract class ForemanBaseObject implements IForemanObject {
	private static final Logger log = Logger.getLogger(ForemanBaseObject.class);
	
	private ForemanServer server;
	private String objectId;
	private String foremanId;
	private IPluginNotificationHandler notificationHandler;
	
	public ForemanBaseObject() {}
	
	/**
	 * Constructor is used by all base objects
	 * @param foremanId
	 * @param server
	 */
	public ForemanBaseObject(String foremanId, ForemanServer server) {
		this.foremanId = foremanId;
		this.server = server;
		this.notificationHandler = server.getNotificationHandler();
		
		generateObjectId(foremanId);
	}
	
	private void generateObjectId(String foremanId) {
		int hashCode = getFinderType().hashCode();
		if(hashCode < 0) {
			hashCode = hashCode * -1;
		}
		
		if(foremanId  == null) {
			objectId = server.getConfig().getId() + "#" + hashCode + "#0";
		} else {
			objectId = server.getConfig().getId() + "#" + hashCode + "#" + foremanId;
		}
	}
	
	
	/**
	 * Constructor is only used by ForemanServers
	 * @param objectId
	 * @param notificationHandler
	 */
	public ForemanBaseObject(String objectId, IPluginNotificationHandler notificationHandler) {
		super();
		this.objectId = objectId;
		this.notificationHandler = notificationHandler;
	}
	
	@Override
	public void notifyElementDeleted() {
		if (notificationHandler != null) {
			notificationHandler.notifyElementDeleted(getFinderType(), getId());
		} else {
			log.warn("Plugin notification handler is not set");
		}
	}
	
	@Override
	public void notifyElementUpdated() {
		if (notificationHandler != null) {
			notificationHandler.notifyElementUpdated(getFinderType(), getId());
		} else {
			log.warn("Plugin notification handler is not set");
		}
	}
	
	@Override
	public void notifyElementInvalidate() {
		if (notificationHandler != null) {
			notificationHandler.notifyElementInvalidate(getFinderType(), getId());
		} else {
			log.warn("Plugin notification handler is not set");
		}
	}
	
	@Override
	public ForemanServer getForemanServer() {
		return server;
	}

	@Override
	public IPluginNotificationHandler getNotificationHandler() {
		return notificationHandler;
	}

	@Override
	public String getId() {
		return objectId;
	}
	
	public void setId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getForemanId() {
		return foremanId;
	}

	public void setForemanId(String foremanId) {
		this.foremanId = foremanId;
	}
}
