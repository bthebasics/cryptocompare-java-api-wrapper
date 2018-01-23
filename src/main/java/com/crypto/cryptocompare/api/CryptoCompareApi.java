package com.crypto.cryptocompare.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;

public class CryptoCompareApi {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CryptoCompareApi.class);

    /**
     * Base Url for CryptoCompare API requests
     */
    private final String BASE_URL = "https://min-api.cryptocompare.com/data/";

    /**
     * Google JSON parser
     */
    private JsonParser jsonParser;

    /*************************
     * Constructor
     *************************/

    public CryptoCompareApi() {
        this.jsonParser = new JsonParser();
    }

    /**
     * Get general info for all the coins available on the website
     * @return
     */
    public JsonObject coinList() {
        String requestUrl = this.BASE_URL + "all/coinlist";
        JsonObject response = getResponseBody(requestUrl);
        return response;
    }

    /**
     * Get the price of a currency against multiple currencies
     * @param fsym from symbol
     * @param tsyms to symbols, include multiple symbols
     * @param e name of exchange. default: CCCAGG
     * @param extraParams name of your application
     * @param sign if set to true, the server will sign the requests
     * @param tryConversion if set to false, it will try to get values without using any conversion at all
     * @return
     * TODO: handle errors/space characters in tsyms
     */
    public JsonObject price(String fsym, String tsyms, Optional<String> e, Optional<String> extraParams,
                            Optional<Boolean> sign, Optional<Boolean> tryConversion) {
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(this.BASE_URL)
                .append("price?fsym=")
                .append(fsym)
                .append("&tsyms=")
                .append(tsyms);

        if (e != null && e.isPresent()) {
            requestUrl.append("&e=")
                    .append(e.get());
        }

        if (extraParams != null && extraParams.isPresent()) {
            String exchangeName = extraParams.get().replaceAll(" ", "%20");
            requestUrl.append("&extraParams=")
                    .append(exchangeName);
        }

        if (sign != null && sign.isPresent()) {
            requestUrl.append("&sign=")
                    .append(sign.get().toString());
        }

        if (tryConversion != null && tryConversion.isPresent()) {
            requestUrl.append("&tryConversion=")
                    .append(tryConversion.get().toString());
        }

        JsonObject response = getResponseBody(requestUrl.toString());
        return response;
    }


    /**
     * Get a matrix of currency prices
     * @param fsyms From Symbols, include multiple symbols
     * @param tsyms To Symbols, include multiple symbols
     * @param e Name of exchange. Default: CCCAGG
     * @param extraParams Name of your application
     * @param sign If set to true, the server will sign the requests
     * @param tryConversion If set to false, it will try to get values without using any conversion at all
     * @return
     */
    public JsonObject priceMulti(String fsyms, String tsyms, Optional<String> e, Optional<String> extraParams, Optional<Boolean> sign, Optional<Boolean> tryConversion) {
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(this.BASE_URL)
                .append("pricemulti?fsyms=")
                .append(fsyms)
                .append("&tsyms=")
                .append(tsyms);

        if (e != null && e.isPresent()) {
            requestUrl.append("&e=")
                    .append(e.get());
        }

        if (extraParams != null && extraParams.isPresent()) {
            String exchangeName = extraParams.get().replaceAll(" ", "%20");
            requestUrl.append("&extraParams=")
                    .append(exchangeName);
        }

        if (sign != null && sign.isPresent()) {
            requestUrl.append("&sign=")
                    .append(sign.get().toString());
        }

        if (tryConversion != null && tryConversion.isPresent()) {
            requestUrl.append("&tryConversion=")
                    .append(tryConversion.get().toString());
        }

        JsonObject response = getResponseBody(requestUrl.toString());
        return response;
    }


    /**
     * Get all the current trading info (price, vol, open, high, low etc) of any list of cryptocurrencies
     * in any other currency that you need. If the crypto does not trade directly into the toSymbol
     * requested, BTC will be used for conversion. This API also returns Display values for all the fields.
     * If the opposite pair trades we invert it (eg.: BTC-XMR).
     * @param fsyms From Symbol
     * @param tsyms To Symbols, include multiple symbols
     * @param e Name of exchange. Default: CCCAGG
     * @param extraParams Name of your application
     * @param sign If set to true, the server will sign the requests
     * @param tryConversion If set to false, it will try to get values without using any conversion at all
     * @return
     */
    public JsonObject priceMultiFull(String fsyms, String tsyms, Optional<String> e, Optional<String> extraParams,
                                     Optional<Boolean> sign, Optional<Boolean> tryConversion) {
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(this.BASE_URL)
                .append("pricemultifull?fsyms=")
                .append(fsyms)
                .append("&tsyms=")
                .append(tsyms);

        if (e != null && e.isPresent()) {
            requestUrl.append("&e=")
                    .append(e.get());
        }

        if (extraParams != null && extraParams.isPresent()) {
            String exchangeName = extraParams.get().replaceAll(" ", "%20");
            requestUrl.append("&extraParams=")
                    .append(exchangeName);
        }

        if (sign != null && sign.isPresent()) {
            requestUrl.append("&sign=")
                    .append(sign.get().toString());
        }

        if (tryConversion != null && tryConversion.isPresent()) {
            requestUrl.append("&tryConversion=")
                    .append(tryConversion.get().toString());
        }

        JsonObject response = getResponseBody(requestUrl.toString());
        return response;
    }


    /**
     * Compute the current trading info (price, vol, open, high, low etc) of the requested pair
     * as a volume weighted average based on the markets requested.
     * @param fsym From Symbol
     * @param tsym To Symbols
     * @param e Name of exchanges, include multiple
     * @param extraParams Name of your application
     * @param sign If set to true, the server will sign the requests
     * @param tryConversion If set to false, it will try to get values without using any conversion at all
     * @return
     */
    public JsonObject generateAvg(String fsym, String tsym, String e, Optional<String> extraParams,
                                  Optional<Boolean> sign, Optional<Boolean> tryConversion) {
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(this.BASE_URL)
                .append("generateAvg?fsym=")
                .append(fsym)
                .append("&tsym=")
                .append(tsym)
                .append("&e=")
                .append(e);

        if (extraParams != null && extraParams.isPresent()) {
            String exchangeName = extraParams.get().replaceAll(" ", "%20");
            requestUrl.append("&extraParams=")
                    .append(exchangeName);
        }

        if (sign != null && sign.isPresent()) {
            requestUrl.append("&sign=")
                    .append(sign.get().toString());
        }

        if (tryConversion != null && tryConversion.isPresent()) {
            requestUrl.append("&tryConversion=")
                    .append(tryConversion.get().toString());
        }

        JsonObject response = getResponseBody(requestUrl.toString());
        return response;
    }





    /**
     * Get the response body given a url in the form of a JsonObject
     * @param requestUrl
     * @return
     */
    private JsonObject getResponseBody(final String requestUrl) {
        String result = null;
        try {
            // Connect to the requested URL
            URL url = new URL(requestUrl);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");

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

        JsonObject jsonObject = (JsonObject) this.jsonParser.parse(result);
        return jsonObject;
    }
}
