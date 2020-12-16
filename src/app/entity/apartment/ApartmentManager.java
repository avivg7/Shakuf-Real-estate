package app.entity.apartment;

import javax.swing.*;
import app.GUI_Operator;
import app.GUI_Table;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Collections;



/**
 * This class represent the system that manage the apartments
 */
public class ApartmentManager {
		
	/**
	 * @param ArrayList<Apartment> - all the apartments that made by the user s
	 */
	private ArrayList<Apartment> _apartments = new ArrayList<Apartment>();
	
	/**
	 * @param ImageIcon - The icon for the GUI
	 */
	public static ImageIcon _icon = new ImageIcon("src\\app\\icons\\_icon.jpg");
	
	/**
	 * @param boolean - 
	 */
	public boolean _stopRunningMunu = false;
	
	/**
	 * @param long - Counter for Entoty's ID, created to track and control its value
	 */
	public static long _counterID = 0;

	public ApartmentManager() {
		// Create some sample apartments
			_counterID++;
			this._apartments.add(new SellApartment("Yehuda Halevi 22, Tel-Aviv", _counterID, 76, 3, "Yossi Cohen", 2000000, 1500000, "12-2-2021"));
			_counterID++;
			this._apartments.add(new SellApartment("Bugrashov 13, Tel-Aviv", _counterID, 106, 4, "Moshe Levi", 3000000, 1900000, "22-4-2021"));
			_counterID++;
			this._apartments.add(new SellApartment("Hagadna 6, Rishon Letzion", _counterID, 70, 3, "Dudi Hertz", 1200000, 1000000, "21-1-2021"));
			_counterID++;
			this._apartments.add(new RentApartment("rotshild 17, Tel-Aviv", _counterID, 50, 2, "Miri Shavtay", 4000, "20-2-2020", "20-2-2021"));
			_counterID++;
			this._apartments.add(new RentApartment("Hayarkon 6, Tel-Aviv", _counterID, 90, 3, "Eli Kopter", 8000, "15-1-2020", "15-1-2021"));
		
	}
	
