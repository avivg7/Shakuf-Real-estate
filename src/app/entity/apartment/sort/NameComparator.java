package app.entity.apartment.sort;

import java.util.Comparator;

import app.entity.apartment.Apartment;

//Comparator for sorting apartments according to Client Name
public class NameComparator implements Comparator<Apartment> {

	@Override
	public int compare(Apartment a1, Apartment a2) {
		return a1.get_clientName().compareTo(a2.get_clientName());
		// method 
		
	}

}
