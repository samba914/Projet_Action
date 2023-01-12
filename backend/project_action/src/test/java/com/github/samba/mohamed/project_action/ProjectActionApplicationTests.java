package com.github.samba.mohamed.project_action;

import com.github.samba.mohamed.project_action.api.AccountApi;
import com.github.samba.mohamed.project_action.api.StockApi;
import com.github.samba.mohamed.project_action.model.SearchHistory;
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
import java.util.stream.Collectors;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProjectActionApplicationTests {
	@Test
	public void test(){

	}


}
