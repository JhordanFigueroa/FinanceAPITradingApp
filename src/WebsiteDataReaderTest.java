
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import static org.junit.Assert.assertEquals;

public class WebsiteDataReaderTest {
    WebsiteDataReader websiteDataReaderTest = new WebsiteDataReader();

    @Test
    public void haveInternet() throws IOException {
        assertEquals("It appears you are not connected to the internet.", true, connectToGoogle());
    }

    public boolean connectToGoogle() throws IOException {
        try {
            testConnection("https://www.google.com");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // method to test if machine is connected to the internet
    public boolean testConnection(String link) throws IOException {
        URL testURL = new URL(link);
        URLConnection myURLConnection = testURL.openConnection();
        myURLConnection.connect();
        myURLConnection.getInputStream().close();
        return true;
    }
}