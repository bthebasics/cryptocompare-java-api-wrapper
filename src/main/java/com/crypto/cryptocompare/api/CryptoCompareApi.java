package com.crypto.cryptocompare.api;

import com.crypto.cryptocompare.utils.ApiUtils;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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
     * Original Url (soon to be decremented)
     */
    private final String DEPRECATED_URL = "https://cryptocompare.com/api/data/";


    /*************************
     * Empty Constructor
     *************************/

    public CryptoCompareApi() {}

    /**
     * Get general info for all the coins available on the website
     * @return
     */
    public JsonObject coinList() {
        String requestUrl = this.BASE_URL + "all/coinlist";
        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }

    /**
     * Get the price of a currency against multiple currencies
     * @param fsym from symbol
     * @param tsyms to symbols, include multiple symbols
     * @param optionalParams
     * @return
     * TODO: handle errors/space characters in tsyms
     */
    public JsonObject price(String fsym, String tsyms, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("price?fsym=")
                .append(fsym)
                .append("&tsyms=")
                .append(tsyms);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }

    /**
     * Get a matrix of currency prices
     * @param fsyms From Symbols, include multiple symbols
     * @param tsyms To Symbols, include multiple symbols
     * @param optionalParams
     * @return
     */
    public JsonObject priceMulti(String fsyms, String tsyms, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("pricemulti?fsyms=")
                .append(fsyms)
                .append("&tsyms=")
                .append(tsyms);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }


    /**
     * Get all the current trading info (price, vol, open, high, low etc) of any list of cryptocurrencies
     * in any other currency that you need. If the crypto does not trade directly into the toSymbol
     * requested, BTC will be used for conversion. This API also returns Display values for all the fields.
     * If the opposite pair trades we invert it (eg.: BTC-XMR).
     * @param fsyms From Symbol
     * @param tsyms To Symbols, include multiple symbols
     * @param optionalParams
     * @return
     */
    public JsonObject priceMultiFull(String fsyms, String tsyms, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("pricemultifull?fsyms=")
                .append(fsyms)
                .append("&tsyms=")
                .append(tsyms);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }


    /**
     * Compute the current trading info (price, vol, open, high, low etc) of the requested pair
     * as a volume weighted average based on the markets requested.
     * @param fsym From Symbol
     * @param tsym To Symbols
     * @param e Name of exchanges, include multiple
     * @param optionalParams
     * @return
     */
    public JsonObject generateAvg(String fsym, String tsym, String e, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("generateAvg?fsym=")
                .append(fsym)
                .append("&tsym=")
                .append(tsym)
                .append("&e=")
                .append(e);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }

    /**
     * Get day average price. The values are based on hourly vwap data and the average can
     * be calculated in different waysIt uses BTC conversion if data is not available because
     * the coin is not trading in the specified currency. If tryConversion is set to false
     * it will give you the direct data. If no toTS is given it will automatically do the
     * current day. Also for different timezones use the UTCHourDiff paramThe calculation
     * types are: HourVWAP - a VWAP of the hourly close price,MidHighLow - the average
     * between the 24 H high and low.VolFVolT - the total volume from / the total volume to
     * (only available with tryConversion set to false so only for direct trades but the
     * value should be the most accurate price)
     * @param fsym From Symbol
     * @param tsym To Symbols
     * @param optionalParams
     * @return
     */
    public JsonObject dayAvg(String fsym, String tsym, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("dayAvg?fsym=")
                .append(fsym)
                .append("&tsym=")
                .append(tsym);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }


    /**
     * Get the price of any cryptocurrency in any other currency that you need at a given
     * timestamp. The price comes from the daily info - so it would be the price at the
     * end of the day GMT based on the requested TS. If the crypto does not trade directly
     * into the toSymbol requested, BTC will be used for conversion. Tries to get direct
     * trading pair data, if there is none or it is more than 30 days before the ts requested,
     * it uses BTC conversion. If the opposite pair trades we invert it (eg.: BTC-XMR)
     * @param fsym From Symbol
     * @param tsyms To Symbols, include multiple
     * @param optionalParams
     * @return
     */
    public JsonObject priceHistorical(String fsym, String tsyms, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("pricehistorical?fsym=")
                .append(fsym)
                .append("&tsyms=")
                .append(tsyms);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }


    /**
     * Get data for a currency pair. It returns general block explorer information,
     * aggregated data and individual data for each exchange available.
     * Warning: This api is getting abused and will be moved to a min-api path in the near future. Please try not to use it.
     * @param fsym The symbol of the currency you want to get that for
     * @param tsym The symbol of the currency that data will be in.
     * @return
     */
    public JsonObject coinSnapshot(String fsym, String tsym) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.DEPRECATED_URL)
                .append("coinsnapshot?fsym=")
                .append(fsym)
                .append("&tsym=")
                .append(tsym);

        JsonObject response = ApiUtils.getResponseBody(sb.toString());
        return response;
    }

    /**
     * Get the general, subs (used to connect to the streamer and to figure out what
     * exchanges we have data for and what are the exact coin pairs of the coin) and
     * the aggregated prices for all pairs available.
     * @param id The id of the coin you want data for
     * @return
     */
    public JsonObject coinSnapshotFullById(Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.DEPRECATED_URL)
                .append("coinsnapshotfullbyid?id=")
                .append(id.toString());

        JsonObject response = ApiUtils.getResponseBody(sb.toString());
        return response;
    }

    /**
     * Get CryptoCompare website, Facebook, code repository, Twitter and Reddit data
     * for coins. If called with the id of a cryptopian you just get data from our website
     * that is available to the public.
     * @param id The id of the coin/exchange you want social data for
     * @return
     */
    public JsonObject socialStats(Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.DEPRECATED_URL)
                .append("socialstats?id=")
                .append(id.toString());

        JsonObject response = ApiUtils.getResponseBody(sb.toString());
        return response;
    }

    /**
     * Get open, high, low, close, volumefrom and volumeto from the each minute
     * historical data. This data is only stored for 7 days, if you need more, use the
     * hourly or daily path. It uses BTC conversion if data is not available because the
     * coin is not trading in the specified currency
     * @param fsym From Symbol
     * @param tsym To Symbols
     * @param optionalParams
     * @return
     */
    public JsonObject histoMinute(String fsym, String tsym, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("histominute?fsym=")
                .append(fsym)
                .append("&tsym=")
                .append(tsym);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }


    /**
     * Get open, high, low, close, volumefrom and volumeto from the each hour historical
     * data. It uses BTC conversion if data is not available because the coin is not trading
     * in the specified currency.
     * @param fsym From Symbol
     * @param tsym To Symbols
     * @param optionalParams
     * @return
     */
    public JsonObject histoHour(String fsym, String tsym, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("histohour?fsym=")
                .append(fsym)
                .append("&tsym=")
                .append(tsym);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }


    /**
     * Get open, high, low, close, volumefrom and volumeto daily historical data. The values
     * are based on 00:00 GMT time. It uses BTC conversion if data is not available because
     * the coin is not trading in the specified currency.
     * @param fsym From Symbol
     * @param tsym To Symbols
     * @param optionalParams
     * @return
     */
    public JsonObject histoDay(String fsym, String tsym, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("histoday?fsym=")
                .append(fsym)
                .append("&tsym=")
                .append(tsym);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }


    /**
     * Used to get all the mining equipment available on the website. It returns an array
     * of mining equipment objects
     * @return
     */
    public JsonObject miningEquipment() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.DEPRECATED_URL)
                .append("miningequipment");

        JsonObject response = ApiUtils.getResponseBody(sb.toString());
        return response;
    }


    /**
     * Get top pairs by volume for a currency (always uses our aggregated data). The number
     * of pairs you get is the minimum of the limit you set (default 5) and the total number
     * of pairs available
     * @param fsym From Symbol
     * @param optionalParams
     * @return
     */
    public JsonObject topPairs(String fsym, Map<String, Object> optionalParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.BASE_URL)
                .append("top/pairs?fsym=")
                .append(fsym);

        String requestUrl = ApiUtils.appendOptionalParameters(sb.toString(), optionalParams);

        JsonObject response = ApiUtils.getResponseBody(requestUrl);
        return response;
    }

}
