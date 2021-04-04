/**
 * 31 de mar. de 2021
 */
package br.com.meal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.meal.service.MealService;

/**
 * @author Daryan Avi
 *
 */
@Controller
public class MealController {
	
	@Autowired
	private MealService service;
	
	@GetMapping("/")
	public String index(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer start,
			Model model) {
		try {
			service.requestSearch(name);
			
			final int mealsPerPage = 10;
			final int displayedPages = 5;
			
			int totalPages = (int)Math.ceil((double)service.getMeals().size() / mealsPerPage);
			int currentPage = start != null ? start : 1;
			int firstPage = totalPages <= displayedPages ? 1 : Math.max(1, Math.min(totalPages - displayedPages + 1, currentPage - (int)displayedPages / 2));
			int lastPage = Math.min(firstPage + displayedPages - 1, totalPages);
			int interval = currentPage * mealsPerPage;
			
			model.addAttribute("meals", service.getMeals().subList(interval - mealsPerPage, Math.min(interval, service.getMeals().size())));
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("firstPage", firstPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("totalPages", totalPages);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "home";
	}
}