package com.github.samba.mohamed.project_action.payload.response;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class AlphavantageResponseIntraDay {
    @JsonProperty("Meta Data")
    MetaData metaData ;


    @JsonAlias("Time Series (60min)")
    HashMap<String, Stock> timeSeries ;

}
