package o11n.foreman;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import ch.dunes.vso.sdk.api.HasChildrenResult;
import ch.dunes.vso.sdk.api.IPluginFactory;
import ch.dunes.vso.sdk.api.IPluginNotificationHandler;
import ch.dunes.vso.sdk.api.PluginExecutionException;
import ch.dunes.vso.sdk.api.QueryResult;
import o11n.foreman.configuration.ForemanConfigurationManagerImpl;
import o11n.foreman.configuration.ForemanServerConfiguration;
import o11n.foreman.configuration.IForemanConfigurationManager;
import o11n.foreman.model.object.ForemanServer;
import o11n.foreman.model.object.Host;
import o11n.foreman.model.object.base.ForemanBaseObject;
import o11n.foreman.model.object.base.ForemanFolder;
import o11n.foreman.model.object.base.ForemanIterator;
import o11n.foreman.model.object.folder.Folder_ForemanServer_Host;
import o11n.foreman.model.object.iterator.HostIterator;
import o11n.foreman.util.FinderUtils;

/**
 * Insert your comment for RemedyARSPluginFactory here
 * @see <fill in related>
 *
 * @since   <Product revision>
 * @version <Implementation version of this type>
 * @author  <Author of this type>
 */
public class ForemanPluginFactory implements IPluginFactory {
    private static final Logger log = Logger.getLogger(ForemanPluginFactory.class);

    private String pluginName;
    private String sessionId;
    private String username;
    private String password;
    private IPluginNotificationHandler pluginNotificationHandler;
    
    private Map<String, ForemanServer> foremanServers;
    
    public ForemanPluginFactory(String pluginName, String sessionId, String username, String password, IPluginNotificationHandler pluginNotificationHandler) {
        log.debug("new ForemanPluginFactory() --> username: " + username);
        this.pluginName = pluginName;
        this.sessionId = sessionId;
        this.username = username;
        this.password = password;
        this.pluginNotificationHandler = pluginNotificationHandler;
        foremanServers = new ConcurrentHashMap<String, ForemanServer>();
        
        initializieHosts();
    }
    
    public void initializieHosts() {
    	IForemanConfigurationManager configMgr = ForemanConfigurationManagerImpl.getInstance();
    	Collection<ForemanServerConfiguration> serverConfigs = configMgr.getConfigurations();
    	Iterator<ForemanServerConfiguration> it = serverConfigs.iterator();
    	
    	while(it.hasNext()) {
    		ForemanServerConfiguration config = it.next();
    		ForemanServer server = new ForemanServer(pluginNotificationHandler, config, username, password);
    		foremanServers.put(config.getId(), server);
    	}
    }

    @Override
    public void executePluginCommand(String cmd) throws PluginExecutionException {
        log.debug("executePluginCommand() --> cmd: " + cmd);
    }

    @Override
    public Object find(String type, String id) {
    	String serverId = FinderUtils.getServerIdFromId(id);
    	String foremanId = FinderUtils.getForemanIdFromId(id);
        ForemanServer server = foremanServers.get(serverId);
        
        if(FinderUtils.isIterator(type)) {
        	return findIterator(type, id, server);
        }
        
    	if(type.equals(ForemanServer.FINDER_TYPE)) {
    		return foremanServers.get(id);
    	} else if(type.equals(Folder_ForemanServer_Host.FINDER_TYPE)) {
    		return server.getFolder_ForemanServer_Host();
    	} else if(type.equals(Host.FINDER_TYPE)) {
    		return server.getFolder_ForemanServer_Host().getHostById(foremanId);
    	}
    	
        return null;
    }
    
    private ForemanIterator findIterator(String type, String id, ForemanServer server) {
    	String search = FinderUtils.getIteratorSearchFilterFromId(id);
		String sortBy = FinderUtils.getIteratorSortByFilterFromId(id);
		String orderBy = FinderUtils.getIteratorOrderByFilterFromId(id);
		
		if(type.equals(HostIterator.FINDER_TYPE)) {
    		return new HostIterator(server, server.getFolder_ForemanServer_Host(), 
    				server.getNotificationHandler(), search, sortBy, orderBy);
    	}
		
		return null;
    }

