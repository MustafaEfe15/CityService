package tr.mustafaefe.CityService.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tr.mustafaefe.CityService.exception.CityNotFoundException;
import tr.mustafaefe.CityService.model.City;
import tr.mustafaefe.CityService.repository.ICityRepository;

@Service
public class CityService {

	private final ICityRepository cityRepository;
	
	public CityService(ICityRepository rep) {
		this.cityRepository = rep;
	}
	
	public List<City> getCities(String name) {
		return name == null ? cityRepository.findAll() : cityRepository.findByName(name);
	}

	public City createCity(City newCity) {
		newCity.setCreatedDate(new Date());
		return cityRepository.insert(newCity);
	}

	public void deleteIl(String id) {
		cityRepository.deleteById(id);
	}

	public City getCityById(String id) {
		return cityRepository.findById(id)
				.orElseThrow(() -> new CityNotFoundException("City not found with id: " + id));
	}

	public void updateCity(String id, City newCity) {
		City oldCity = getCityById(id);
		oldCity.setName(newCity.getName());
		cityRepository.save(newCity);
		
	}
}
