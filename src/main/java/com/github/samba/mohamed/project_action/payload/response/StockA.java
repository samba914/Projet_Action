package com.github.samba.mohamed.project_action.payload.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.univocity.parsers.annotations.Parsed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockA {
    @Parsed(field = "symbol")
    private String symbol;
    @Parsed(field = "name")
    private String name;
    @Parsed(field = "exchange")
    private String exchange;
    @Parsed(field = "assetType")
    private String assetType;
    @Parsed(field = "ipoDate")
    private String ipoDate;
    @Parsed(field = "delistingDate")
    private String delistingDate;
    @Parsed(field = "status")
    private String status;
}
