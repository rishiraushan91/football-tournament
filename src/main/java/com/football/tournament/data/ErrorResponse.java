
package com.football.tournament.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

/**
 * ErrorResponse detail response
 */

@Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ErrorResponse
{

    @JsonProperty(value = "error", index = 1)
    private String error;

    @JsonProperty(value = "message", index = 2)
    private String message;

    @JsonProperty(value = "code", index = 3)
    private int code;

    @JsonProperty(value = "createdAt", index = 4)
    private OffsetDateTime createdAt;

}
