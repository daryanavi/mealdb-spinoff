package br.com.meal;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.meal.service.MealService;

/**
 * Teste de execução do Service da aplicação, passando três parâmetros diferentes para o método requestSearch.
 * 
 * @author Daryan Avi
 */
@SpringBootTest
class MealApplicationServiceTests {

	@Autowired
	private MealService service;
	
	@Test
	void emptySearch() throws IOException {
		service.requestSearch(null);
		
		assertThat(service.getMeals()).isNotEmpty();
	}
	
	@Test
	void oneLetterSearch() throws IOException {
		service.requestSearch("a");
		
		assertThat(service.getMeals()).isNotEmpty();
	}
	
	@Test
	void randomLettersSearch() throws IOException {
		service.requestSearch("aaisdoaisdijhudsajhsdxashdsaxasdxjhnasxdhnndjhskckjhasdcdsakjh");
		
		assertThat(service.getMeals()).isEmpty();
	}
	
}