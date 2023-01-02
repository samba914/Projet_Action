package com.github.samba.mohamed.project_action.payload.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @JsonProperty("1. open")
    String open ;
    @JsonProperty("2. high")
    String high ;
    @JsonProperty("3. low")
    String low ;
    @JsonProperty("4. close")
    String close ;
    @JsonProperty("5. adjusted close")
    String adjustedClose ;
    @JsonProperty( "6. volume")
    @JsonAlias("5. volume")
    String volume ;
    @JsonProperty("7. dividend amount")
    String dividendAmount ;
    @JsonProperty("8. split coefficient")
    String splitCoefficient ;

    Date date;

}
