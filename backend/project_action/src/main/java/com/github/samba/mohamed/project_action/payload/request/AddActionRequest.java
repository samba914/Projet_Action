package com.github.samba.mohamed.project_action.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddActionRequest {
    private String stockName;
    private String stockSymbol;
    private float stockQuantity;
    private float purchasePrice;
}
