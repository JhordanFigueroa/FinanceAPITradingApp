import org.junit.Test;
import static org.junit.Assert.assertEquals;


class AutoTraderTest {
    AutoTrader testAutoTrader = new AutoTrader();
    // create a test Quote, passing fixed amounts for the high, low, open, and close values
    Quote quoteTesterApproveTrade = new Quote("high,low,open,close,volume100,85,90.5,96.34,200");
    Quote quoteTesterDeclineTrade = new Quote("high,low,open,close,volume100,85,90.5,96.34,200");


    @Test
    public void testApproveSPYTrade() {
        // the AutoTrader uses a the k value for a specific ETF and compares it to a tolerance value to approve/reject trade
        quoteTesterApproveTrade.setETF("SPY");
        // set the k value over the tolerance value of 100
        quoteTesterApproveTrade.setK(101);
        assertEquals("The k value is greater than the tolerance for " +
                        "the SPY index. The trade should be executed (return true).", true,
                testAutoTrader.decideTrade(quoteTesterApproveTrade));
    }

    @Test
    public void testDeclineSPYTrade() {
        // the AutoTrader uses a the k value for a specific ETF and compares it to a tolerance value to approve/reject trade
        quoteTesterApproveTrade.setETF("SPY");
        // set the k value over the tolerance value of 100
        quoteTesterApproveTrade.setK(99);
        assertEquals("The k value is less than the tolerance for " +
                        "the SPY index. The trade should be not be executed (return false).", false,
                testAutoTrader.decideTrade(quoteTesterApproveTrade));
    }
}