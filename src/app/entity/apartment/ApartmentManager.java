package app.entity.apartment;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import app.KeyValue;


/**
 * TODO
 * A. Change getUserSelection to be static
 * B. The _icon to
 */

// This class represent the system that manage the apartments
public class ApartmentManager {
	
	/**
	 * 
	 */
	private ArrayList<Apartment> _apartments;
	
	/**
	 * 
	 */
	private ImageIcon _icon;
	
	/**
	 * 
	 */
	public boolean _stopRunningMunu = false;
	
	/**
	 * @param long - Counter for Entoty's ID, created to track and control its value
	 */
	public static long _counterID = 1;
	
	public ApartmentManager() {
		
		_apartments = new ArrayList<Apartment>();
		
		// Icon picture for the GUI
		_icon = new ImageIcon("src\\app\\icons\\_icon.jpg");
		
		// Create some sample apartments
		/**
			this._apartments.add(new SellApartment("Yehuda Halevi 22, Tel-Aviv", _counterID, 76, 3, "Yossi Cohen", 2000000, 1500000, "12-2-2021"));
			_counterID++;
			this._apartments.add(new SellApartment("Bugrashov 13, Tel-Aviv", _counterID, 106, 4, "Moshe Levi", 3000000, 1900000, "22-4-2021"));
			_counterID++;
			this._apartments.add(new SellApartment("Hagadna 6, Rishon Letzion", _counterID, 70, 3, "Dudi Hertz", 1200000, 1000000, "21-1-2021"));
			_counterID++;
			this._apartments.add(new RentApartment("rotshild 17, Tel-Aviv", _counterID, 50, 2, "Miri Shavtay", 4000, "20-2-2020", "20-2-2021"));
			_counterID++;
			this._apartments.add(new RentApartment("Hayarkon 6, Tel-Aviv", _counterID, 90, 3, "Eli Kopter", 8000, "15-1-2020", "15-1-2021"));
		**/
	}
	
	/**
	 * 
	 * 
	 * @param options - 
	 * @param label - 
	 * @param description - 
	 * 
	 * @return String - 
	 */
	public String getUserSelection( String[] options, String label, String description ) {
		
		// Frame for the GUI
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		frame.setSize(420,420);
		
		// This is the menu, here the user will be able to choose which of the main actions of the program he wants to do
		String userSelection = (String)JOptionPane.showInputDialog(
				frame, 
				label,
				description,
				0, 
				this._icon,
				options,
				options[0]
			);
		
		return userSelection.trim();
		
	}
	
