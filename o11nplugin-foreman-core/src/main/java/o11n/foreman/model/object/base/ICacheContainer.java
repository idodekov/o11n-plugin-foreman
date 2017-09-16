package o11n.foreman.model.object.base;

import java.util.List;

public interface ICacheContainer {
	ForemanBaseObject getCachedChild(String id);
	void addCachedChild(ForemanBaseObject object);
	List<ForemanBaseObject> getCachedChildren();
	void reloadCache();
}
