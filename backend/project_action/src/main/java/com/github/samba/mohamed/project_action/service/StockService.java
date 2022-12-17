package com.github.samba.mohamed.project_action.service;

import com.github.samba.mohamed.project_action.payload.response.Stock;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@NoArgsConstructor
public class StockService {
    public Stock getStockByDay(String symbol, String date){
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol="+symbol+"&apikey=1XDCVS7DM3C1XE20";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseTeam = restTemplate.getForEntity(url,Object.class);
        Object bodyTeam  = responseTeam.getBody();
        return null;
        //List<MatchInfo> matchInfosTeam = bodyTeam.getData();
    }
}
