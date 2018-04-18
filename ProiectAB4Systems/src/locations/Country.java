package locations;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that models a country. One country may
 * contain more counties.
 * 
 * @author Gabriel Bercaru
 */
public class Country {
	private String name;
	private Set<County> counties;
	
	public Country() {
		this.name = null;
		counties = new HashSet<>();
	}
	
	public Country(String name) {
		this.name = name;
		counties = new HashSet<>();
	}
	
	public Country(Set<County> counties) {
		this.name = null;
		this.counties = counties;
	}
	
	public void addCounty(County county) {
		counties.add(county);
	}
	
	public boolean removeCounty(County county) {
		return counties.remove(county);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public Set<County> getCounties() {
		return counties;
	}
}
