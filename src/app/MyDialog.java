package app;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 * MyDialog is a GUI Dialog window with table of data
 */
public class MyDialog extends JDialog {
	
	/**
	 * 
	 * @param frame - Frame that going to contain the table for the GUI
	 * @param JTable table - Table with all the data from the apartments
	 */
	public MyDialog( JFrame frame, JTable table) {
        super( frame, "Show All Apartments", true );

        Container c = getContentPane();
        c.setLayout( new FlowLayout() );
        c.add( table );
        this.pack();
        this.setLocationRelativeTo(c.getParent());
        this.show();
	}
}