	/**
	 * Runs the menu
	 */
	public void runManager() {
		
		// String array for the labels
		String[] options = {
				"Add new apartment",
				"Find apartment",
				"Choose an ID from a list",
		        "Show all apartments", 
		        "Sort apartments",
		        "Exit"
	        };
		
		try {
			
			while(!_stopRunningMunu) {
				
				String userSelection = GUI_Operator.getUserSelection(
						options, 
						"Please select the action you want to do", 
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
					case "Choose an ID from a list":
						this.selectIdFromList();
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
						GUI_Operator.showGUI_Massage("Thank you and goodbye");
						break;
	
					default:
						break;
				}
			}
		} 
		catch (Exception e) {
			GUI_Operator.showGUI_Massage("Thank you and goodbye");
		}
	}
	
	 
	/**
	 * Add new apartment to the list
	 */
	public void addApartment(){
		try {

			 String address;
			 double squareMeter;
			 int numberOfRooms;
			 String clientName; 
			 double price; 
			 double offeredPrice; 
			 String entryDate; 
			 String rentalStartDate;
			 String rentalEndDate;

			// String array for the labels
			String[] options = {"Apartment For Sall", 
								"Apartment For Rent"};
			
			String userSelection = GUI_Operator.getUserSelection(
					options, 
					"Please select the type of apartment you would like to add to the apartment database", 
					"Add new apartment"
				);
			
			//
			GUI_Operator GUI;
			
			// Switch case for the input that returned from the user selection
			switch ( userSelection) {
				case "Apartment For Rent":
					
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
					
					// Create a new GUI with the text labels we created
					GUI = new GUI_Operator(LABEL_TEXTS_RENT, "Create a new rent apartment");
					
				    // Take input from the GUI
				    address = GUI.extractor("Address");
				    squareMeter = Double.parseDouble(GUI.extractor("Square Meter"));
				    numberOfRooms = Integer.parseInt(GUI.extractor("Number Of Rooms"));
				    clientName = GUI.extractor("Client Name");
				    price = Double.parseDouble(GUI.extractor("Price"));
				    rentalStartDate = GUI.extractor("Start Date (dd-mm-yyyy)");
				    rentalEndDate = GUI.extractor("End Date (dd-mm-yyyy)");
				    				    
				    // Check if the id already exist in the list and for invalid input
				    if ( this.isExist(_counterID + 1) ) {
				    	GUI_Operator.showGUI_Massage("The action failed, the database is allready contain an apartment with similar ID, please try again");
				    	_counterID++;
					} 
				    
				    else if( 
				    		!RentApartment.isValidFields( squareMeter, numberOfRooms, price, address, clientName,rentalStartDate, rentalEndDate) 
				    	){
				    	GUI_Operator.showGUI_Massage("The action failed, you enterd invalid input");
				    }
		
				    else { 
						// Advanced the counter and create a new apartment in the list
						_counterID++;
						this._apartments.add( 
								new RentApartment(
									address, 
									_counterID, 
									squareMeter, 
									numberOfRooms, 
									clientName, 
									price, 
									rentalStartDate, 
									rentalEndDate
								)
							);
						
						GUI_Operator.showGUI_Massage("Apartment added successfully");
					}			
			    break;
				case "Apartment For Sall":
					
					// String array for the labels
					String[] LABEL_TEXTS_SALL = {
							"Address",
					         "Square Meter", 
					         "Number Of Rooms", 
					         "Client Name", 
					         "Price",
					         "Offered Price",
					         "Entry Date dd-mm-yyyy"
				         };
					
					// Create a new GUI with the text labels we created
					GUI = new GUI_Operator(LABEL_TEXTS_SALL, "Create a new sale Apartment");
					    
				    // Take input from the text fields
					address = GUI.extractor("Address");
				    squareMeter = Double.parseDouble(GUI.extractor("Square Meter"));
				    numberOfRooms = Integer.parseInt(GUI.extractor("Number Of Rooms"));
				    clientName = GUI.extractor("Client Name");
				    price = Double.parseDouble(GUI.extractor("Price"));
				    offeredPrice = Double.parseDouble(GUI.extractor("Offered Price"));
				    entryDate = GUI.extractor("Entry Date dd-mm-yyyy");
	
				    
				 // Check if the id already exist in the list and for invalid input
				    if ( this.isExist(_counterID + 1) ) {
				    	GUI_Operator.showGUI_Massage("The action failed, the database is allready contain an apartment with similar ID, please try again");
				    	_counterID++;
					} 
				    
				    else if( !SellApartment.isValidFields( squareMeter, numberOfRooms, price, address, clientName, entryDate, offeredPrice)) {  
				    	GUI_Operator.showGUI_Massage("The action failed, you enterd invalid input");
				    }
		
				    else { 
						// Advanced the counter and create a new apartment in the list
						_counterID++;
						this._apartments.add( 
								new SellApartment(
									address, 
									_counterID, 
									squareMeter, 
									numberOfRooms, 
									clientName, 
									price, 
									offeredPrice, 
									entryDate
								)
							);
						
						GUI_Operator.showGUI_Massage("Apartment added successfully");
					}			
				    				    		    
				break;
			}
		} catch (Exception e) {
			GUI_Operator.showGUI_Massage("The action failed, please fill in all the fields before sending the create file");
		}					    
	}

	/**
	 *  Check if the list contain specific apartment
	 * @param long ID - The ID of the Entity represent the primary key of the Entity value
	 * @return boolean - Return True if the apartment with the ID exist in the apartments list
	 */
	private boolean isExist(long ID) {
		for (Apartment a : _apartments) {
			if (a.getID() == ID) 
				return true;
		}				
		return false;		
	}
	
	/**
	 * Preset the user a list of ID's that he can selects and perform actions
	 */
	public void selectIdFromList() {
		try {
			String[] IDOptions = new String[_apartments.size()];
			for (int i = 0; i < _apartments.size(); i++) {
				IDOptions[i] = String.valueOf(_apartments.get(i).getID());
			}
			
			String userSelection = GUI_Operator.getUserSelection(IDOptions, "Select the wanted ID", "Select the wanted ID 2");
			this.actionsOnApartment(getApartmentByID(userSelection));
			
		} catch (Exception e) {
			GUI_Operator.showGUI_Massage("Somthing went wrong, please try again");
		}
	}
	
