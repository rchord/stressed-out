package com.hello;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class User {
    @JsonProperty("user_id")
    private int user_id;

    @JsonProperty("first_name")
    @Length(max = 3)
    private String first_name;

    @JsonProperty("last_name")
    @Length(max = 3)
    private String last_name;

    public User() {

    }

    public User(int user_id, String first_name, String last_name) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
