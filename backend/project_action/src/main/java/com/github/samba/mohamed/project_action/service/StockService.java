package com.github.samba.mohamed.project_action.service;


import com.github.samba.mohamed.project_action.payload.response.AlphavantageResponse;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class StockService {
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
    public Stock getStockLastDay(String symbol){
        try {
            HashMap<Date, Stock> stocks = alphaAPI(symbol).getTimeSeries();
            TreeMap<Date, Stock>a = new  TreeMap<Date, Stock>();
            TreeMap<Date,Stock> s= stocks.entrySet() .stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, TreeMap::new));
            return s.firstEntry().getValue();
        }
        catch (Exception e) {
            System.err.println("error in getStockByDay");
        }
        return  null ;
    }
    public HashMap<Date,Stock> getStockByRangeDate(String symbol, String date1, String date2){
        try{
            Date dateOne = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
            Date dateTwo = new SimpleDateFormat("dd/MM/yyyy").parse(date2);
            HashMap<Date, Stock> stocks  = alphaAPI( symbol).getTimeSeries();
            Iterator it = stocks.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry value = (Map.Entry)it.next();

                if( (dateOne.after((Date) value.getKey()) || dateTwo.before((Date) value.getKey())) && ( (!DateUtils.isSameDay( (Date) value.getKey(),dateOne)) && (!DateUtils.isSameDay( (Date) value.getKey(),dateTwo))  )  ){
                    it.remove();
                }else{
                    Stock a = (Stock)value.getValue();
                    a.setDate((Date) value.getKey());
                    value.setValue(a);
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
        String URL  = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=%s&apikey=1XDCVS7DM3C1XE20";  ;
        String url = String.format(URL,symbol) ;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AlphavantageResponse> response = restTemplate.getForEntity(url,AlphavantageResponse.class);
        return response.getBody();
    }

    public  List<StockA> getAllStock(){
        String u_url= "https://www.alphavantage.co/query?function=LISTING_STATUS&state=active&apikey=1XDCVS7DM3C1XE20";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(u_url,String.class);
        String o =  response.getBody();
        String input = "1,2\n3,4";

        StringReader reader = new StringReader(o);

            BeanListProcessor<StockA> rowProcessor = new BeanListProcessor<StockA>(StockA.class);
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            settings.setProcessor(rowProcessor);
            CsvParser parser = new CsvParser(settings);
            parser.parse(reader);
        return rowProcessor.getBeans();
    }


   /* public static void main(String[] args) {
        String date1 = "2022-12-13" ;
        String date2 = "2022-12-17" ;
        System.out.println(getStockLastDay("IBM"));
        //System.out.println(getStockByRangeDate("IBM",date1, date2).size());
    }*/

}
