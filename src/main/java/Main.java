import javax.swing.*;

import GUI.DrinkGUI;


public class Main{
	//starts the program
	
	
	public static void main(String[] args)throws ClassNotFoundException{
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
			      	new DrinkGUI().setVisible(true);
			}
		});
	}
}
