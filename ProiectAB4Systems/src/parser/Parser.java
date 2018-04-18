package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;
import java.util.Set;

import locations.City;
import locations.Country;
import locations.County;
import locations.Location;

/**
 * A utility class used for reading the input file and
 * storing all the entries inside.
 * 
 * @author Gabriel Bercaru
 *
 */
public class Parser {
	private String inputFilename;
	private Integer id;
	private BufferedReader br;
	
	public Parser(String inputFilename) {
		id = 0;
		this.inputFilename = inputFilename;
		openFileForReading();
	}
	
	/**
	 * Public method that attempts to read the input file for which
	 * this parser instance was created.
	 * 
	 * @param locations The set of locations that will be used to store the entries
	 * @throws IOException If the reader fails to read the next line of input
	 */
	public void readAllEntries(Set<Location> locations) throws IOException {
		while (readLocation(locations)) {
			id++;
		}
		
		closeFile();
	}
	
	/**
	 * Private method that attempts to read a single entry in the input file.
	 * If successfully read the entry, place it inside locations.
	 * 
	 * @param locations The set of locations used to place the newly parsed entry
	 * @return True if the the parse succeeded, False otherwise
	 * @throws IOException If the reader failed to read the current line of input
	 */
	private boolean readLocation(Set<Location> locations) throws IOException {
		String line = br.readLine();
		
		if (line == null || line.length() == 0) {
			return false;
		}
		
		String[] components = line.split(",");
		
		String name = components[0];
		City city = new City(components[1]);
		County county = new County(components[2]);
		Country country = new Country(components[3]);
		Float avgPrice = Float.parseFloat(components[4]);
		Set<String> activities = new TreeSet<>();
		
		for (int i = 5; i < components.length - 2; i++) {
			activities.add(components[i]);
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = dateFormat.parse(components[components.length - 2]);
			endDate = dateFormat.parse(components[components.length - 1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		locations.add(new Location(id, name, city, county, country, avgPrice,
				activities, startDate, endDate));
		
		return true;
	}
	
	/**
	 * Method called inside the constructor for obtaining a BufferedReader instance
	 */
	private void openFileForReading() {
		try {
			br = new BufferedReader(new FileReader(inputFilename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used at the end of the parse process
	 */
	public void closeFile() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
