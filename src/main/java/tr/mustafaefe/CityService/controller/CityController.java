package tr.mustafaefe.CityService.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import tr.mustafaefe.CityService.model.City;

@RestController
@RequestMapping("/cities")
public class CityController {

	private static final List<City> allCities = new ArrayList<City>();

	public CityController() {
		City burdur = new City("15", "Burdur", new Date());
		City bursa = new City("16", "Bursa", new Date());
		City istanbul = new City("34", "İstanbul", new Date());
		
		allCities.add(burdur);
		allCities.add(bursa);
		allCities.add(istanbul);
	}

	@GetMapping
	@JsonView(City.class)
	public ResponseEntity<List<City>> getCities() {
		return new ResponseEntity<>(allCities, OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<City> getCityById(@PathVariable String id) {
		City result = findCityById(id);

		return new ResponseEntity<City>(result, OK);
	}

	@PostMapping
	@JsonView(City.class)
	public ResponseEntity createCity(@RequestBody City newCity) {
		newCity.setCreatedDate(new Date());
		allCities.add(newCity);
		
		return new ResponseEntity<City>(newCity, CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> replaceCityById(@PathVariable String id, @RequestBody City newCity) {
		City result = findCityById(id);
		result.setName(newCity.getName());
		result.setCreatedDate(new Date());
		
		return new ResponseEntity<Void>(OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCity(@PathVariable String id) {
		City result = findCityById(id);
		allCities.remove(result);
		
		return new ResponseEntity<Void>(OK);
	}
	
	private City findCityById(String id) {
		return allCities.stream()
				.filter(city -> city.getId()
				.equals(id)).findFirst()
				.orElseThrow(() -> new RuntimeException("City not found!"));
	}
}
