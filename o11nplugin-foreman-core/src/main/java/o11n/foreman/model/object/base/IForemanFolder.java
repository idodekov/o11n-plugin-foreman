package o11n.foreman.model.object.base;

import java.util.List;

public interface IForemanFolder {
	ForemanBaseObject findChild(String id);
	void addChild(ForemanBaseObject object);
	List<ForemanBaseObject> findChildren(String filter);
}
