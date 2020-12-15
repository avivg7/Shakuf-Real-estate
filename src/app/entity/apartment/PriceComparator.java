package app.entity.apartment;

import java.util.Comparator;
// Comparator for sorting apartments according to price
public class PriceComparator implements Comparator<Apartment>{

	@Override
	public int compare(Apartment a1, Apartment a2) {
		return (int)(a1.get_price() - a2.get_price());		
	}

}
