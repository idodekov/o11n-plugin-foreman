package o11n.foreman.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import o11n.foreman.configuration.ForemanServerConfiguration;

public class RestRequest {
	private static final Logger log = Logger.getLogger(RestRequest.class);
	
	private HttpUriRequest request;
	
	public RestRequest(RequestType type, ForemanConnection connection, 
			String resourcePath) {
		this(type, connection, resourcePath, null);
	}
	
	public RestRequest(RequestType type, ForemanConnection connection, 
			String resourcePath, String body) {
		switch(type) {
			case PUT :
				HttpPut put = new HttpPut(connection.buildRestUrl(resourcePath));
				if(body != null) {
					put.setEntity(buildEntity(body));
				}
				request = put;
				break;
			case POST :
				HttpPost post = new HttpPost(connection.buildRestUrl(resourcePath));
				if(body != null) {
					post.setEntity(buildEntity(body));
				}
				request = post;
				break;
			case DELETE :
				request = new HttpDelete(connection.buildRestUrl(resourcePath));
				break;
			case GET :
				request = new HttpGet(connection.buildRestUrl(resourcePath));
				break;
			default:
				throw new IllegalArgumentException("Unknown request type: " + type);
		}
		
		String credentials = connection.getUsername() + ":" + connection.getPassword();
		credentials = Base64.encodeBase64String(credentials.getBytes());
		request.setHeader("Authorization", "Basic " + credentials);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-Type", "application/json");
	}
	
	private HttpEntity buildEntity(String content) {
		try {
			return new ByteArrayEntity(content.getBytes("UTF-8"));
		} catch (Exception e) {
			log.error("An erro has occurred while trying to set HTTP request body. " + 
					e.getMessage(), e);
			return null;
		}
	}
	
	public RestResponse execute() throws ForemanRestException {
		RestResponse restResponse = new RestResponse();
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		BufferedReader jsonReader = null;
		
		try {
			client = HttpClients.createDefault();
			response = client.execute(request);
			restResponse.setStatusCode(response.getStatusLine().getStatusCode());
			restResponse.setReasonPhase(response.getStatusLine().getReasonPhrase());
			
			jsonReader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = jsonReader.readLine()) != null) {
			    result.append(line);
			}
			
			JSONObject json = JSONValue.parse(result.toString(), JSONObject.class);
			restResponse.setJson(json);
		} catch (Exception e) {
			String errorMessage = "Error occurred while attempting to "
					+ "connect to Foreman server. " + e.getMessage();
			log.error(errorMessage, e);
			throw new ForemanRestException(errorMessage, e);
		} finally {
			try {
				if(jsonReader != null) {
					jsonReader.close();
				}
				
				if(response != null) {
					response.close();
				}
				
				if(client != null) {
					client.close();
				}
			} catch(Exception e1) {
				String errorMessage = "Error occurred while attempting to "
						+ "close REST connection. " + e1.getMessage();
				log.error(errorMessage, e1);
				throw new ForemanRestException(errorMessage, e1);
			}
		}
		
		if(log.isDebugEnabled()) {
			log.debug(response);
		}
		
		return restResponse;
	}
	
	public static void main(String[] args) {
		ForemanServerConfiguration config = new ForemanServerConfiguration();
		config.setHost("www.foaas.com");
		config.setPort(80);
		config.setSsl(false);
		config.setUsername("admin");
		config.setPassword("VMware1!");
		config.setSharedSession(true);
		ForemanConnection connection = new ForemanConnection(config,null,null);
		RestRequest request = new RestRequest(RequestType.GET, 
				connection, "/version");
		try {
			RestResponse response = request.execute();
			System.out.println(response.toString());
		} catch (ForemanRestException e) {
			e.printStackTrace();
		}
	}
}
