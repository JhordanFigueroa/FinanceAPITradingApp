/**
 * This is the Quote Class, which is where we read through the JSON format of the API.
 *
 * @author Alex llgenfritz
 */

import java.util.Scanner;

public class Quote {
    //Instance Variables
    String ETF;
    String csvText;
    double high;
    double low;
    double open;
    double close;
    double volume;
    double k;

    public Quote(String text) { // text is passed as the output from the url that gets the information
        // typically looks like this:
        // "high,low,open,close20.1,20.1,20.1,20.1"
        System.out.println(text);
        Scanner textScan = new Scanner(text);
        textScan.useDelimiter(",");
        textScan.next(); // skips the word high
        textScan.next(); // skips the word low
        textScan.next(); // skips the word open
        textScan.next(); // skips the word volume
        high = Double.parseDouble(textScan.next().substring(6)); // gets the number after "close"
        low = Double.parseDouble(textScan.next()); // gets the number after that
        open = Double.parseDouble(textScan.next()); // etc.
        close = Double.parseDouble(textScan.next());
        volume = Double.parseDouble(textScan.next());
        setK(StochasticOscillator.calcStochasticOscillation(this.getHigh(), this.getLow(), this.getClose()));
        textScan.close();
        System.out.println(volume);
    }

    //Getters and Setters
    public String getETF() {
        return ETF;
    }

    public void setETF(String eTF) {
        ETF = eTF;
        csvText = ETF + "," + high + "," + low + "," + open + "," + close + volume + "\n";
    }

    public double getHigh() { return high; }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getOpen() { return open; }

    public double getVolume() { return volume; }

    @Override
    //ToString method to display data.
    public String toString() {
        return "Security: " + ETF + ", high: " + high + ", low: " + low + ", open: " + open + ", close: " +
                close + ", Volume: " +volume;
    }
}