package com.session_guard.api.dao;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDAO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("password")
    private String password;

}
