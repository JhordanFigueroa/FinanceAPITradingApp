/**
 * This is the WebsiteDataReader Class, which establishes a connection with the API and gets the data we need
 *
 * @author Alex llgenfritz
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebsiteDataReader {
    URLTranslator translator = new URLTranslator();

    public Quote getData(String ETF) {
        String URL = translator.convert(ETF);
        String output = readConnection(URL);
        Quote quote = new Quote(output);
        quote.setETF(ETF);
        System.out.println(quote);
        return quote;
    }

    //This establishes the connection with the API
    private String readConnection(String url) {
        try {
            URL iex = new URL(url);
            URLConnection yc;
            yc = iex.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            String output = "";
            while ((inputLine = in.readLine()) != null)
                output += inputLine;
            in.close();
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}