package com.github.samba.mohamed.project_action.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private String symbol;
    @JsonProperty("1. open")
    private float open;
    private float close;
    private float high;
    private float low;
    private Long volume;

}
