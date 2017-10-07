package com.hello.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import com.hello.SayingModel;

import java.util.Optional;

/**
 * Created by rchord on 8/15/17.
 */
public interface HelloClient {
    @RequestLine("GET /hello/{name}")
    @Headers({
        "Content-Type: application/json"
    })
    SayingModel getSayingStuff(@Param("name") Optional<String> name);

    @RequestLine("POST /hello/{name}")
    @Headers({
        "Content-Type: application/json"
    })
    void create(SayingModel saying) ;

}
