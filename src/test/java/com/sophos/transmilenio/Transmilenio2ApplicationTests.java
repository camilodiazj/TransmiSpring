package com.sophos.transmilenio;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.sophos.transmilenio.beans.Estacion;
import com.sophos.transmilenio.controllers.EstacionController;
import com.sophos.transmilenio.controllers.TroncalController;

@SpringBootTest
class Transmilenio2ApplicationTests {
	
	
	@InjectMocks
	EstacionController controller;
	TroncalController tController;
	



}
