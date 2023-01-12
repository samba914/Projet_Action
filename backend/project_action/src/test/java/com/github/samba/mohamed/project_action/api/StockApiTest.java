package com.github.samba.mohamed.project_action.api;

import com.github.samba.mohamed.project_action.model.SearchHistory;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockApiTest {

    @Mock
    StockApi stockApi ;
    EasyRandom random = new EasyRandom() ;

    @Test
    void getStockByDay() {
        Stock stock = random.nextObject(Stock.class) ;
        String symbol = random.nextObject(String.class) ;
        String date = random.nextObject(String.class) ;
        Mockito.when(stockApi.getStockByDay(symbol,date)).thenReturn(stock) ;
        Assert.assertEquals(stockApi.getStockByDay(symbol,date),stock);

    }

    @Test
    void getStockByDayWithHoursData() {
        List<Stock> stocks = random.objects(Stock.class,10).toList() ;
        String symbol = random.nextObject(String.class) ;
        String date = random.nextObject(String.class) ;
        String stockName = random.nextObject(String.class) ;
        Mockito.when(stockApi.getStockByDayWithHoursData(symbol,stockName,date)).thenReturn(stocks) ;
        Assert.assertEquals(stockApi.getStockByDayWithHoursData(symbol,stockName,date),stocks);
    }

    @Test
    void getStockLastDay() {
        Stock stock = random.nextObject(Stock.class) ;
        String symbol = random.nextObject(String.class) ;
        Mockito.when(stockApi.getStockLastDay(symbol)).thenReturn(stock) ;
        Assert.assertEquals(stockApi.getStockLastDay(symbol),stock);
    }

    @Test
    void getStockByRange() {
        List<Stock> stocks = random.objects(Stock.class,10).toList() ;
        String symbol = random.nextObject(String.class) ;
        String date1 = random.nextObject(String.class) ;
        String date2 = random.nextObject(String.class) ;
        String stockName = random.nextObject(String.class) ;
        Mockito.when(stockApi.getStockByRange(symbol,stockName,date1,date2)).thenReturn(stocks) ;
        Assert.assertEquals(stockApi.getStockByRange(symbol,stockName,date1,date2),stocks);
    }

    @Test
    void getAllStock() {
        List<StockA> stocks = random.objects(StockA.class,10).toList() ;
        Mockito.when(stockApi.getAllStock()).thenReturn(stocks) ;
        Assert.assertEquals(stockApi.getAllStock(),stocks);
    }

    @Test
    void getSearchHistory() {
        List<SearchHistory> searchHistories = random.objects(SearchHistory.class,10).toList() ;
        Mockito.when(stockApi.getSearchHistory()).thenReturn(searchHistories) ;
        Assert.assertEquals(stockApi.getSearchHistory(),searchHistories);
    }
}