import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class URLTranslatorTest {
    URLTranslator testURLTranslator = new URLTranslator();

    // the URLTranslator class builds the URL used by the API to retrieve financial data
    // if a sandbox is being used, then a different URL is required
    // the below tests ensure that the appropriate URL is being built by the method for the SPY index fund
    @Test
    public void testURLConvert(){
        String URL = testURLTranslator.convert("SPY");
        assertEquals("https://cloud.iexapis.com/stable/stock/SPY/quote?token=pk_7313575e87494da491b522361a906d15&filter=high,low,open,close,volume&format=csv",
                URL, "The URL does not match what should be passed to the API");
    }

    @Test
    public void testURLConvertSandbox(){
        testURLTranslator.setSandbox(true);
        String URL = testURLTranslator.convert("SPY");
        assertEquals("https://sandbox.iexapis.com/stable/stock/SPY/quote?token=Tpk_c72352ab6cf9403db89a7a721b5434cc&filter=high,low,open,close,volume&format=csv",
                URL, "The URL does not match what should be passed to the API");
    }

}