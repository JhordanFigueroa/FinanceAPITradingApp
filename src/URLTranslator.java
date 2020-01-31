/**
 * This is the URLTranslator Class, which is where we structure the API URL in a way to search for any ETF/Stock
 *
 * @author Alex llgenfritz
 */
public class URLTranslator {
    String base = "https://cloud.iexapis.com/stable/stock/"; // base url for retrieving stocks
    String ETF; // spy, qqq, dia
    String range = "/quote"; // latest quote
    String filter = "&filter=high,low,open,close,volume"; // can pass isUSMarketOpen to see if that's the case
    String format = "&format=csv"; // csv format
    String testBase = "https://sandbox.iexapis.com/stable/stock/";
    boolean sandbox = false;
    private String token = "?token=pk_7313575e87494da491b522361a906d15"; // public token
    private String testToken = "?token=Tpk_c72352ab6cf9403db89a7a721b5434cc";

    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    //Converts the URL into a re-usable URL
    public String convert(String etf) {
        String URL;
        // returns the URL given the etf
        // high, low, open, close
        URL = base + etf + range + token + filter + format;
        if (sandbox) URL = testBase + etf + range + testToken + filter + format;
        System.out.println(URL);
        return URL;
    }
}