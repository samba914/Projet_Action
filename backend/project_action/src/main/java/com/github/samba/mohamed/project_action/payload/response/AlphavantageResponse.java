package com.github.samba.mohamed.project_action.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlphavantageResponse {
    @JsonProperty("Meta Data")
    MetaData metaData ;


    @JsonProperty("Time Series (Daily)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    HashMap<Date, Stock> timeSeries ;

}
