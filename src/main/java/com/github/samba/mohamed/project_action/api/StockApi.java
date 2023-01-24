package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.model.SearchHistory;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import com.github.samba.mohamed.project_action.repository.SearchHistotyRepository;
import com.github.samba.mohamed.project_action.service.SearchHistoryService;
import com.github.samba.mohamed.project_action.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "https://front-action-safe.azurewebsites.net/",allowCredentials="true")
@RestController
@AllArgsConstructor
@RequestMapping("/api/stocks")
public class StockApi {
    private StockService stockService;
    private SearchHistoryService searchHistoryService;
    private SearchHistotyRepository sRepo;



    @GetMapping("/getStockByDay")
    public Stock getStockByDay(@RequestParam String symbol,@RequestParam String date){
        return stockService.getStockByDay(symbol,date);
    }
    @GetMapping("/getStockByDayWithHoursData")
    public List<Stock> getStockByDayWithHoursData(@RequestParam String symbol,@RequestParam String stockName,@RequestParam String date){
        searchHistoryService.saveSearchHistory(symbol,stockName);
        return stockService.getStockByIntraDay(symbol,date);
    }
    @GetMapping("/getStockLastDay")
    public Stock getStockLastDay(@RequestParam String symbol){
        return stockService.getStockLastDay(symbol);
    }
    @GetMapping("/getStockByRange")
    public Collection<Stock> getStockByRange(@RequestParam String symbol, @RequestParam String stockName, @RequestParam String dateStart, @RequestParam String dateEnd){
        HashMap<Date,Stock> map =stockService.getStockByRangeDate(symbol,dateStart,dateEnd);
        searchHistoryService.saveSearchHistory(symbol,stockName);
        return map.values();
    }



    @GetMapping("/getAllStock")
    public List<StockA> getAllStock(){
        return stockService.getAllStock();
    }

    @GetMapping("/getSearchHistory")
    public List<SearchHistory> getSearchHistory(){
        searchHistoryService = new SearchHistoryService(sRepo);
        return searchHistoryService.getSearchHistory();
    }
}
