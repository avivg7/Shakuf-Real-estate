package app.entity.apartment;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//This class represent the sale type of Apartment
public class SellApartment extends Apartment {

	private double _offeredPrice;
	private String _entryDate;

	public SellApartment(String _address, long _id, double _squareMeter, int _numberOfRooms, String _clientName, double _price,
			double _offeredPrice, String _entryDate) {
		super(_address, _id, _squareMeter, _numberOfRooms, _clientName, _price);
		this._offeredPrice = _offeredPrice;
		this._entryDate = _entryDate;
	}
	
	@Override
	public String toString() {
		return "Sell " + super.toString() +"Offered Price=" + _offeredPrice
				+ ", Entry Date=" + _entryDate + "]\n";
	}
	
	// Object cloning refers to creation of exact copy of an object
	@Override
	public SellApartment clone() throws CloneNotSupportedException {
		return (SellApartment)super.clone();
		
	}
	
	// Method that update the apartment's values
	@Override
	public void updateApartment(){
		try {
			// String array for the labels
			String[] LABEL_TEXTS_SALL = { "Address",
			         "Square Meter", "Number Of Rooms", "Client Name", "Price", "Offered Price", "Entry Date dd-mm-yyyy" };
			
		    JPanel panel = new JPanel(new BorderLayout(5, 5));	    
		    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		    
		    // Create the labels for the GUI
		    for (int i = 0; i < LABEL_TEXTS_SALL.length; i++) 
		    	label.add(new JLabel(LABEL_TEXTS_SALL[i], SwingConstants.RIGHT));
			
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
		    JTextField offeredPrice = new JTextField();
		    controls.add(offeredPrice);
		    JTextField entryDate = new JTextField();
		    controls.add(entryDate);
		    
		    // Add boarder for the text fields
		    panel.add(controls, BorderLayout.CENTER);
		    
		    // Run GUI
		    JOptionPane.showConfirmDialog(null, panel, "Create a new Sell Apartment", JOptionPane.OK_CANCEL_OPTION);
		
		    // Take input from the text fields 
		    String _address = address.getText().toString();
			 double _squareMeter = Double.parseDouble(squareMeter.getText().toString());
			 int _numberOfRooms = Integer.parseInt(numberOfRooms.getText().toString());
			 String _clientName = clientName.getText().toString(); 
			 double _price = Double.parseDouble(price.getText().toString()); 
			 double _offeredPrice = Double.parseDouble(offeredPrice.getText().toString()); 
			 String _entryDate = entryDate.getText().toString(); 
		    
			 // Check for invalid input
		   if(_squareMeter < 1 || _numberOfRooms < 1 ||  _price < 1 || _offeredPrice < 1 || _address.length() < 1 || _clientName .length() < 1 || _entryDate.length() < 1 || !(_entryDate.matches("\\d{2}-\\d{2}-\\d{4}"))){		
				JOptionPane.showMessageDialog(null, "The action failed, you enterd invalid input");
			} else { // Set new values
				this.set_address(_address);
			    this.set_squareMeter(_squareMeter);
			    this.set_numberOfRooms(_numberOfRooms);
			    this.set_clientName(_clientName);
			    this.set_price(_price);
			    this.set_offeredPrice(_offeredPrice);
			    this.set_entryDate(_entryDate);
			    JOptionPane.showMessageDialog(null," successfully updated");		
			}
		  	    
		} catch (Exception e) {
			// In case of exception the user will see the next massage
			JOptionPane.showMessageDialog(null, "The action failed, please fill in all the fields before sending the update");
		} 
		
	}

	public double get_offeredPrice() {
		return _offeredPrice;
	}

	public void set_offeredPrice(double _offeredPrice) {
		this._offeredPrice = _offeredPrice;
	}

	public String get_entryDate() {
		return _entryDate;
	}

	
	public void set_entryDate(String _entryDate) {
		this._entryDate = _entryDate;
	}
	
	

}
