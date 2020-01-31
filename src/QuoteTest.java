import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class QuoteTest {
    // create a test Quote, passing fixed amounts for the high, low, open, and close values
    Quote quoteTester = new Quote("high,low,open,close,volume100,85,90.5,96.34,200");


    // the Quote class pulls in string data and skips unnecessary strings while parsing doubles
    // these tests confirm that this functionality is working as expected

    @Test
    public void testHighVariableParsing(){
        assertEquals(100, quoteTester.getHigh(), "The High must be equal to 100");
    }

    private void assertEquals(int i, double high, String s) {
    }

    @Test
    public void testLowVariableParsing(){
        assertEquals(85, quoteTester.getLow(), "The Low must be equal to 85");
    }

    @Test
    public void testOpenVariableParsing(){
        assertEquals((int) 90.5, quoteTester.getOpen(), "The Open must be equal to 90.5");
    }

    @Test
    public void testCloseVariableParsing(){
        assertEquals((int) 96.34, quoteTester.getClose(), "The Close must be equal to 96.34");
    }

    @Test
    public void testVolumeVariablePArsing(){
        assertEquals(200, quoteTester.getVolume(),"The volume must be equal to 200");
    }

//    @Test
//    public void testSetETF(){
//        quoteTester.setETF("Test ETF");
//        assertEquals("Test ETF",quoteTester.getETF(), "setETF() method appears to be working improperly.");
//    }

    // the following test is used to confirm that the Stochastic Oscillator metric is being properly calculated
    // note that the success of this test is continent upon the tests for High, Low, and Close variables listed above
    @Test
    public void testSetK(){
        // set the value of k using the High, Low, and Close data passed to the Quote class
        quoteTester.setK(StochasticOscillator.calcStochasticOscillation(quoteTester.getHigh(),quoteTester.getLow(),
                quoteTester.getClose()));
        assertEquals((int) 75.60000000000002,quoteTester.getK(),"K value should be roughly equal to 75.6");
    }


}