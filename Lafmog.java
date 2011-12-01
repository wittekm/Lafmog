public class Lafmog {
    
    public static void main(String[] args) {
        try {
            Settings settings = Settings.getInstance();
            System.out.println(settings.getLastfmApiKey());
        } catch (Exception e) {
            System.err.println("Encountered exception " + e);
        }
    }
}