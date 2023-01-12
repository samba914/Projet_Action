package com.github.samba.mohamed.project_action.service;


import com.github.samba.mohamed.project_action.model.SearchHistory;
import com.github.samba.mohamed.project_action.payload.response.AlphavantageResponse;
import com.github.samba.mohamed.project_action.payload.response.AlphavantageResponseIntraDay;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StockService {
    public Stock getStockByDay(String symbol, String date){
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
        StringReader reader = new StringReader(o);

            BeanListProcessor<StockA> rowProcessor = new BeanListProcessor<StockA>(StockA.class);
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            settings.setProcessor(rowProcessor);
            CsvParser parser = new CsvParser(settings);
            parser.parse(reader);
        return rowProcessor.getBeans();
    }

    public static AlphavantageResponseIntraDay alphaAPIIntraDay(String symbol){
        String URL  = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=60min&apikey=1XDCVS7DM3C1XE20";  ;
        String url = String.format(URL,symbol) ;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AlphavantageResponseIntraDay> response = restTemplate.getForEntity(url,AlphavantageResponseIntraDay.class);
        return response.getBody();

    }

    public  List<Stock> getStockByIntraDay(String symbol, String date){
        try {
            Stock tmp ;
            List<Stock> stockIntraDay = new ArrayList<>();
            HashMap<String, Stock> stocks = alphaAPIIntraDay(symbol).getTimeSeries();
            Date dateObject = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            for(String s : stocks.keySet()){
                if (dateObject.equals(new SimpleDateFormat("yyyy-MM-dd").parse(s.split(" ")[0]))){
                    tmp = stocks.get(s) ;
                    tmp.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s));
                    stockIntraDay.add(tmp);
                }
            }
            return stockIntraDay ;
        }
        catch (Exception e) {
            System.err.println("error in getStockByDay : "+ e);
        }
        return  null ;
    }


//   public static void main(String[] args) {
//        StockService z = new StockService();
//        String date1 = "27/12/2022" ;
//        String date2 = "30/12/2022" ;
//       List<Stock> s = getStockByIntraDay("IBM",date1) ;
//        System.out.println(s);
//       System.out.println(s.size());
//        for (Stock k : s){
//            System.out.println(k.getDate());
//        }
//       //System.out.println(z.getStockByRangeDate("IBM",date1, date2).size());
//    }

}
