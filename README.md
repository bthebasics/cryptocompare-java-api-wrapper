[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.jeffreytai/cryptocompare-api-wrapper/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.jeffreytai/cryptocompare-api-wrapper)

# Java CryptoCompare Unofficial API Client

This library is designed to help you make your own projects that interact with the [CryptoCompare API](https://www.cryptocompare.com/api/#). There is a large amount of data that the API has made available. This repository aims to provide access to all of their current functionalities.

### Example of Application
```
import com.crypto.cryptocompare.api.CryptoCompareApi;
import com.google.gson.JsonObject;

CryptoCompareApi api = new CryptoCompareApi();
JsonObject response = api.coinList();
```

### Usage
Requests: The CrytoCompare API allows for a number of optional parameters to be provided. This can be passed in with the use of a map that specifies the name and value.

```
JsonObject response = api.priceMulti(
  "ETH,DASH",
  "BTC,USD,EUR",
  new LinkedHashMap<String, Object>() {{
    put("extraParams", "TestProject");
  }});
```

Responses: All responses provided by the API are in Gson JSON objects.

### Functions
This API currently has the following functions
* Full coin list
* Individual price
* Multi price 
* Full details on multi price
* Average data (all-time)
* Average data (daily)
* Historical prices
* Coin snapshot
* Coin snapshot by ID
* Social stats
* Histominute
* Histohour
* Histoday
* Mining equipment
* Top pairs

### Dependency Management

##### Maven
```
<dependency>
    <groupId>com.github.jeffreytai</groupId>
    <artifactId>cryptocompare-api-wrapper</artifactId>
    <version>1.0.0</version>
</dependency>
```

##### Gradle
```
compile 'com.github.jeffreytai:cryptocompare-api-wrapper:1.0.0'
```

##### Ivy
```
<dependency org="com.github.jeffreytai" name="cryptocompare-api-wrapper" rev="1.0.0" />
```
