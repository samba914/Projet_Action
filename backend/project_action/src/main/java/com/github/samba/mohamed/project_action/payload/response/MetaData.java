package com.github.samba.mohamed.project_action.payload.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaData {
    @JsonProperty("1. Information")
    String information ;
    @JsonProperty("2. Symbol")
    String symbol ;
    @JsonProperty("3. Last Refreshed")
    String lastRefreshed ;
    @JsonProperty("4. Output Size")
    @JsonAlias("5. Output Size")
    String outputSize ;
    @JsonProperty("5. Time Zone")
    @JsonAlias("6. Time Zone")
    String  timeZone ;
    @JsonProperty("4. Interval")
    String interval ;
}
