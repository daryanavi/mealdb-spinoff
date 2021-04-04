/**
 * 2 de abr. de 2021
 */
package br.com.meal.service;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meal.dto.MealDTO;
import lombok.Getter;

/**
 * @author Daryan Avi
 *
 */
@Service
public class MealService {

	private final String MEALDB_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
	
	@Autowired
	@Getter
	private List<MealDTO> meals;
	private String lastName;
	
	public void requestSearch(String name) throws IOException {
		if (name == null) {
			name = "";
		}
		
		if (name.equals(lastName)) {
			return;
		}
		
		lastName = name;
		
		URL url = new URL(MEALDB_URL + name);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		
		DataInputStream data = new DataInputStream(conn.getInputStream());
		String s = new String(IOUtils.toByteArray(data));
		data.close();
		
		meals = new ArrayList<>();
		
		JSONObject jObj = new JSONObject(s);
		
		if (jObj.isNull("meals")) {
			return;
		}
		
		JSONArray jArray = jObj.getJSONArray("meals");
		
		for (int i = 0; i < jArray.length(); i ++) {
			MealDTO meal = new MealDTO();
			JSONObject jMeal = (JSONObject)jArray.get(i);
			
			meal.setId(jMeal.getInt("idMeal"));
			meal.setName(jMeal.getString("strMeal"));
			meal.setCategory(jMeal.getString("strCategory"));
			meal.setArea(jMeal.getString("strArea"));
			meal.setThumbnail(jMeal.getString("strMealThumb"));
			meal.setInstructions(jMeal.getString("strInstructions").trim());
			
			meals.add(meal);
		}
	}

}