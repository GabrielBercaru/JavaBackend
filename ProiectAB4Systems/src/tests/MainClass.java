package tests;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import locations.City;
import locations.Country;
import locations.County;
import locations.Location;
import solver.Solver;

public class MainClass {
	private Solver solver;
	
	@Before
    public void setup() {
        try {
			solver = new Solver("input/input0");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
 
    @org.junit.Test
    public void testCheapestDestination() {
    	Date refStartDate = null;
    	Date refEndDate = null;
		try {
			refStartDate = new SimpleDateFormat("dd/MM/yyyy").parse("05/07/2018");
			refEndDate = new SimpleDateFormat("dd/MM/yyyy").parse("15/07/2018");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Location> locs = solver.cheapestKDaysDestination(1);
		
		Set<String> set = new TreeSet<String>();
		set.add("tur oras");
		Location refLocation = new Location(2, "C", new City("CityA"), new County("CountyA"),
    			new Country("CountryA"), Float.valueOf(110.75f), set, refStartDate, refEndDate);
		
    	Assert.assertEquals(refLocation, locs.get(0));
    }
    
    @After
    public void tearDown() {
        solver = null;
    }
}