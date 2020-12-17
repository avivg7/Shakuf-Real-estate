package app.entity.apartment;

import app.gui.GUI_Operator;

/**
 * 
 * This class represent the rental type of Apartment
 *
 */
public class RentApartment extends Apartment {

	private String _rentalStartDate;
	private String _rentalEndDate;
	
	/**
	 * \
	 * @param _address - Apartment address
	 * @param _id - Entity id
	 * @param _squareMeter - The Apartment square in meters
	 * @param _numberOfRooms = The Apartment total room numbers
	 * @param _clientName - Mame of the client
	 * @param _price - The Price per month of the rent 
	 * @param _rentlStartDate - The date we start the rental
	 * @param _rentalEndDate - The date we finish the rental
	 */
	public RentApartment(String _address, long _id, double _squareMeter, int _numberOfRooms, String _clientName, double _price, 
			 String _rentlStartDate, String _rentalEndDate) {
		super(_address, _id, _squareMeter, _numberOfRooms, _clientName, _price);
		this._rentalStartDate = _rentlStartDate;
		this._rentalEndDate = _rentalEndDate;
	}

	/**\
	 * @return - All of the apartment details
	 */
	@Override
	public String toString() {
		return "Rent " + super.toString() + ",\nRental Start Date:" + _rentalStartDate
				+ ",\nRental End Date:" + _rentalEndDate + "\n";
	}
	
	/**
	 * @return - Object cloning refers to creation of exact copy of an object
	 */
	@Override
	public RentApartment clone() throws CloneNotSupportedException {
		return (RentApartment)super.clone();
		
	}
	
	/**
	 * @return boolean - return True if the user input is valid
	 * 
	 * @param double squareMeter 
	 * @param int numberOfRooms 
	 * @param double price 
	 * @param String address 
	 * @param String clientName  
	 */
	public static boolean isValidFields( double squareMeter, int numberOfRooms, double price, String address, String clientName, String rentalStartDate, String rentalEndDate) {
		return Apartment.isValidFields(squareMeter, numberOfRooms, price, address, clientName) &&
				rentalStartDate.matches("\\d{2}-\\d{2}-\\d{4}") && rentalEndDate.matches("\\d{2}-\\d{2}-\\d{4}");
	}
	
	/**
	 * Method that update the apartment's values
	 */
	@Override
	public void updateApartment(){
		try {
			// String array for the labels
			String[] LABEL_TEXTS_RENT = {
					"Address",
			         "Square Meter", 
			         "Number Of Rooms", 
			         "Client Name", 
			         "Price",
			         "Start Date (dd-mm-yyyy)", 
			         "End Date (dd-mm-yyyy)" 
		         };
			
			// Create and run GUI
			GUI_Operator GUI = new GUI_Operator(LABEL_TEXTS_RENT, "Update apartment values");
			
		    // Take input from the text fields
		     String address = GUI.extractor("Address");
			 double squareMeter = Double.parseDouble(GUI.extractor("Square Meter"));
			 int numberOfRooms = Integer.parseInt(GUI.extractor("Number Of Rooms"));
			 String clientName = GUI.extractor("Client Name");
			 double price = Double.parseDouble(GUI.extractor("Price")); 
			 String rentalStartDate = GUI.extractor("Start Date (dd-mm-yyyy)");
			 String rentalEndDate = GUI.extractor("End Date (dd-mm-yyyy)");
			 
			// Check for invalid input
		    if(
		    	!RentApartment.isValidFields( squareMeter, numberOfRooms, price, address, clientName,rentalStartDate, rentalEndDate)
		    	){
		    	
			    GUI_Operator.showGUI_Massage("The action failed, you enterd invalid input");
			} else { // Set new values
				this.set_address(address);
			    this.set_squareMeter(squareMeter);
			    this.set_numberOfRooms(numberOfRooms);
			    this.set_clientName(clientName);
			    this.set_price(price);
			    this.set_rentlStartDate(rentalStartDate);
			    this.set_rentalEndDate(rentalEndDate);
			    
			    GUI_Operator.showGUI_Massage("Successfully updated");
			}		    
		    
		} catch (Exception e) {
			// In case of exception the user will see the next massage
			GUI_Operator.showGUI_Massage("The action failed, please fill in all the fields before sending the update");
		}
		
	}

	public String get_rentlStartDate() {
		return _rentalStartDate;
	}

	public void set_rentlStartDate(String _rentlStartDate) {
		this._rentalStartDate = _rentlStartDate;
	}

	public String get_rentalEndDate() {
		return _rentalEndDate;
	}

	public void set_rentalEndDate(String _rentalEndDate) {
		this._rentalEndDate = _rentalEndDate;
	}
	/**
	 * @return String - The type of the apartment (Apartment for rent)
	 */
	@Override
	public String getType() {
		return "Apartment for rent";
	}
	

}