	/**
	 * Runs the menu
	 */
	public void runManager() {
		
		// String array for the labels
		String[] options = {
				"Add new apartment",
				"Find apartment",
		        "Show all apartments", 
		        "Sort apartments",
		        "Exit"
	        };
		
		try {
			
			while(!_stopRunningMunu) {
				
				String userSelection = this.getUserSelection(
						options, 
						"Please select the type of apartment you would like to add to the apartment database", 
						"Menu"
					);
				
				// Switch case that represents every option on the menu
				switch ( userSelection ) {
					case "Add new apartment":
						this.addApartment();
						break;
					
					case "Find apartment":
						this.findApartment();
						break;
						
					case "Show all apartments":
						this.showAllApartments();		
						break;
						
					case "Sort apartments":
						this.sortByPrice();		
						break;
						
					case "Exit":
						// If the user choose Exit then we will get out from the loop
						this._stopRunningMunu = true;
						JOptionPane.showMessageDialog(null, "Thank you and goodbye");
						break;
	
					default:
						break;
				}
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Thank you and goodbye");
		}
	}
	
	public Map<String, JPanel> _getApartmentGUIManager( String[] LABEL_TEXTS_RENT ) {
		
		Map<String, JPanel> hm = new HashMap<>();
		
		// Create panels for the GUI	
		JPanel panel = new JPanel(new BorderLayout(5, 5));   
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		
		
		// Create the labels for the GUI
	    for (int i = 0; i < LABEL_TEXTS_RENT.length; i++) {
	    	label.add(new JLabel(LABEL_TEXTS_RENT[i], SwingConstants.RIGHT));
	    }
	    
	    // Add boarder for the labels
	    panel.add(label, BorderLayout.WEST);
	    
	    // Create the text fields for the GUI
//	    JTextField address = new JTextField();
//	    JTextField squareMeter = new JTextField();
//	    JTextField numberOfRooms = new JTextField();
//	    JTextField clientName = new JTextField();
//	    JTextField price = new JTextField();
//	    
//	    controls.add(address);
//	    controls.add(squareMeter);
//	    controls.add(numberOfRooms);
//	    controls.add(clientName);
//	    controls.add(price);
	    
	    // Add boarder for the text fields
	    //panel.add(controls, BorderLayout.CENTER);
	    
	    // Run GUI
	    //JOptionPane.showConfirmDialog(null, panel, confirmDialogText, JOptionPane.OK_CANCEL_OPTION);
		
		hm.put("panel",panel);
		hm.put("label",label);
		hm.put("controls",controls);
		
		//Map<String, Object> fields = new HashMap<>();
		
	    // Take input from the text fields
		/*
		 * fields.put("address", address.getText().toString() );
		 * fields.put("squareMeter",
		 * Double.parseDouble(squareMeter.getText().toString()) );
		 * fields.put("numberOfRooms",
		 * Integer.parseInt(numberOfRooms.getText().toString() ) );
		 * fields.put("clientName", clientName.getText().toString() );
		 * fields.put("price", Double.parseDouble(price.getText().toString() ) );
		 * 
		 * hm.put("fields",controls);
		 */
		
		return hm;
	}
	
	public static void showApartmentConfirmDialog( JPanel panel, String confirmText ) {
	
		   JOptionPane.showConfirmDialog(null, panel, confirmText, JOptionPane.OK_CANCEL_OPTION);
	}
	 
	/**
	 * Add new apartment to the list
	 */
	public void addApartment(){
		try {

			 String _address;
			 double _squareMeter;
			 int _numberOfRooms;
			 String _clientName; 
			 double _price; 
			 double _offeredPrice; 
			 String _entryDate; 
			 String _rentlStartDate;
			 String _rentalEndDate;
			 
	
			
			// Create panels for the GUI	
			 //JPanel panel = new JPanel(new BorderLayout(5, 5));
			 //JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
			 //JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
			 
			 JPanel panel, label, controls;
			 
			 Map<String, JPanel> apartmentGUIManager
			 
			// String array for the labels
			String[] options = {"Apartment For Sall", "Apartment For Rent"};
			
			String userSelection = this.getUserSelection(
					options, 
					"Please select the type of apartment you would like to add to the apartment database", 
					"Add new apartment"
				);
			
			// Switch case for the input that returned from the user selection
			switch ( userSelection) {
				case "Apartment For Rent":
					
					
					// String array for the labels
					String[] LABEL_TEXTS_RENT = { "Address",
					         "Square Meter", "number Of Rooms", "Client Name", "Price", "Start Date dd-mm-yyyy", "End Date dd-mm-yyyy" };
					
					 apartmentGUIManager = this._getApartmentGUIManager(LABEL_TEXTS_RENT);
					 
					
					// Create the labels for the GUI
					/*
					 * for (int i = 0; i < LABEL_TEXTS_RENT.length; i++) { label.add(new
					 * JLabel(LABEL_TEXTS_RENT[i], SwingConstants.RIGHT)); }
					 */
				    
				    // Add boarder for the labels
				    //panel.add(label, BorderLayout.WEST);
					panel = apartmentGUIManager.get("panel");
					label = apartmentGUIManager.get("label");
					controls = apartmentGUIManager.get("controls");
				    
				    // Create the text fields for the GUI
				    JTextField addressRent = new JTextField();
				    controls.add(addressRent);
				    JTextField squareMeterRent = new JTextField();
				    controls.add(squareMeterRent);
				    JTextField numberOfRoomsRent = new JTextField();
				    controls.add(numberOfRoomsRent);
				    JTextField clientNameRent = new JTextField();
				    controls.add(clientNameRent);
				    JTextField priceRent = new JTextField();
				    controls.add(priceRent);
				    JTextField rentlStartDateRent = new JTextField();
				    controls.add(rentlStartDateRent);
				    JTextField rentalEndDateRent = new JTextField();
				    controls.add(rentalEndDateRent);
				    
				    // Add boarder for the text fields
				    panel.add(controls, BorderLayout.CENTER);
				    
				    showApartmentConfirmDialog(panel, "Create a new Sell Apartment");
				    
				    // Run GUI
				    //JOptionPane.showConfirmDialog(null, panel, "Create a new Sell Apartment", JOptionPane.OK_CANCEL_OPTION);
				    
				    // Take input from the text fields
				    _address = addressRent.getText().toString();
				    _squareMeter = Double.parseDouble(squareMeterRent.getText().toString());
				    _numberOfRooms = Integer.parseInt(numberOfRoomsRent.getText().toString());
				    _clientName = clientNameRent.getText().toString();
				    _price = Double.parseDouble(priceRent.getText().toString());
				    _rentlStartDate = rentlStartDateRent.getText().toString();
				    _rentalEndDate = rentalEndDateRent.getText().toString();
				    
				    String _rentlDateMatches = "\\d{2}-\\d{2}-\\d{4}";
				    
				    // Check if the id already exist in the list and for invalid input
				    if ( this.isExist(_counterID + 1) ) {
				    	JOptionPane.showMessageDialog(null, "The action failed, the database is allready contain an apartment with similar ID");
					} 
				    
				    else if( 
				    		!Apartment.isValidFields( _squareMeter, _numberOfRooms, _price, _address, _clientName ) ||
				    		!_rentlStartDate.matches(_rentlDateMatches) || !_rentalEndDate.matches(_rentlDateMatches)
			    		){
				    	
				    	JOptionPane.showMessageDialog(null, "The action failed, you enterd invalid input");
				    }
		
				    else { 
						// Advanced the counter and create a new apartment in the list
						_counterID++;
						this._apartments.add( 
								new RentApartment(
									_address, 
									_counterID, 
									_squareMeter, 
									_numberOfRooms, 
									_clientName, 
									_price, 
									_rentlStartDate, 
									_rentalEndDate
								)
							);
						
						JOptionPane.showMessageDialog(null, "Apartment added successfully");
					}			
			    break;
				case "Apartment For Sall":
					
					// String array for the labels
					String[] LABEL_TEXTS_SALL = {
							"Address",
					         "Square Meter",
					         "number Of Rooms",
					         "Client Name",
					         "Price",
					         "Offered Price",
					         "Entry Date dd-mm-yyyy"
				         };
					
					
					apartmentGUIManager = this._getApartmentGUIManager(LABEL_TEXTS_RENT);
					
					// Create the labels for the GUI
					/*
					 * for (int i = 0; i < LABEL_TEXTS_SALL.length; i++) { label.add( new
					 * JLabel(LABEL_TEXTS_SALL[i], SwingConstants.RIGHT) ); }
					 */
	
				    // Add boarder for the labels
				    //panel.add(label, BorderLayout.WEST);
				    
				    // Create the text fields for the GUI
				    JTextField addressSall = new JTextField();
				    controls.add(addressSall);
				    JTextField squareMeterSall = new JTextField();
				    controls.add(squareMeterSall);
				    JTextField numberOfRoomsSall = new JTextField();
				    controls.add(numberOfRoomsSall);
				    JTextField clientNameSall = new JTextField();
				    controls.add(clientNameSall);
				    JTextField priceSall = new JTextField();
				    controls.add(priceSall);
				    JTextField offeredPriceSall = new JTextField();
				    controls.add(offeredPriceSall);
				    JTextField entryDateSall = new JTextField();
				    controls.add(entryDateSall);
				    
				    // Add boarder for the text fields
				    panel.add(controls, BorderLayout.CENTER);
				    
				    
				    showApartmentConfirmDialog(panel,  "Create a new Sell Apartment");
	
				    // Run GUI
				    //JOptionPane.showConfirmDialog(null, panel, "Create a new Sell Apartment", JOptionPane.OK_CANCEL_OPTION);
				    
				    // Take input from the text fields
				    _address = addressSall.getText().toString();
				    _squareMeter = Double.parseDouble(squareMeterSall.getText().toString());
				    _numberOfRooms = Integer.parseInt(numberOfRoomsSall.getText().toString());
				    _clientName = clientNameSall.getText().toString();
				    _price = Double.parseDouble(priceSall.getText().toString());
				    _offeredPrice = Double.parseDouble(offeredPriceSall.getText().toString());
				    _entryDate = entryDateSall.getText().toString();
	
				    // Check if the id already exist in the list and for invalid input
				    if (this.isExist(_counterID + 1)) {
				    	JOptionPane.showMessageDialog(null, "The action failed, the database is allready contain an apartment with similar ID");
					} else if(_squareMeter < 1 || _numberOfRooms < 1 ||  _price < 1 || _offeredPrice < 1 || _address.length() < 1 || _clientName .length() < 1 || _entryDate.length() < 1 || !(_entryDate.matches("\\d{2}-\\d{2}-\\d{4}"))){
						JOptionPane.showMessageDialog(null, "The action failed, you enterd invalid input");
					} else {
						// Advanced the counter and create a new apartment in the list
						_counterID++;
						this._apartments.add(new SellApartment(_address, _counterID, _squareMeter, _numberOfRooms, _clientName, _price, _offeredPrice, _entryDate));
						JOptionPane.showMessageDialog(null, "Apartment added successfully");
					}
				    		    
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The action failed, please fill in all the fields before sending the create file");
		}					    
	}

	
	// return true if apartment Is exist in the list
	private boolean isExist(long ID) {
		for (Apartment a : _apartments) {
			if (a.getID() == ID) 
				return true;
		}
					
		return false;		
	}

	// shows all the details of the apartments in the list
	public void showAllApartments() {
		
		try {
			/*
			JFrame frame = new JFrame("Storage");
			JList<Apartment> list = new JList<>();
			DefaultListModel<Apartment> model = new DefaultListModel<>();
			
			JLabel label = new JLabel();
			JPanel panel = new JPanel();
			JSplitPane splitPane = new JSplitPane();
			
			list.setModel(model);
			
			// 18-19
			for (Apartment apartment : _apartments) {
				model.addElement(apartment);
			}
			
			list.getSelectionModel().addListSelectionListener(e -> {
	            Apartment apartment = list.getSelectedValue();
	            label.setText(apartment.toString());
	        });
			
			splitPane.setLeftComponent(new JScrollPane(list));
	        panel.add(label);
	        splitPane.setRightComponent(panel);

	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.add(splitPane);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
			
	        SwingUtilities.invokeLater(JListTutorial::new);
			*/
			
			JPanel panel = new JPanel(new BorderLayout(5, 5));		    
			JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
			
			for (Apartment apartment : _apartments) 
				label.add(new JLabel(apartment.toString(), SwingConstants.RIGHT));
			
			panel.add(label, BorderLayout.WEST);
			JOptionPane.showConfirmDialog(null, panel, "Create a new Sell Apartment", JOptionPane.OK_CANCEL_OPTION);
		
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somthing went wrong, please try again");
		}
	}
	
	
	// Sort the list by price parameter
	public void sortByPrice() {
		Collections.sort(_apartments, new PriceComparator());
		JOptionPane.showMessageDialog(null, "The apartments were successfully sorted");
	}
	
	
	// find specific apartment by id key
	public void findApartment() {
		
		// Frame for the GUI
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		frame.setSize(420,420);
		
		// If an apartment with the same ID is found for the input that the user entered, the value will become true
		boolean found = false;
		
		// We will ask the user for input of ID 
		String findID = (String)JOptionPane.showInputDialog(
				frame, 
				"Please enter the ID of the requested apartment",
				"Enter the ID of the requested apartment",
				 0, _icon,
				null,
				null
				);
		
		// We will look for the ID from the input in the entire list of apartments, if we found 
		// the apartment the user will be able to choose actions he can do on that apartment
		for (Apartment apartment : _apartments) {
			
			// found an apartment with the same ID
			if (apartment.getID()== Long.parseLong(findID)) {
				found = true;
				
				// String array for the labels
				String[] options = {"Edit Apartment", "Remove Apartment", "Show Details"};
				
				// Now the user will be able to choose actions he can do on the apartment we found
				String userSelection = (String)JOptionPane.showInputDialog(
						frame, 
						"Please select the type of apartment you would like to add to the apartment database",
						"Chooses an apartment type",
						0, _icon,
						options,
						options[0]
						);
				
				// Switch case that represents every action the user can do on the chosen apartment 
				switch (userSelection) {
					case "Edit Apartment":
						apartment.updateApartment();					
						break;
						
					case "Remove Apartment":
						try {
							// remove the chosen apartment from the list
							_apartments.remove(apartment);
							JOptionPane.showMessageDialog(null, "Apartment ID: " + findID + " successfully removed");					
						} catch (Exception e) {
							
							// if something went wrong while trying to remove the object from the list
							JOptionPane.showMessageDialog(null, "The action failed, please try again");
						}
						
						break;
						
					case "Show Details":	
						JOptionPane.showMessageDialog(null, apartment.toString());
						break;
	
					default:
						break;
				}
				
				break;
			}
		}
		
		if (!found) 
			// we the boolean value of found is still false after the last iteration them we will send that massage
			JOptionPane.showMessageDialog(null, "The Apartment is not in our database :(");
					 
	}


}
