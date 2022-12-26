package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import com.github.samba.mohamed.project_action.service.StockService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081",allowCredentials="true")
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/stocks")
public class StockApi {
    private StockService stockService;

    @GetMapping("/all")
    public String getTest(){
        return  "test";
    }

    @GetMapping("/getStockByDay")
    public Stock getStockByDay(@RequestParam String symbol,@RequestParam String date){
        stockService.getStockByDay(symbol,date);
        return null;
    }
    @GetMapping("/getStockLastDay")
    public Stock getStockLastDay(@RequestParam String symbol){
        return stockService.getStockLastDay(symbol);
    }
    @GetMapping("/getStockByRange")
    public Collection<Stock> getStockByRange(@RequestParam String symbol, @RequestParam String stockName, @RequestParam String dateStart, @RequestParam String dateEnd){
        HashMap<Date,Stock> map =stockService.getStockByRangeDate(symbol,dateStart,dateEnd);
        return map.values();
    }

    @GetMapping("/getAllStock")
    public List<StockA> getAllStock(){
        stockService = new StockService();
        return stockService.getAllStock();
    }
}
