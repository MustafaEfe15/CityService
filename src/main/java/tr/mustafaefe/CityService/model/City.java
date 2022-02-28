package tr.mustafaefe.CityService.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class City {

	@JsonView(City.class)
	private String id;
	@JsonView(City.class)
	private String name;
	@JsonView(City.class)
	private Date createdDate;

	public City(String id, String name, Date createdDate) {
		super();
		this.id = id;
		this.name = name;
		this.createdDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
