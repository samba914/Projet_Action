package com.github.samba.mohamed.project_action.service;

import com.github.samba.mohamed.project_action.payload.response.AlphavantageResponse;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
    @Mock
    StockService stockService ;
    EasyRandom random = new EasyRandom();


    @Test
    void getStockByDay() {
        Stock s = random.nextObject(Stock.class);
        Mockito.when(stockService.getStockByDay("IBM","01/01/2023")).thenReturn(s) ;
        Assert.assertEquals(stockService.getStockByDay("IBM","01/01/2023"),s);

    }

    @Test
    void getStockLastDay() {
        Stock s = random.nextObject(Stock.class);
        Mockito.when(stockService.getStockLastDay("IBM")).thenReturn(s) ;
        Assert.assertEquals(stockService.getStockLastDay("IBM"),s);
    }

    @Test
    void getStockByRangeDate() {
        Stock s = random.nextObject(Stock.class) ;
        Date d = random.nextObject(Date.class) ;
        HashMap<Date,Stock> mapStocks = new HashMap<>() ;
        mapStocks.put(d,s) ;
        Mockito.when(stockService.getStockByRangeDate("IBM","01/01/2023","01/01/2023")).thenReturn(mapStocks) ;
        Assert.assertEquals(stockService.getStockByRangeDate("IBM","01/01/2023","01/01/2023"),mapStocks);
    }


    @Test
    void getAllStock() {
        List<StockA> stocks = random.objects(StockA.class,10).toList() ;
        Mockito.when(stockService.getAllStock()).thenReturn(stocks) ;
        Assert.assertEquals(stockService.getAllStock(), stocks);
    }



    @Test
    void getStockByIntraDay() {
        List<Stock> stocks = random.objects(Stock.class,10).toList() ;
        Mockito.when(stockService.getStockByIntraDay("IBM","01/01/2023")).thenReturn(stocks) ;
        Assert.assertEquals(stockService.getStockByIntraDay("IBM","01/01/2023"), stocks);
    }
}