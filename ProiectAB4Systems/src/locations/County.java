package locations;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that models a county. It contains all the cities
 * registered in that county (and implicitly all the locations
 * available for visiting inside the county).
 * 
 * @author Gabriel Bercaru
 */
public class County {
	private String name;
	private Set<City> cities;
	
	public County() {
		this.name = null;
		cities = new HashSet<>();
	}
	
	public County(Set<City> cities) {
		this.name = null;
		this.cities = cities;
	}
	
	public County(String name) {
		this.name = name;
		this.cities = new HashSet<>();
	}
	
	public void addCity(City city) {
		cities.add(city);
	}
	
	public boolean removeCity(City city) {
		return cities.remove(city);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public Set<City> getCities() {
		return cities;
	}
}
