package com.hello.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by rchord on 8/15/17.
 */
@Slf4j
public class ClientFactory {

    public static HelloClient getHelloClient() {
                return createBuilder().target(HelloClient.class, "http://localhost:8081/api/");
    }

    public static Feign.Builder createBuilder() {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                .registerModule(new JavaTimeModule());

        return Feign.builder().client(new OkHttpClient())
                    .decoder(new GsonDecoder()).encoder(new GsonEncoder())
                    .logger(new Slf4jLogger())
                    .logLevel(Logger.Level.FULL);
    }
}
