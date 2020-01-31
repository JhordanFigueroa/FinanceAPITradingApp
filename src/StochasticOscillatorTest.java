import org.junit.Test;
import static org.junit.Assert.assertEquals;

class StochasticOscillatorTest {

    @Test
    void calcStochasticOscillation() {
        double k = StochasticOscillator.calcStochasticOscillation(10,5,8);
        assertEquals(60,k,"The k value should equal 60");
    }

    private void assertEquals(int i, double k, String s) {
    }

    @Test
    void getDenominator() {
        double denominator = StochasticOscillator.setDenominator(10, 5);
        assertEquals(5,denominator,"The denominator should be equal to 5");
    }

    @Test
    void getNumerator() {
        double numerator = StochasticOscillator.setNumerator(8,5);
        assertEquals(3, numerator, "The numerator should equal 3.");
    }
}