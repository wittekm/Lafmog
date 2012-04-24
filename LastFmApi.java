import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map.Entry;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStream;


import org.w3c.dom.*; // Document. element, etc
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


public class LastFmApi {
    
    // http://www.last.fm/api/show?service=278
    public enum GetRecentTracksError { 
    	INVALID_SERVICE (2),
    	INVALID_METHOD (3),
    	AUTHENTICATION_FAILED (4),
    	INVALID_FORMAT(5),
    	INVALID_PARAMETERS(6),
    	INVALID_RESOURCE(7),
    	OPERATION_FAILED(8),
    	INVALID_SESSION_KEY(9),
    	INVALID_API_KEY(10),
    	SERVICE_OFFLINE(11),
    	INVALID_METHOD_SIGNATURE(13),
    	RETRY(16),
    	SUSPENDED_API_KEY(26),
    	RATE_EXCEEDED(29);

    	private final int errorCode;
    	private static TreeMap<Integer, GetRecentTracksError> map;
    	static {
    	    map = new TreeMap<Integer, GetRecentTracksError>();
            for (GetRecentTracksError error: GetRecentTracksError.values()) {
            	map.put(new Integer(error.get()), error);
            }
        }

    	GetRecentTracksError(int errorCode) {
    		this.errorCode = errorCode;
    	}

    	private int get() {
    		return errorCode; 
    	}
        
        public static GetRecentTracksError fromInt(int i)  {
    		return map.get(i);
    	}
    }

    private static final String apiUrlString = "http://ws.audioscrobbler.com/2.0/";
    
    public void test() throws IOException {
        HashMap<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("user", Settings.getInstance().getUsername());
        
        HttpURLConnection conn = postToApi("user.getTopArtists", paramsMap);
        
        // now we gotta read it ughhhh
        if(conn.getResponseCode() == 200) {
            System.out.println("good job dude");
            processRest(conn.getInputStream());
        } else {
            System.out.println("oops. check .getErrorStream()");
        }
    }
    
    // json or xml-style? Choices, choices...
    public void processRest(InputStream istream) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Element domRoot = db.parse(istream).getDocumentElement();
            System.out.println(AssortedJunk.xmlToString(domRoot));
        }
        catch(Exception e) { 
            //whatever
        }
    }
    
    private HttpURLConnection postToApi(String method, HashMap<String, String> paramsMap) 
    throws IOException {
        HttpURLConnection conn = apiConnection();
        String params = params("user.getTopArtists", paramsMap);
        BufferedWriter postWriter = new BufferedWriter(
          new OutputStreamWriter(conn.getOutputStream()));
        postWriter.write(params);
        postWriter.close();
        return conn;
    }
    
    public HttpURLConnection apiConnection() throws IOException {
        URL apiUrl = null;
        try { apiUrl = new URL(apiUrlString); } catch(MalformedURLException e) { }
        HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        return conn;
    }
    
    public String params(String method, HashMap<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("method=")
        .append(method)
        .append("&");
        
        for(Entry<String, String> param : params.entrySet()) {
            stringBuilder.append(param.getKey())
            .append("=")
            .append(param.getValue())
            .append("&");
        }
        
        String apiKey = Settings.getInstance().getLastfmApiKey();
        
        stringBuilder.append("api_key=")
        .append(apiKey);
        
        return stringBuilder.toString();
    }
}