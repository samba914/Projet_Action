package com.github.samba.mohamed.project_action.service;

import com.github.samba.mohamed.project_action.payload.response.AlphavantageResponse;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
public class StockService {
    private static final String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=%s&apikey=1XDCVS7DM3C1XE20";  ;
    public static Stock getStockByDay(String symbol, String date){
        try {
            Date dateOne = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            HashMap<Date, Stock> stocks = alphaAPI(symbol).getTimeSeries();
            for(Date d : stocks.keySet()){
                if (DateUtils.isSameDay(d,dateOne)){
                    return stocks.get(d);
                }
            }
        }
        catch (Exception e) {
            System.err.println("error in getStockByDay");
        }
        return  null ;
    }
    public static HashMap<Date,Stock> getStockByRangeDate(String symbol, String date1, String date2){
        try{
            Date dateOne = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            Date dateTwo = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
            HashMap<Date, Stock> stocks  = alphaAPI( symbol).getTimeSeries();
            Iterator it = stocks.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry value = (Map.Entry)it.next();

                if( (dateOne.after((Date) value.getKey()) || dateTwo.before((Date) value.getKey())) && ( (!DateUtils.isSameDay( (Date) value.getKey(),dateOne)) && (!DateUtils.isSameDay( (Date) value.getKey(),dateTwo))  )  ){
                    it.remove();
                }
            }
            return  stocks ;
        }
        catch(NullPointerException err1 ) {
            System.err.println("error in getStockByRangeDate : "+err1);
        }
        catch (IllegalArgumentException err2){
            System.err.println("error in getStockByRangeDate : "+err2);
        }
        catch (ParseException err3){
            System.err.println("error in getStockByRangeDate : "+err3);
        }

        return null ;

    }

    public static AlphavantageResponse alphaAPI(String symbol){
        String url = String.format(URL,symbol) ;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AlphavantageResponse> response = restTemplate.getForEntity(url,AlphavantageResponse.class);
        return response.getBody();
    }

    public static void main(String[] args) {
        String date1 = "2022-12-13" ;
        String date2 = "2022-12-17" ;
        //System.out.println(getStockByDay("IBM", "2022-12-16"));
        System.out.println(getStockByRangeDate("IBM",date1, date2).size());
    }

}
