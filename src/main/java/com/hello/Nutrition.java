package com.hello;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Nutrition {
    private long id;

    @Length(max = 3)
    private String foodName;

    public Nutrition() {
        // Jackson deserialization
    }

    public Nutrition(long id, String foodName) {
        this.id = id;
        this.foodName = foodName;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getFoodName() {
        return foodName;
    }
}
