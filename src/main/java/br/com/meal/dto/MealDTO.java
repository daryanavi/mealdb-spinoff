/**
 * 2 de abr. de 2021
 */
package br.com.meal.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Daryan Avi
 *
 */
@Component
@Data
public class MealDTO {

	private int id;
	private String name, category, area, thumbnail, instructions;

}