package o11n.foreman.util;

public class FinderUtils {
	private FinderUtils() {}
	
	public static boolean isFolder(String relationName) {
		return relationName.startsWith("Folder_");
	}
	
	public static boolean isIterator(String relationName) {
		return relationName.startsWith("Iterator_");
	}
	
	public static String getFolderParent(String relationName) {
		return org.apache.commons.lang.StringUtils.split(relationName, "_")[1];
	}
	
	public static String getServerIdFromId(String id) {
		String[] arr = org.apache.commons.lang.StringUtils.split(id, "#");
		
		if(arr.length > 0) {
			return org.apache.commons.lang.StringUtils.split(id, "#")[0];
		}
		
		return null;
	}
	
	public static String getForemanIdFromId(String id) {
		String[] arr = org.apache.commons.lang.StringUtils.split(id, "#");
		
		if(arr.length > 2) {
			return org.apache.commons.lang.StringUtils.split(id, "#")[2];
		}
		
		return null;
	}
	
	public static String getIteratorSearchFilterFromId(String id) {
		String[] arr = org.apache.commons.lang.StringUtils.split(id, "#");
		
		if(arr.length > 3) {
			return org.apache.commons.lang.StringUtils.split(id, "#")[3];
		}
		
		return null;
	}
	
	public static String getIteratorSortByFilterFromId(String id) {
		String[] arr = org.apache.commons.lang.StringUtils.split(id, "#");
		
		if(arr.length > 4) {
			return org.apache.commons.lang.StringUtils.split(id, "#")[4];
		}
		
		return null;
	}
	
	public static String getIteratorOrderByFilterFromId(String id) {
		String[] arr = org.apache.commons.lang.StringUtils.split(id, "#");
		
		if(arr.length > 5) {
			return org.apache.commons.lang.StringUtils.split(id, "#")[5];
		}
		
		return null;
	}
	
}
