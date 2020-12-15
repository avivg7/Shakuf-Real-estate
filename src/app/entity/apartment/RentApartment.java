package app.entity.apartment;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

// This class represent the rental type of Apartment
public class RentApartment extends Apartment {

	private String _rentlStartDate;
	private String _rentalEndDate;
	
	
	public RentApartment(String _address, long _id, double _squareMeter, int _numberOfRooms, String _clientName, double _price, 
			 String _rentlStartDate, String _rentalEndDate) {
		super(_address, _id, _squareMeter, _numberOfRooms, _clientName, _price);
		this._rentlStartDate = _rentlStartDate;
		this._rentalEndDate = _rentalEndDate;
	}

	
	@Override
	public String toString() {
		return "Rent " + super.toString() + ", Rental Start Date:" + _rentlStartDate
				+ ", Rental End Date:" + _rentalEndDate + "]\n";
	}
	
	// Object cloning refers to creation of exact copy of an object
	@Override
	public RentApartment clone() throws CloneNotSupportedException {
		return (RentApartment)super.clone();
		
	}
	
	// Method that update the apartment's values
	@Override
	public void updateApartment(){
		try {
			// String array for the labels
			String[] LABEL_TEXTS_RENT = { "Address",
			         "Square Meter", "number Of Rooms", "Client Name", "Price", "Start Date dd-mm-yyyy", "End Date dd-mm-yyyy" };
			
			JPanel panel = new JPanel(new BorderLayout(5, 5));	
			JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
			
			// Create the labels for the GUI
		    for (int i = 0; i < LABEL_TEXTS_RENT.length; i++) 
		    	label.add(new JLabel(LABEL_TEXTS_RENT[i], SwingConstants.RIGHT));
			 
		    // Add boarder for the labels
		    panel.add(label, BorderLayout.WEST);
		    
		    // Create the text fields for the GUI
		    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		    JTextField address = new JTextField();
		    controls.add(address);
		    JTextField squareMeter = new JTextField();
		    controls.add(squareMeter);
		    JTextField numberOfRooms = new JTextField();
		    controls.add(numberOfRooms);
		    JTextField clientName = new JTextField();
		    controls.add(clientName);
		    JTextField price = new JTextField();
		    controls.add(price);
		    JTextField rentlStartDate = new JTextField();
		    controls.add(rentlStartDate);
		    JTextField rentalEndDate = new JTextField();
		    controls.add(rentalEndDate);
		    
		    // Add boarder for the text fields
		    panel.add(controls, BorderLayout.CENTER);
		    
		    // Run GUI
		    JOptionPane.showConfirmDialog(null, panel, "Update apartment values", JOptionPane.OK_CANCEL_OPTION);
		    
		    // Take input from the text fields
		    String _address = address.getText().toString();
			 double _squareMeter = Double.parseDouble(squareMeter.getText().toString());
			 int _numberOfRooms = Integer.parseInt(numberOfRooms.getText().toString());
			 String _clientName = clientName.getText().toString(); 
			 double _price = Double.parseDouble(price.getText().toString()); 
			 String _rentlStartDate = rentlStartDate.getText().toString();
			 String _rentalEndDate = rentalEndDate.getText().toString();
			 
			// Check for invalid input
		    if(_squareMeter < 1 || _numberOfRooms < 1 || _price < 1 || _rentlStartDate.length() < 6 || _address.length() < 1 || _clientName .length() < 1 || _rentalEndDate.length() < 6 || !(_rentlStartDate.matches("\\d{2}-\\d{2}-\\d{4}")) || !(_rentlStartDate.matches("\\d{2}-\\d{2}-\\d{4}"))){
				JOptionPane.showMessageDialog(null, "The action failed, you enterd invalid input");
			} else { // Set new values
				this.set_address(_address);
			    this.set_squareMeter(_squareMeter);
			    this.set_numberOfRooms(_numberOfRooms);
			    this.set_clientName(_clientName);
			    this.set_price(_price);
			    this.set_rentlStartDate(_rentlStartDate);
			    this.set_rentalEndDate(_rentalEndDate);
			    JOptionPane.showMessageDialog(null, " successfully updated");
			}		    
		    
		} catch (Exception e) {
			// In case of exception the user will see the next massage
			JOptionPane.showMessageDialog(null, "The action failed, please fill in all the fields before sending the update");
		}
		
	}

	public String get_rentlStartDate() {
		return _rentlStartDate;
	}

	public void set_rentlStartDate(String _rentlStartDate) {
		this._rentlStartDate = _rentlStartDate;
	}

	public String get_rentalEndDate() {
		return _rentalEndDate;
	}

	public void set_rentalEndDate(String _rentalEndDate) {
		this._rentalEndDate = _rentalEndDate;
	}
	

}
