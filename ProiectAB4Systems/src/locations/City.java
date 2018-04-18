package locations;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that models a city. It contains all the locations
 * available for visiting within that city.
 * 
 * @author Gabriel Bercaru
 */
public class City {
	private String name;
	private Set<Location> locations;
	
	public City() {
		this.name = null;
		locations = new HashSet<>();
	}
	
	public City(String name) {
		this.name = name;
		locations = new HashSet<>();
	}

	public City(Set<Location> locations) {
		this.name = null;
		this.locations = locations;
	}

	public void addLocation(Location location) {
		locations.add(location);
	}

	public boolean removeLocation(Location location) {
		return locations.remove(location);
	}

	@Override
	public String toString() {
		return name;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public Set<Location> getLocations() {
		return locations;
	}
	
}
