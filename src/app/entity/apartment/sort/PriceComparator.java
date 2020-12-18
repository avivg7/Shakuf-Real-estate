package app.entity.apartment.sort;

import java.util.Comparator;

import app.entity.apartment.Apartment;
// Comparator for sorting apartments according to price
public class PriceComparator implements Comparator<Apartment>{

	@Override
	public int compare(Apartment a1, Apartment a2) {
		return (int)(a1.get_price() - a2.get_price());		
	}

}
