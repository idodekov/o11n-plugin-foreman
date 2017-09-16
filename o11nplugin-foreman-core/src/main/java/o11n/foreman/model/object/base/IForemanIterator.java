package o11n.foreman.model.object.base;

import java.util.Iterator;

import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import o11n.foreman.model.object.ForemanServer;

public interface IForemanIterator extends Iterator<ForemanBaseObject> {
	@Override
	boolean hasNext();
	@Override
	ForemanBaseObject next();
	
	String getId();
	String getFinderType();
	String getSearch();
	String getSortBy();
	String getSortOrder();
	ForemanServer getServer();
	IPluginNotificationHandler getNotificationHandler();
	int getObjectCount();
}
