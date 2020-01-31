/**
 * This class is the Stochastic Oscillator, which is a financial indicator
 *
 * @author Chris Payne
 */
public abstract class StochasticOscillator {

    /**
     * Calculate the Stochastic Oscillator momentum indicator see the following URL
     * for more info: https://www.investopedia.com/terms/s/stochasticoscillator.asp
     *
     * @param h14
     * @param l14
     * @param close
     * @return
     */
    public static double calcStochasticOscillation(double h14, double l14, double close) {
        // class to calculate the Stochastic Oscillator indicator
        double denominator = setDenominator(h14, l14);
        double numerator = setNumerator(close, l14);
        double k = (numerator / denominator) * 100;
        return k;
    }

    public static double setNumerator(double close, double l14){
        return  close - l14;
    }

    public static double setDenominator(double h14, double l14){
        return h14 - l14;
    }
}