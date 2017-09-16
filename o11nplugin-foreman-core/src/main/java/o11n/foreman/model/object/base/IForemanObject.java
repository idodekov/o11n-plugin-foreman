package o11n.foreman.model.object.base;

import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import o11n.foreman.model.object.ForemanServer;

public interface IForemanObject {
	String getFinderType();
	void invalidate();
	void notifyElementDeleted();
	void notifyElementUpdated();
	void notifyElementInvalidate();
	ForemanServer getForemanServer();
	IPluginNotificationHandler getNotificationHandler();
	String getId();
	String getForemanId();
}
