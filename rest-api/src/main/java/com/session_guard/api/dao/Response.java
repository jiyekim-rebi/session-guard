package com.session_guard.api.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @JsonProperty("reason")
    private String reason;


}