	// shows all the details of the apartments in the list
	public void showAllApartments() {
		
			
		try {
			GUI_Table gt = new GUI_Table(_apartments);
			
			
			
			/*
			JFrame f=new JFrame();
			
			String[] column = {
					"Type",
					"ID",
					"Address",
					"Square Meter",
					"Number Of Rooms",
					"Clint Name",
					"Price",
					"Entry Date",
					"Rental Start Date",
					"Rental End Date",
				};
					
			String[][] data = new String[_apartments.size()][11]; 
			for (int i = 0; i < _apartments.size(); i++) {
				System.out.println("try");

				data[i][0] = _apartments.get(i).getType();
				data[i][1] = String.valueOf(_apartments.get(i).getID());
				data[i][2] = _apartments.get(i).get_address();
				data[i][3] = String.valueOf(_apartments.get(i).get_squareMeter());
				data[i][4] = String.valueOf(_apartments.get(i).get_numberOfRooms());
				data[i][5] = _apartments.get(i).get_clientName();
				data[i][6] = String.valueOf(_apartments.get(i).get_price());
				if (_apartments.get(i) instanceof SellApartment) {
					data[i][7] = String.valueOf(((SellApartment) _apartments.get(i)).get_offeredPrice());
					data[i][8] = ((SellApartment) _apartments.get(i)).get_entryDate();	
					data[i][9] = "";
					data[i][10] = "";
				} 
				else
				{
					data[i][7] = "";
					data[i][8] = "";
					data[i][9] = ((RentApartment) _apartments.get(i)).get_rentlStartDate();
					data[i][10] = ((RentApartment) _apartments.get(i)).get_rentalEndDate();
				}
			}
	    f=new JFrame();    
	       
	    JTable jt=new JTable(data,column);    
	    jt.setBounds(30,40,200,300);          
	    JScrollPane sp=new JScrollPane(jt);
	    f.add(sp);    
	    //f.setAlwaysOnTop(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setSize(800,800);    
	    f.setVisible(true);
	    
	    */
	    
		/*	
			
		*/
			
		} 
		catch (Exception e) {
			GUI_Operator.showGUI_Massage("Somthing went wrong, please try again");
		}
	}
	
	
	/**
	 * Sort all the apartments value in the list by price, the method uses Comparator
	 */
	public void sortByPrice() {
		Collections.sort(_apartments, new PriceComparator());
		GUI_Operator.showGUI_Massage("The apartments were successfully sorted");
	}
	
	
	/**
	 * Find specific apartment by ID
	 */
	public void findApartment() {
		
		// If an apartment with the same ID is found for the input that the user entered, the value will become true		
		String findID = GUI_Operator.getUserTextInput(
				"Please enter the ID of the requested apartment", 
				"Enter the ID of the requested apartment");
		
		// We will look for the ID from the input in the entire list of apartments, if we found 
		// the apartment the user will be able to choose actions he can do on that apartment
		if (isExist(Long.parseLong(findID))) {
			this.actionsOnApartment(getApartmentByID(findID));
		}
		else 
		{
			// we the boolean value of found is still false after the last iteration them we will send that massage
			GUI_Operator.showGUI_Massage("The Apartment is not in our database :(");			
		}
					 
	}

	/**
	 * Method that get ID and return the apartment with the ID
	 * 
	 * @param long ID - The input is the ID of the apartment we are looking for
	 * @return Apartment -The apartment with the ID 
	 */
	public Apartment getApartmentByID(String ID) {
		for (Apartment apartment : _apartments) {
			if (apartment.getID()== Long.parseLong(ID)) {
				return apartment;
			}
		}
		return null;
	}
	
	/** 
	 * Method that give the user option to take 3 action on apartment: Edit, Remove and Show details
	 * @param Apartment apartment - The apartment we want to take actions on
	 */
	public void actionsOnApartment(Apartment apartment) {
		
		// String array for the labels
		String[] options = {
				"Edit Apartment", 
				"Remove Apartment", 
				"Show Details"};
		
		// Now the user will be able to choose actions he can do on the apartment we found
		String userSelection = GUI_Operator.getUserSelection(
				options, 
				"Please select the action you want to take  on selected apartment", 
				"Chooses an cation"						
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
					GUI_Operator.showGUI_Massage("Successfully removed from the list");
				} catch (Exception e) {
					
					// if something went wrong while trying to remove the object from the list
					GUI_Operator.showGUI_Massage("The action failed, please try again");
				}
				
				break;
				
			case "Show Details":
				GUI_Operator.showGUI_Massage(apartment.toString());
				break;

			default:
				break;
		}
	}

}
