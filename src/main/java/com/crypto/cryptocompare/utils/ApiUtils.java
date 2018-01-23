package com.crypto.cryptocompare.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class ApiUtils {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ApiUtils.class);

    /**
     * User agent for HTTP requests
     */
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";

    /**
     * Google JSON parser
     */
    private static JsonParser jsonParser = new JsonParser();

    /**
     * Empty constructor
     */
    public ApiUtils() {}

    /**
     * Append the optional parameters to the URL
     * @param url
     * @param optionalParams
     * @return
     */
    public static String appendOptionalParameters(String url, Map<String, Object> optionalParams) {
        for (Map.Entry<String, Object> param : optionalParams.entrySet()) {
            String value = (param.getKey().equals("extraParams"))
                    ? param.getValue().toString().replaceAll(" ", "%20")
                    : param.getValue().toString();

            url += "&" + param.getKey() + "=" + value;
        }
        return url;
    }

    /**
     * Get the response body given a url in the form of a JsonObject
     * @param requestUrl
     * @return
     */
    public static JsonObject getResponseBody(final String requestUrl) {
        String result = null;
        try {
            // Connect to the requested URL
            URL url = new URL(requestUrl);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("User-Agent", USER_AGENT);

            // Stream the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));

            // Write each line of the response into a buffer
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            result = sb.toString();
        } catch (IOException ex) {
            logger.error("Error getting response body for {}", requestUrl);
        }

        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        return jsonObject;
    }
}
