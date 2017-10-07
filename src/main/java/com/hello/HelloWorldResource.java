package com.hello;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/hello-world/{name}")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String yo;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName, String yo) {
        this.template = template;
        this.defaultName = defaultName;
        this.yo = yo;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@PathParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Path("/yo")
    @Timed
    public Saying sayYo(@PathParam("name") Optional<String> name){
        final String value = String.format(yo, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}