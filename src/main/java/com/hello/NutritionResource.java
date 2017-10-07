package com.hello;

import com.codahale.metrics.annotation.Timed;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.apache.http.protocol.HTTP.USER_AGENT;

@Path("/nutrition/{food}")
@Produces(MediaType.APPLICATION_JSON)
public class NutritionResource {
    private final String template;
    private final String yo;
    private final String defaultName;
    private final AtomicLong counter;

    public NutritionResource(String template, String defaultName, String yo) {
        this.template = template;
        this.defaultName = defaultName;
        this.yo = yo;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Nutrition foodNutrition(@PathParam("food") Optional<String> food) {
        final String value = String.format(template, food.orElse(defaultName));
        String url = "http://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=n&max=25&offset=0&api_key=DEMO_KEY";

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);

            // add request header
            request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(request);

            System.out.println("Response Code : "
                                       + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return new Nutrition(counter.incrementAndGet(), value);
    }
}
