package test;

import java.io.IOException;
import java.util.TreeSet;
import java.util.Set;

import locations.Location;
import parser.Parser;

/**
 * Class used for testing the functionality of the input file parser.
 * 
 * @author Gabriel Bercaru
 */
public class MainClass {
	private static Parser parser;
	private static Set<Location> locations;
	
	public static void main(String[] args) throws IOException {
		initializeReferences();
		
		parser.readAllEntries(locations);
		
		for (Location location : locations) {
			System.out.println(location);
		}
	}

	private static void initializeReferences() {
		parser = new Parser("test0");
		locations = new TreeSet<>();
	}
}
