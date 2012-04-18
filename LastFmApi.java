import java.util.TreeMap;

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
}