import GUI.DrinkClient;

import javax.swing.*;


public class Main{
	public static void main(String[] args)throws ClassNotFoundException{
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
			      	//new DrinkGUI().setVisible(true);
				new DrinkClient();//.setVisible(true);
			}
		});
	}
}
