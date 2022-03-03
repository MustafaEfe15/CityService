package tr.mustafaefe.CityService.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import tr.mustafaefe.CityService.exception.CityNotFoundException;
import tr.mustafaefe.CityService.model.City;
import tr.mustafaefe.CityService.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	private final CityService cityService;

	public CityController(CityService ser) {
		this.cityService = ser; 
	}

	@GetMapping
	@JsonView(City.class)
	public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name) {
		return new ResponseEntity<>(cityService.getCities(name), OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> getCityById(@PathVariable String id) {
		return new ResponseEntity<City>(findCityById(id), OK);
	}

	@PostMapping()
	@JsonView(City.class)
	public ResponseEntity<City> createCity(@RequestBody City newCity) {
		return new ResponseEntity<City>(cityService.createCity(newCity), CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> replaceCityById(@PathVariable String id, @RequestBody City newCity) {
		cityService.updateCity(id, newCity);
		 
		return new ResponseEntity<Void>(OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCity(@PathVariable String id) {
		cityService.deleteIl(id);
		
		return new ResponseEntity<Void>(OK);
	}
	
	private City findCityById(String id) {
		return cityService.getCityById(id);
	}
	
	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<String> handleCityNotFoundException(CityNotFoundException exc) {
		return new ResponseEntity<>(exc.getMessage(), NOT_FOUND);
	}
}
