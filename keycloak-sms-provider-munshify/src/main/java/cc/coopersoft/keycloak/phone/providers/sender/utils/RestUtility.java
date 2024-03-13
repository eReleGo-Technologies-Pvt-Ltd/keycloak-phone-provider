package cc.coopersoft.keycloak.phone.providers.sender.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.coopersoft.keycloak.phone.providers.sender.exceptions.IntegrationException;


public class RestUtility {
	
    /**
     * This method is used to get parameters on URL connection.
     *
     * @param url
     *            String
     * 
     * @return String
     * 
     * @throws IntegrationException
     */
	
	private static final String API_TIMEOUT = "30000";
	
	public static final String GET = "GET";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestUtility.class);
	
    public static String sendGetData(final String url) throws IntegrationException {
        String inputLine = null;
        LOGGER.info("Base URL : " + url + " Parameters : ");
        URL connection = null;
        try {
            connection = new URL(url);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        StringBuilder response = new StringBuilder();
        try {
            URLConnection urlCon = connection.openConnection();
            HttpURLConnection con = null;
            if (urlCon instanceof HttpsURLConnection) {
                con = (HttpsURLConnection) connection.openConnection();
            } else {
                con = (HttpURLConnection) connection.openConnection();
            }
            con.setConnectTimeout(Integer.parseInt(API_TIMEOUT));
            con.setReadTimeout(Integer.parseInt(API_TIMEOUT));
            con.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            con.setRequestMethod(GET);

            int responseCode = con.getResponseCode();
            LOGGER.info("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append("\n");
            }
            in.close();
            // LOG.info("response:" + response);
        } catch (SocketTimeoutException exception) {
        	LOGGER.error("Core Service Error " + url);
        	LOGGER.error("Error " + exception.getMessage());
            throw new IntegrationException("Exception Occured Service Timed Out");
        } catch (IOException exception) {
        	LOGGER.error("Error " + exception.getMessage());
        } catch (Exception exception) {
        	LOGGER.error("Error " + exception.getMessage());
        }
        return response.toString();
    }

}

