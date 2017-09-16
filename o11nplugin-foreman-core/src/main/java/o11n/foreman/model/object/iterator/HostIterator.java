package o11n.foreman.model.object.iterator;

import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import o11n.foreman.model.object.ForemanServer;
import o11n.foreman.model.object.Host;
import o11n.foreman.model.object.base.ForemanIterator;
import o11n.foreman.model.object.base.IObjectBuilder;

public class HostIterator extends ForemanIterator {
	public static final String FINDER_TYPE = "Iterator_ForemanHost";
	
	@Override
	public String getFinderType() {
		return FINDER_TYPE;
	}

	public HostIterator(ForemanServer server, IObjectBuilder builder, 
			IPluginNotificationHandler notificationHandler) {
		super(server, builder, notificationHandler);
	}
	
	public HostIterator(ForemanServer server, IObjectBuilder builder, 
			IPluginNotificationHandler notificationHandler, String search, 
			String sortBy, String sortOrder) {
		super(server, builder, notificationHandler, search, sortBy, sortOrder);
	}

	@Override
	public Host next() {
		return (Host) super.next();
	}
}
