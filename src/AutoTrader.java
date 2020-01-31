/**
 * This is the AutoTrader Class, which is how we tell the user whether to execute the trade. The user can change
 * the tolerance levels for each ETF at their discretion and preference
 *
 * @author Alex llgenfritz
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AutoTrader {
    static WebsiteDataReader data = new WebsiteDataReader();
    static ArrayList<Quote> SPYquotes = new ArrayList<Quote>();
    static ArrayList<Quote> QQQquotes = new ArrayList<Quote>();
    static ArrayList<Quote> DIAquotes = new ArrayList<Quote>();
    static double SPYtolerance = 100.0; // minimum threshold required to initiate trade
    static double QQQtolerance = 75.0; // minimum threshold required to initiate trade
    static double DIAtolerance = 50.0; // minimum threshold required to initiate trade

    //Boolean to decide whether to execute the trade based on the tolerance levels, which can be adjusted by user.
    public static boolean decideTrade(Quote quote) {
        double stochasticOscillator = quote.getK();
        double tolerance;
        switch (quote.getETF()) {
            case "SPY":
                tolerance = SPYtolerance;
                break;
            case "QQQ":
                tolerance = QQQtolerance;
                break;
            case "DIA":
                tolerance = DIAtolerance;
                break;
            default:
                return false;
        }

        return stochasticOscillator > tolerance;
    }

    public static void saveData() {
        System.out.println("Outputting data to test.csv");
        try {
            FileWriter test = new FileWriter("test.csv");
            test.append("ETF,high,low,open,close,volume\n");
            for (Quote SPYquote : SPYquotes) {
                test.append(SPYquote.csvText);
            }
            for (Quote DIAquote : DIAquotes) {
                test.append(DIAquote.csvText);
            }
            for (Quote QQQquote : QQQquotes) {
                test.append(QQQquote.csvText);
            }
            test.flush();
            test.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}