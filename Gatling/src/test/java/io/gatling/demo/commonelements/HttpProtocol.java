package io.gatling.demo.commonelements;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class HttpProtocol {

    public static HttpProtocolBuilder httpProtocol = http
            //.proxy(Proxy("localhost",8888))
            .disableCaching()   //turn on/off for specific scenarios (no info why recorder do not place it here when checked)
            //.warmUp("http://192.168.88.248:1080")
            .baseUrl("http://192.168.88.248:1080")
            .disableAutoReferer()
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .doNotTrackHeader("1")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:137.0) Gecko/20100101 Firefox/137.0")
            .silentResources();   //ignore time load of resources in report, but they will affect tested system

}