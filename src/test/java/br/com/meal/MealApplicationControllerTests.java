package br.com.meal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.meal.controller.MealController;

/**
 * Teste de execução do Controller da aplicação.
 * 
 * @author Daryan Avi
 */
@SpringBootTest
class MealApplicationControllerTests {

	@Autowired
	private MealController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
}