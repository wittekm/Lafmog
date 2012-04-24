public class Lafmog {
    
    public static void main(String[] args) {
        try {
            Settings settings = Settings.getInstance();
            System.out.println(settings.getLastfmApiKey());
            
            // just testing
            LastFmApi.GetRecentTracksError error = LastFmApi.GetRecentTracksError.fromInt(2);
            System.out.println(error);
            
            LastFmApi api = new LastFmApi();
            api.test();
            
        } catch (Exception e) {
            System.err.println("Encountered exception " + e);
        }
    }
}