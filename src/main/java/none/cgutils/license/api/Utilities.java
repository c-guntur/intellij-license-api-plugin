package none.cgutils.license.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utilities {

    private Utilities() {
        // no instances of this class are allowed
    }

    public static String readUrl(URL url) {
        StringBuffer sb = new StringBuffer();
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {

        }
        return sb.toString();
    }

    public static void main(String[] args) throws MalformedURLException {
//        System.out.println(Utilities.readUrl(new URL("https://licenseapi.herokuapp.com/licenses")));
    }

}
