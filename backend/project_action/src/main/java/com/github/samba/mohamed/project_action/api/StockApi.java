package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/stocks")
public class StockApi {
    private StockService stockService;

    @GetMapping("/all")
    public String getTest(){
        return  "test";
    }

    @GetMapping("/getStockByDay")
    public Stock getStockByDay(@RequestParam String symbol,@RequestParam String date){
        stockService= new StockService();
        stockService.getStockByDay(symbol,date);
        return null;
    }
}
