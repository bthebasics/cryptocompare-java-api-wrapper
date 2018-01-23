package com.crypto;

import com.crypto.cryptocompare.api.CryptoCompareApi;
import com.google.gson.JsonObject;

public class Application {
    public static void main(String[] args) {
        CryptoCompareApi api = new CryptoCompareApi();
        JsonObject json = api.coinList();
        System.out.println(json.toString());
    }
}
