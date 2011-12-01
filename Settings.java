import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;

public class Settings {
    private Properties properties;
    private final String LASTFM_API_KEY, LASTFM_SECRET, FACEBOOK_APP_ID, 
      FACEBOOK_APP_SECRET;
    private static Settings instance;
    
    public Settings() throws RAIIException {
        properties = new Properties();
        try {
            properties.load(new FileReader("settings.txt"));
        } catch(IOException e) {
            throw new RAIIException();
        }
        LASTFM_API_KEY = properties.getProperty("LASTFM_API_KEY");
        LASTFM_SECRET = properties.getProperty("LASTFM_SECRET");
        FACEBOOK_APP_ID = properties.getProperty("FACEBOOK_APP_ID");
        FACEBOOK_APP_SECRET = properties.getProperty("FACEBOOK_APP_SECRET");
    }
    
    public String getLastfmApiKey() { return LASTFM_API_KEY; }
    public String getLastfmSecret() { return LASTFM_SECRET; }
    public String getFacebookAppId() { return FACEBOOK_APP_ID; }
    public String getFacebookAppSecret() { return FACEBOOK_APP_SECRET; }
    
    public static Settings getInstance() throws RAIIException {
        if(instance == null)
            instance = new Settings();
        return instance;
    }
}