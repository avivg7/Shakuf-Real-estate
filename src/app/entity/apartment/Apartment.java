package app.entity.apartment;

import app.entity.Entity;

/**  
 * Entity of type Apartment, abstract class
 */
public abstract class Apartment extends Entity {

	private String _address;
	private double _squareMeter;
	private int _numberOfRooms;
	private String _clientName;
	private double _price;
	
	/**
	 * 
	 * @param _address - Apartment address
	 * @param _id - Entity id
	 * @param _squareMeter - The Apartment square in meters
	 * @param _numberOfRooms = The Apartment total room numbers
	 * @param _clientName - Mame of the client
	 * @param _price - The Price per month of the rent 
	 */
	public Apartment(String _address, long _id, double _squareMeter, int _numberOfRooms, String _clientName, double _price) {
		super(_id);
		this._address = _address;
		this._squareMeter = _squareMeter;
		this._numberOfRooms = _numberOfRooms;
		this._clientName = _clientName;
		this._price = _price;
	}
		
	public String get_address() {
		return _address;
	}
	
	public void set_address(String _address) {
		this._address = _address;
	}
	
	public double get_squareMeter() {
		return _squareMeter;
	}
	
	public void set_squareMeter(double _squareMeter) {
		this._squareMeter = _squareMeter;
	}
	
	public int get_numberOfRooms() {
		return _numberOfRooms;
	}
	
	public void set_numberOfRooms(int _numberOfRooms) {
		this._numberOfRooms = _numberOfRooms;
	}
	
	public String get_clientName() {
		return _clientName;
	}
	
	public void set_clientName(String _clientName) {
		this._clientName = _clientName;
	}
	
	public double get_price() {
		return _price;
	}
	
	public void set_price(double _price) {
		this._price = _price;
	}
	
	/**
	 * Method that update the apartment's values
	 */
	public abstract void updateApartment();
	
	/**
	 * @return boolean - return True if the user input is valid
	 * 
	 * @param double squareMeter 
	 * @param int numberOfRooms 
	 * @param double price 
	 * @param String address 
	 * @param String clientName  
	 */
	public static boolean isValidFields( double squareMeter, int numberOfRooms, double price, String address, String clientName ) {
		return (
			squareMeter >= 1 &&
    		numberOfRooms >= 1 &&
    		price >= 1 &&
    		address.length() >= 1 &&
    		clientName.length() >= 1
		);
	}

	/**
	 * @return Object - cloning refers to creation of exact copy of an object
	 */
	@Override
	public Apartment clone() throws CloneNotSupportedException {
	return (Apartment)super.clone();
	}
	
	/**\
	 * @return String - All of the apartment details
	 */
	@Override
	public String toString() {
		return "\nID: " + super.ID + "\nAddress: " + _address + "\nSquare Meter: " + _squareMeter
				+ "\nNumber Of Rooms: " + _numberOfRooms + "\nClient Name: " + _clientName + "\nPrice: " + _price;
	}
	
	/**
	 * @return String - all parameters as one String
	 */
	public abstract String getAllParam();
	
	/**
	 * @return String - the class type (Apartment for sell / Apartment for rent
	 */
	public abstract String getType();

}
