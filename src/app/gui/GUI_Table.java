package app.gui;


import java.awt.Dialog;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;

import app.entity.apartment.Apartment;
import app.entity.apartment.RentApartment;
import app.entity.apartment.SellApartment;

/**
 * class that represent a table that contain all of the apartments data 
 */
public class GUI_Table {
		/**
		 * @param JFrame - GUI frame
		 */
	 	JFrame frame;
	 	
	 	/**
	 	 * @param JTable - Data table designed for use with GUI 
	 	 */
	 	JTable table;
	 	/**
	 	 * @param Dialog - Top level window that can contain a JTable
	 	 */
	 	Dialog dialog;
	 	
	 	/**
	 	 * @param ArrayList<Apartment> - list of apartment from the manager class 
	 	 */
	    public GUI_Table(ArrayList<Apartment> _apartments){ 
	    	
	    	//set the columns of the table
			String[] column = {
					"Type",
					"ID",
					"Address",
					"Square Meter",
					"Number Of Rooms",
					"Client Name",
					"Price",
					"Offered Price",
					"Entry Date",
					"Rental Start Date",
					"Rental End Date",
				};
			
			// set the table's values
			String[][] data = new String[_apartments.size()+1][11];
			for (int i = 0; i < column.length; i++) {
				data[0][i] = column[i];
			}
			
			for (int i = 0, j = 1; i < _apartments.size(); i++,j++) {
				
				data[j][0] = _apartments.get(i).getType();
				data[j][1] = String.valueOf(_apartments.get(i).getID());
				data[j][2] = _apartments.get(i).get_address();
				data[j][3] = String.valueOf(_apartments.get(i).get_squareMeter());
				data[j][4] = String.valueOf(_apartments.get(i).get_numberOfRooms());
				data[j][5] = _apartments.get(i).get_clientName();
				data[j][6] = String.valueOf(_apartments.get(i).get_price());
				if (_apartments.get(i) instanceof SellApartment) {
					data[j][7] = String.valueOf(((SellApartment) _apartments.get(i)).get_offeredPrice());
					data[j][8] = ((SellApartment) _apartments.get(i)).get_entryDate();	
					data[j][9] = "------";
					data[j][10] = "------";
				} 
				else
				{
					data[j][7] = "------";
					data[j][8] = "------";
					data[j][9] = ((RentApartment) _apartments.get(i)).get_rentlStartDate();
					data[j][10] = ((RentApartment) _apartments.get(i)).get_rentalEndDate();
				}
			}
		
		// Set the frame value and run the GUI	
	    frame=new JFrame();    
	    table=new JTable(data,column); 
	    table.setRowHeight(30);
	    
	    // Adjust the table columns size
	    setJTableColumnsWidth(table,1200,40,10,95,30,40,30,30,30,30,35,35);
	    JScrollPane sp=new JScrollPane(table);;
	    frame.add(sp);    
	    
	    // Create a new dialog GUI with the frame and the table we just created
	    MyDialog md = new MyDialog(frame,table);
	    }
		
	    /**
	     * Method that adjust the column of the table
	     * 
	     * @param JTable table - The table we want to adjust 
	     * @param int tablePreferredWidth - The size of the Preferred Width
	     * @param double percentages - percentages of the Preferred Width  
	     */
	    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
	            double... percentages) {
	        double total = 0;
	        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	            total += percentages[i];
	        }
	     
	        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	            TableColumn column = table.getColumnModel().getColumn(i);
	            column.setPreferredWidth((int)
	                    (tablePreferredWidth * (percentages[i] / total)));
	        }
	    }
	    
	    
}