    @Override
    public QueryResult findAll(String type, String query) {
        log.debug("findAll() --> type: " + type + ", query: " + query);
        List<Object> result = new ArrayList<Object>();
        
        if(type.equals(ForemanServer.FINDER_TYPE)) {
        	result.addAll(foremanServers.values());
        	return new QueryResult(result, result.size());
    	} 
        
        Iterator<ForemanServer> it = this.foremanServers.values().iterator();
        while(it.hasNext()) {
        	ForemanServer server = it.next();
        	
        	if(type.equals(Folder_ForemanServer_Host.FINDER_TYPE)) {
        		result.add(server.getFolder_ForemanServer_Host());
        	} else if(type.equals(Host.FINDER_TYPE)) {
        		result.addAll(server.getFolder_ForemanServer_Host().getCachedChildren());
        	}
        }

        return new QueryResult(result, result.size());
    }

    @Override
    public HasChildrenResult hasChildrenInRelation(String parentType, String parentId, String relationName) {
        log.debug("hasChildrenInRelation() --> parentType: " + parentType + ", parentId: " + parentId + ", relationName: " + relationName);
        
        if(FinderUtils.isFolder(relationName)) {
        	return HasChildrenResult.Yes;
        } else if(parentType.equals(ForemanServer.FINDER_TYPE)) {
        	return HasChildrenResult.Yes;
        }
        
        return HasChildrenResult.Unknown;
    }

    @Override
    public List<?> findRelation(String parentType, String parentId, String relationName) {
        log.debug("findRelation() --> parentType: " + parentType + ", parentId: " + parentId + ", relationName: " + relationName);

        List<ForemanBaseObject> result = null;
        
        if (parentId == null) {
        	return new ArrayList<ForemanBaseObject>(foremanServers.values());
        }
        
        result = new ArrayList<ForemanBaseObject>();
        
        String serverId = FinderUtils.getServerIdFromId(parentId);
        ForemanServer server = foremanServers.get(serverId);
        
        if(parentType.equals(ForemanServer.FINDER_TYPE) 
        		&& relationName.equals(Folder_ForemanServer_Host.FINDER_TYPE)) {
        	ForemanBaseObject folder = server.getFolder_ForemanServer_Host();
        	result.add(folder);
        } else if(parentType.equals(Folder_ForemanServer_Host.FINDER_TYPE) 
        		&& relationName.equals(Host.FINDER_TYPE)) {
        	ForemanFolder folder = server.getFolder_ForemanServer_Host();
        	result = folder.getCachedChildren();
        }
        
        return result;
    }

    @Override
    public void invalidate(String type, String id) {
        log.debug("invalidate() --> type: " + type + ", id: " + id);
    }

    @Override
    public void invalidateAll() {
    	Iterator<ForemanServer> it = this.foremanServers.values().iterator();
        while(it.hasNext()) {
        	it.next().invalidate();
        }
    }
    
    public String getPluginName() {
        return pluginName;
    }

    public IPluginNotificationHandler getPluginNotificationHandler() {
        return pluginNotificationHandler;
    }
    
	public void updateServer(ForemanServerConfiguration newCfg) {
		ForemanServer server = foremanServers.get(newCfg.getId());
		if(server == null) {
			String message = "Can't update FormemanServer with ID " + newCfg.getId() + 
					". It doesn't exist.";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		pluginNotificationHandler.notifyElementUpdated(server.getFinderType(), newCfg.getId());
    }
    
	public void deleteServer(String cfgId) {
		ForemanServer server = foremanServers.get(cfgId);
		
		if(server == null) {
			String message = "Can't delete FormemanServer with ID " + cfgId + 
					". It doesn't exist.";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		
		foremanServers.remove(cfgId);
		pluginNotificationHandler.notifyElementDeleted(server.getFinderType(), cfgId);
	}
    
	public void createServer(ForemanServerConfiguration newCfg) {
		ForemanServer server = new ForemanServer(pluginNotificationHandler, newCfg, username, password);
		foremanServers.put(newCfg.getId(), server);
		pluginNotificationHandler.notifyElementInvalidate(server.getFinderType(), newCfg.getId());
    }

}
