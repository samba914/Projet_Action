package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.model.SearchHistory;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import com.github.samba.mohamed.project_action.repository.AccountRepository;
import com.github.samba.mohamed.project_action.repository.SearchHistotyRepository;
import com.github.samba.mohamed.project_action.service.AccountService;
import com.github.samba.mohamed.project_action.service.SearchHistoryService;
import com.github.samba.mohamed.project_action.service.StockService;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockApiTest {

    @Mock
    private SearchHistotyRepository searchHistotyRepository;
    @Mock
    private SearchHistoryService searchHistoryService;
    @Mock
    private StockService stockService;

    private StockApi stockApi;

    private EasyRandom random;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        random= new EasyRandom();
        stockApi = new StockApi(stockService,searchHistoryService,searchHistotyRepository);
    }

    @Test
    public void shoulGetSearchHistory(){
        List<SearchHistory> history = random.objects(SearchHistory.class,10).collect(Collectors.toList());
        Mockito.when(searchHistotyRepository.findAll()).thenReturn(history);
        List<SearchHistory> output = stockApi.getSearchHistory();
        Assertions.assertEquals(history,output);
    }

    @Test
    public void shoulGetAllStock(){
        List<StockA> listStock = random.objects(StockA.class,10).collect(Collectors.toList());
        Mockito.when(stockService.getAllStock()).thenReturn(listStock);
        List<StockA> output = stockApi.getAllStock();
        Assertions.assertEquals(listStock,output);
    }

    @Test
    void getStockByDay() {
        Stock stock = random.nextObject(Stock.class) ;
        String symbol = "IBM" ;
        String date = "04/01/2023";
        Mockito.when(stockService.getStockByDay(symbol,date)).thenReturn(stock) ;
        Assert.assertEquals(stockApi.getStockByDay(symbol,date),stock);

    }


    @Test
    void getStockByDayWithHoursData() {
        List<Stock> stocks = random.objects(Stock.class,10).toList() ;
        String symbol = "IBM" ;
        String date = "04/01/2023";
        String stockName = "International Business Machines Corp" ;
        Mockito.when(stockService.getStockByIntraDay(symbol,date)).thenReturn(stocks) ;
        Assert.assertEquals(stockApi.getStockByDayWithHoursData(symbol,stockName,date),stocks);
    }

    @Test
    void getStockLastDay() {
        Stock stock = random.nextObject(Stock.class) ;
        String symbol = "IBM";
        Mockito.when(stockService.getStockLastDay(symbol)).thenReturn(stock) ;
        Assert.assertEquals(stockApi.getStockLastDay(symbol),stock);
    }

    @Test
    void getStockByRange() {
        HashMap<Date,Stock> stocks = new HashMap<>();
        String symbol = "IBM" ;
        String date1 = "01/01/2023";
        String date2 = "12/01/2023";
        String stockName = "International Business Machines Corp" ;
        Mockito.when(stockService.getStockByRangeDate(symbol,date1,date2)).thenReturn(stocks) ;
        Assert.assertEquals(stockApi.getStockByRange(symbol,stockName,date1,date2),stocks.values());
    }


}