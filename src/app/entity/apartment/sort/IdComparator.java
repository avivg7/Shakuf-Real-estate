package app.entity.apartment.sort;
import java.util.Comparator;
import app.entity.apartment.Apartment;

//Comparator for sorting apartments according to Id order
public class IdComparator implements Comparator<Apartment> {

	@Override
	public int compare(Apartment a1, Apartment a2) {
		return (int)(a1.getID() - a2.getID());			
	}

}
