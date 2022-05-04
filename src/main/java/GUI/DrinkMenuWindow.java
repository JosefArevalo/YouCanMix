package main.java.GUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DrinkMenuWindow extends YouCanMixState{

private DrinkClient DC;
	
	private JFrame DrinkMenuFrame = new JFrame(); //DRINK MENU FRAME
	
	public DrinkMenuWindow(DrinkClient client) {
		this.DC = client;
		mainMenu();
	}
	
	
	//INTERFACE FOR THE MAIN MENU
	public void mainMenu() {	
		
		//getContentPane().removeAll();//clears frame

		//Main menu
		
		DrinkMenuFrame.setLayout(new FlowLayout());
		DrinkMenuFrame.setIconImage(DC.icon);
		DrinkMenuFrame.setTitle("YouCanMix");
		
		JPanel menu = new JPanel(new GridBagLayout());	//panel to display menu
		
        menu.add(new JScrollPane(DC.drinkTable));//ADDS THE DRINK TABLE TO THE PANEL
			
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // add components to the panel
        //constraints.gridx = 0;
        //constraints.gridy = 0;     
        //menu.add(DC.View, constraints);
 
        constraints.gridx = 0;
        constraints.gridy = 1; 
        constraints.anchor = GridBagConstraints.WEST;
        menu.add(DC.Search, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1; 
        constraints.anchor = GridBagConstraints.CENTER;
        menu.add(DC.Rate, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1; 
        constraints.anchor = GridBagConstraints.EAST;
        menu.add(DC.Create, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        menu.add(DC.Exit, constraints);
         
        // set border for the panel
        menu.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Main Menu"));
        
        DrinkMenuFrame.setSize(500, (DC.currentSize*75));//SETS THE SIZE OF THE FRAME
         
        // add the panel to this frame
        DrinkMenuFrame.add(menu);
        
        DrinkMenuFrame.setVisible(true);
        DrinkMenuFrame.pack();
        DrinkMenuFrame.setLocationRelativeTo(null);
        DrinkMenuFrame.validate();
	}
	
	@Override
	public void nextWindow() {
		DrinkMenuFrame.setVisible(false);
		switch(DC.choice) {
		case SEARCH:
			DC.setWindow(DC.getSearchWindow());
			return;
		case RATE:
			DC.setWindow(DC.getRateDrinkWindow());
			return;
		case CREATE:
			DC.setWindow(DC.getCreateDrinkWindow());
			return;
		default:
			break;
		}	
	}
	
	@Override
	public void exit() {
		System.exit(0);
	}
}
