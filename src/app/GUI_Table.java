package app;


import java.awt.Dialog;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import app.entity.apartment.Apartment;
import app.entity.apartment.RentApartment;
import app.entity.apartment.SellApartment;

/**
 * class that represent a table that contain all of the apartments data 
 */
public class GUI_Table {
	 	JFrame frame; 
	 	JTable table;
	 	Dialog dialog;
	 	
	 	/**
	 	 * 
	 	 * @param ArrayList<Apartment> _apartments 
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
			
			// set the table values
			String[][] data = new String[_apartments.size()+1][11];
			for (int i = 0; i < column.length; i++) {
				data[0][i] = column[i];
			}
			
			for (int i = 0, j = 0; i < _apartments.size(); i++,j++) {
				data[i+1][0] = _apartments.get(i).getType();
				data[i+1][1] = String.valueOf(_apartments.get(i).getID());
				data[i+1][2] = _apartments.get(i).get_address();
				data[i+1][3] = String.valueOf(_apartments.get(i).get_squareMeter());
				data[i+1][4] = String.valueOf(_apartments.get(i).get_numberOfRooms());
				data[i+1][5] = _apartments.get(i).get_clientName();
				data[i+1][6] = String.valueOf(_apartments.get(i).get_price());
				if (_apartments.get(i) instanceof SellApartment) {
					data[i+1][7] = String.valueOf(((SellApartment) _apartments.get(i)).get_offeredPrice());
					data[i+1][8] = ((SellApartment) _apartments.get(i)).get_entryDate();	
					data[i+1][9] = "";
					data[i+1][10] = "";
				} 
				else
				{
					data[i][7] = "";
					data[i][8] = "";
					data[i][9] = ((RentApartment) _apartments.get(i)).get_rentlStartDate();
					data[i][10] = ((RentApartment) _apartments.get(i)).get_rentalEndDate();
				}
			}
		
		
		// set the frame value and run the GUI	
	    frame=new JFrame();    
	    table=new JTable(data,column); 
	    table.setRowHeight(30);
	    setJTableColumnsWidth(table,1200,40,10,95,30,40,30,30,30,30,35,30);
	    JScrollPane sp=new JScrollPane(table);;
	    frame.add(sp);    
	    MyDialog md = new MyDialog(frame,table);
	    }
		
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
