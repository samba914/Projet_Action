package com.github.samba.mohamed.project_action;

import com.github.samba.mohamed.project_action.api.AccountApi;
import com.github.samba.mohamed.project_action.api.StockApi;
import com.github.samba.mohamed.project_action.model.Account;
import com.github.samba.mohamed.project_action.model.Action;
import com.github.samba.mohamed.project_action.model.SearchHistory;
import com.github.samba.mohamed.project_action.payload.request.BalanceRequest;
import com.github.samba.mohamed.project_action.payload.response.Stock;
import com.github.samba.mohamed.project_action.payload.response.StockA;
import com.github.samba.mohamed.project_action.repository.AccountRepository;
import com.github.samba.mohamed.project_action.repository.SearchHistotyRepository;
import com.github.samba.mohamed.project_action.service.AccountService;
import com.github.samba.mohamed.project_action.service.SearchHistoryService;
import com.github.samba.mohamed.project_action.service.StockService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProjectActionApplicationTests {
	@Mock
	private SearchHistotyRepository searchHistotyRepository;
	@Mock
	private SearchHistoryService searchHistoryService;
	@Mock
	private AccountService accountService;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private StockService stockService;

	private StockApi stockApi;
	private AccountApi accountApi;

	private EasyRandom random;

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
		random= new EasyRandom();
		accountApi = new AccountApi(accountService);
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



}
