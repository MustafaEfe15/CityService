package tr.mustafaefe.CityService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Indexed;

import tr.mustafaefe.CityService.model.City;

public interface ICityRepository extends MongoRepository<City, String> {

	List<City> findByName(String name);

}
