package solver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import locations.Location;
import parser.Parser;

/**
 * Class used for testing the functionality of the input file parser.
 * 
 * @author Gabriel Bercaru
 */
public class Solver {
	private Parser parser;
	private Map<String, Location> locations;
	
	public Solver(String inputFilename) throws IOException {
		initializeReferences(inputFilename);
		parser.readAllEntries(locations);
	}

	/**
	 * Private method to instantiate inner class members
	 */
	private void initializeReferences(String inputFilename) {
		parser = new Parser(inputFilename);
		locations = new TreeMap<>();
	}
	
	/**
	 * Public method to answer the first type of queries.
	 * 
	 * Get all the entries which match the given attribute
	 * @param attribute What attribute should be used for matching
	 * @param value Value of the attribute to match on
	 * @return A list of locations which matched the given value
	 */
	public List<Location> getByAttribute(String attribute, String value) {
		List<Location> locs = null;
		
		if (attribute.equals("City")) {
			locs = locations.values().parallelStream()
				.filter(location->location.getCity().getName().equals(value))
				.collect(Collectors.toList());
		} else if (attribute.equals("County")) {
			locs = locations.values().parallelStream()
				.filter(location->location.getCounty().getName().equals(value))
				.collect(Collectors.toList());
		} else if (attribute.equals("Country")) {
			locs = locations.values().parallelStream()
				.filter(location->location.getCountry().getName().equals(value))
				.collect(Collectors.toList());
		}
		
		return locs;
	}
	
	/**
	 * Public method to answer the second type of queries.
	 * 
	 * Gets the cheapest k entries (according to total expenses between startDate and endDate),
	 * which match the given value.
	 * 
	 * @param attribute What attribute should be used for matching
	 * @param value Value of the attribute to match on
	 * @param k How many days should be returned in the answer
	 * @param startDate Lower bound of the time interval which will contain the returned entries
	 * @param endDate Upper bound of the time interval which will contain the returned entries
	 * @return A list of at most k elements containing the cheapest destinations
	 */
	public List<Location> topKDestinationsByTotalPrice(String attribute, String value, int k,
			Date startDate, Date endDate) {
		List<Location> locs = getByAttribute(attribute, value);
		
		Collections.sort(locs, new Comparator<Location>() {
			@Override
			public int compare(Location l1, Location l2) {
				return (int) l2.totalCostBetweenDates(startDate, endDate)
						- (int) l1.totalCostBetweenDates(startDate, endDate);
			}
		});
		
		return k > locs.size() ? locs : locs.subList(0, k);
	}
	
	/**
	 * Public method to answer the third type of queries.
	 * 
	 * Gets the cheapest entry for a k-days trip.
	 * 
	 * @param k Number of days the customer is planning to spend
	 * @return A list with the cheapest locations according to the given duration
	 */
	public List<Location> cheapestKDaysDestination(int k) {
		List<Location> locs = locations.values().parallelStream().collect(Collectors.toList());
		
		Collections.sort(locs, new Comparator<Location>() {
			public int compare(Location l1, Location l2) {
				return (int) l2.totalCostForKDays(k) -
						(int) l1.totalCostForKDays(k);
			}
		});
		
		locs.sort((Location l1, Location l2)->(int) l1.totalCostForKDays(k) - (int) l2.totalCostForKDays(k));
		
		List<Location> ret = new ArrayList<Location>();
		ret.add(locs.get(0));
		
		Float minCost = locs.get(0).totalCostForKDays(k);
		for (int i = 1; i < locs.size(); i++) {
			if (locs.get(i).totalCostForKDays(k) == minCost) {
				ret.add(locs.get(i));
			} else {
				break;
			}
		}
		
		return ret;
	}
}
