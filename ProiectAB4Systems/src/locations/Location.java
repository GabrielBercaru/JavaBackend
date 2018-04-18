package locations;
import java.util.Date;
import java.util.Set;

/**
 * Class that contains all the information needed to
 * uniquely identify a holiday location.
 * 
 * @author Gabriel Bercaru
 */
public class Location implements Comparable<Location> {
	private Integer id;
	private String name;
	private City city;
	private County county;
	private Country country;
	private Float avgPrice;
	private Set<String> activities;
	private Date startDate;
	private Date endDate;
	private Float totalCost;
	private Integer numDays;
	
	public Location(Integer id, String name, City city, County county,
					Country country, Float avgPrice,
					Set<String> activities, Date startDate,
					Date endDate) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.county = county;
		this.country = country;
		this.avgPrice = avgPrice;
		this.activities = activities;
		this.startDate = startDate;
		this.endDate = endDate;
		
		numDays = (int) (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
		totalCost = avgPrice * numDays;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(id + " | ");
		sb.append(name + " | ");
		sb.append(city + " | ");
		sb.append(county + " | ");
		sb.append(country + " | ");
		sb.append(avgPrice + " | ");
		sb.append("[  ");
		for (String activity : activities) {
			sb.append(activity + "  ");
		}

		sb.append("] | ");
		sb.append(startDate + " | ");
		sb.append(endDate);
		
		return sb.toString();
	}
	
	/**
	 * Computes and returns the total cost needed to spend some days
	 * in this location.
	 * 
	 * @param startDate Start date of the interval to consider
	 * @param endDate End date of the interval to consider
	 * @return total cost between given dates
	 */
	public float totalCostBetweenDates(Date startDate, Date endDate) {
		numDays = (int) (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
		
		return avgPrice * numDays;
	}
	
	/**
	 * Computes and returns the total cost needed to spend the given
	 * number of days in this location.
	 * 
	 * @param numDays Number of days to be spent in this location
	 * @return total cost for the given number of days
	 */
	public float totalCostForKDays(int numDays) {
		return avgPrice * numDays;
	}
	
	/**
	 * Since the locations are stored inside a TreeSet structure,
	 * compareTo needs to be override so the entries will be sorted. 
	 */
	@Override
	public int compareTo(Location other) {
		return this.id - other.getId();
	}
	
	/**
	 * Override the default equals() method to properly compare
	 * whether two locations are the same or not.
	 */
	@Override
	public boolean equals(Object o) {
		Location other = (Location) o;
		
		boolean equalActivities = true;
		
		Object[] theseActivities = activities.toArray();
		Object[] thoseActivities = other.getActivities().toArray();
		
		for (int i = 0; i < theseActivities.length; i++) {
			equalActivities &= theseActivities[i].equals(thoseActivities[i]);
		}
		
		return id.equals(other.getId()) && name.equals(other.getName())
				&& city.equals(other.getCity()) && county.equals(other.getCounty())
				&& country.equals(other.getCountry())
				&& avgPrice.equals(other.getAvgPrice())
				&& equalActivities
				&& startDate.equals(other.getStartDate())
				&& endDate.equals(other.getEndDate());
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Float getTotalCost() {
		return totalCost;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Float getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Float avgPrice) {
		this.avgPrice = avgPrice;
	}

	public Set<String> getActivities() {
		return activities;
	}

	public void setActivities(Set<String> activities) {
		this.activities = activities;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
