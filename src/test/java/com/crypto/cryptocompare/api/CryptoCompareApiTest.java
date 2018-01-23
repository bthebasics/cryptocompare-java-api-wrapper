package com.crypto.cryptocompare.api;

import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CryptoCompareApiTest {

    @Test
    public void CoinListTest() {
        CryptoCompareApi api = new CryptoCompareApi();
        JsonObject response = api.coinList();

        assertTrue(response.get("Response").getAsString().equals("Success"));
    }

    @Test
    public void PriceTest() {
        CryptoCompareApi api = new CryptoCompareApi();

        // Test 1
        JsonObject basicResponse = api.price(
                "ETH",
                "BTC,USD,EUR",
                null,
                null,
                null,
                null);

        assertTrue(basicResponse.has("BTC"));
        assertTrue(basicResponse.has("USD"));
        assertTrue(basicResponse.has("EUR"));


        JsonObject appNameResponse = api.price(
                "ETH",
                "BTC,USD,EUR",
                null,
                Optional.of("CryptoCompareWrapper"),
                null,
                null
        );

        assertTrue(appNameResponse.has("BTC"));
        assertTrue(appNameResponse.has("USD"));
        assertTrue(appNameResponse.has("EUR"));

        // Test 2
        JsonObject appNameWithSpaceResponse = api.price(
                "ETH",
                "BTC,USD,EUR",
                null,
                Optional.of("Crypto Compare Wrapper"),
                null,
                null
        );

        assertTrue(appNameWithSpaceResponse.has("BTC"));
        assertTrue(appNameWithSpaceResponse.has("USD"));
        assertTrue(appNameWithSpaceResponse.has("EUR"));

        // Test 3
        JsonObject exchangeResponse = api.price(
                "BTC",
                "USD,EUR",
                Optional.of("Coinbase"),
                Optional.of("CryptoCompareWrapper"),
                null,
                null
        );

        assertTrue(exchangeResponse.has("USD"));
        assertTrue(exchangeResponse.has("EUR"));
    }

    @Test
    public void PriceMultiTest() {
        CryptoCompareApi api = new CryptoCompareApi();

        // Test 1
        JsonObject basicResponse = api.priceMulti(
                "ETH,DASH",
                "BTC,USD,EUR",
                null,
                null,
                null,
                null);

        assertTrue(basicResponse.has("ETH"));
        assertTrue(basicResponse.has("DASH"));

        JsonObject basicResponseFromEth = basicResponse.get("ETH").getAsJsonObject();
        JsonObject basicResponseFromDash = basicResponse.get("DASH").getAsJsonObject();

        assertTrue(basicResponseFromEth.has("BTC"));
        assertTrue(basicResponseFromEth.has("USD"));
        assertTrue(basicResponseFromEth.has("EUR"));
        assertTrue(basicResponseFromDash.has("BTC"));
        assertTrue(basicResponseFromDash.has("USD"));
        assertTrue(basicResponseFromDash.has("EUR"));

        // Test 2
        JsonObject appNameResponse = api.priceMulti(
                "ETH,DASH",
                "BTC,USD,EUR",
                null,
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(appNameResponse.has("ETH"));
        assertTrue(appNameResponse.has("DASH"));

        JsonObject appNameResponseFromEth = appNameResponse.get("ETH").getAsJsonObject();
        JsonObject appNameResponseFromDash = appNameResponse.get("DASH").getAsJsonObject();

        assertTrue(appNameResponseFromEth.has("BTC"));
        assertTrue(appNameResponseFromEth.has("USD"));
        assertTrue(appNameResponseFromEth.has("EUR"));
        assertTrue(appNameResponseFromDash.has("BTC"));
        assertTrue(appNameResponseFromDash.has("USD"));
        assertTrue(appNameResponseFromDash.has("EUR"));

        // Test 3
        JsonObject altCoinResponse = api.priceMulti(
                "REP,BTC",
                "USD,XMR",
                null,
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(altCoinResponse.has("REP"));
        assertTrue(altCoinResponse.has("BTC"));

        JsonObject altCoinResponseFromRep = altCoinResponse.get("REP").getAsJsonObject();
        JsonObject altCoinResponseFromBtc = altCoinResponse.get("BTC").getAsJsonObject();

        assertTrue(altCoinResponseFromRep.has("USD"));
        assertTrue(altCoinResponseFromRep.has("XMR"));
        assertTrue(altCoinResponseFromBtc.has("USD"));
        assertTrue(altCoinResponseFromBtc.has("XMR"));

        // Test 4
        JsonObject exchangeResponse = api.priceMulti(
                "BTC,ETH",
                "USD",
                Optional.of("Coinbase"),
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(exchangeResponse.has("BTC"));
        assertTrue(exchangeResponse.has("ETH"));

        JsonObject exchangeResponseFromBtc = exchangeResponse.get("BTC").getAsJsonObject();
        JsonObject exchangeResponseFromEth = exchangeResponse.get("ETH").getAsJsonObject();

        assertTrue(exchangeResponseFromBtc.has("USD"));
        assertTrue(exchangeResponseFromEth.has("USD"));
    }

    @Test
    public void PriceMultiFullTest() {
        CryptoCompareApi api = new CryptoCompareApi();

        // Test 1
        JsonObject basicResponse = api.priceMultiFull(
                "ETH,DASH",
                "BTC,USD,EUR",
                null,
                null,
                null,
                null);

        assertTrue(basicResponse.has("RAW"));
        assertTrue(basicResponse.has("DISPLAY"));

        JsonObject basicResponseDisplay = basicResponse.get("DISPLAY").getAsJsonObject();

        assertTrue(basicResponseDisplay.has("ETH"));
        assertTrue(basicResponseDisplay.has("DASH"));

        JsonObject basicResponseDisplayEth = basicResponseDisplay.get("ETH").getAsJsonObject();
        JsonObject basicResponseDisplayDash = basicResponseDisplay.get("DASH").getAsJsonObject();

        assertTrue(basicResponseDisplayEth.has("BTC"));
        assertTrue(basicResponseDisplayEth.has("USD"));
        assertTrue(basicResponseDisplayEth.has("EUR"));
        assertTrue(basicResponseDisplayDash.has("BTC"));
        assertTrue(basicResponseDisplayDash.has("USD"));
        assertTrue(basicResponseDisplayDash.has("EUR"));

        // Test 2
        JsonObject appNameResponse = api.priceMultiFull(
                "ETH,DASH",
                "BTC,USD,EUR",
                null,
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(appNameResponse.has("RAW"));
        assertTrue(appNameResponse.has("DISPLAY"));

        JsonObject appNameResponseDisplay = appNameResponse.get("DISPLAY").getAsJsonObject();

        assertTrue(appNameResponseDisplay.has("ETH"));
        assertTrue(appNameResponseDisplay.has("DASH"));

        JsonObject appNameResponseDisplayEth = appNameResponseDisplay.get("ETH").getAsJsonObject();
        JsonObject appNameResponseDisplayDash = appNameResponseDisplay.get("DASH").getAsJsonObject();

        assertTrue(appNameResponseDisplayEth.has("BTC"));
        assertTrue(appNameResponseDisplayEth.has("USD"));
        assertTrue(appNameResponseDisplayEth.has("EUR"));
        assertTrue(appNameResponseDisplayDash.has("BTC"));
        assertTrue(appNameResponseDisplayDash.has("USD"));
        assertTrue(appNameResponseDisplayDash.has("EUR"));

        // Test 3
        JsonObject altCoinResponse = api.priceMultiFull(
                "REP,BTC",
                "USD,XMR",
                null,
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(altCoinResponse.has("RAW"));
        assertTrue(altCoinResponse.has("DISPLAY"));

        JsonObject altCoinResponseDisplay = altCoinResponse.get("DISPLAY").getAsJsonObject();

        assertTrue(altCoinResponseDisplay.has("REP"));
        assertTrue(altCoinResponseDisplay.has("BTC"));

        JsonObject altCoinResponseDisplayRep = altCoinResponseDisplay.get("REP").getAsJsonObject();
        JsonObject altCoinResponseDisplayBtc = altCoinResponseDisplay.get("BTC").getAsJsonObject();

        assertTrue(altCoinResponseDisplayRep.has("USD"));
        assertTrue(altCoinResponseDisplayRep.has("XMR"));
        assertTrue(altCoinResponseDisplayBtc.has("USD"));
        assertTrue(altCoinResponseDisplayBtc.has("XMR"));

        // Test 4
        JsonObject exchangeResponse = api.priceMultiFull(
                "BTC,ETH",
                "USD",
                Optional.of("Coinbase"),
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(exchangeResponse.has("RAW"));
        assertTrue(exchangeResponse.has("DISPLAY"));

        JsonObject exchangeResponseDisplay = exchangeResponse.get("DISPLAY").getAsJsonObject();

        assertTrue(exchangeResponseDisplay.has("BTC"));
        assertTrue(exchangeResponseDisplay.has("ETH"));

        JsonObject exchangeResponseDisplayBtc = exchangeResponseDisplay.get("BTC").getAsJsonObject();
        JsonObject exchangeResponseDisplayEth = exchangeResponseDisplay.get("ETH").getAsJsonObject();

        assertTrue(exchangeResponseDisplayBtc.has("USD"));
        assertTrue(exchangeResponseDisplayEth.has("USD"));
    }

    @Test
    public void GenerateAvgTest() {
        CryptoCompareApi api = new CryptoCompareApi();

        // Test 1
        JsonObject basicResponse = api.generateAvg(
                "BTC",
                "USD",
                "Coinbase,Bitfinex",
                null,
                null,
                null);

        assertTrue(basicResponse.has("RAW"));
        assertTrue(basicResponse.has("DISPLAY"));

        JsonObject basicResponseDisplay = basicResponse.get("DISPLAY").getAsJsonObject();

        assertTrue(basicResponseDisplay.has("FROMSYMBOL"));
        assertTrue(basicResponseDisplay.has("TOSYMBOL"));
        assertTrue(basicResponseDisplay.has("MARKET"));
        assertTrue(basicResponseDisplay.has("PRICE"));

        // Test 2
        JsonObject appNameResponse = api.generateAvg(
                "ETH",
                "BTC",
                "Poloniex,Kraken,Coinbase,HitBTC",
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(appNameResponse.has("RAW"));
        assertTrue(appNameResponse.has("DISPLAY"));

        JsonObject appNameResponseDisplay = appNameResponse.get("DISPLAY").getAsJsonObject();

        assertTrue(appNameResponseDisplay.has("FROMSYMBOL"));
        assertTrue(appNameResponseDisplay.has("TOSYMBOL"));
        assertTrue(appNameResponseDisplay.has("MARKET"));
        assertTrue(appNameResponseDisplay.has("PRICE"));

        // Test 3
        JsonObject exchangeResponse = api.generateAvg(
                "ZEC",
                "USD",
                "Poloniex,Bitfinex",
                Optional.of("CryptoCompareWrapper"),
                null,
                null);

        assertTrue(exchangeResponse.has("RAW"));
        assertTrue(exchangeResponse.has("DISPLAY"));

        JsonObject exchangeResponseDisplay = exchangeResponse.get("DISPLAY").getAsJsonObject();

        assertTrue(exchangeResponseDisplay.has("FROMSYMBOL"));
        assertTrue(exchangeResponseDisplay.has("TOSYMBOL"));
        assertTrue(exchangeResponseDisplay.has("MARKET"));
        assertTrue(exchangeResponseDisplay.has("PRICE"));
    }
}
