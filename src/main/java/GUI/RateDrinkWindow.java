package main.java.GUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RateDrinkWindow extends YouCanMixState{

private DrinkClient DC;
	
	private JFrame rateDrinkFrame = new JFrame(); //DRINK MENU FRAME
	
	public RateDrinkWindow(DrinkClient client) {
		this.DC = client;
		rateDrinks();
	}	

	//interface for user to rate drinks
	public void rateDrinks() { 
	
		//getContentPane().removeAll();//clears frame
		//setVisible(false);
		
		//Rate Drinks
		rateDrinkFrame = new JFrame();//NEW FRAME TO DISPLAY RATE DRINKS
		rateDrinkFrame.setLayout(new FlowLayout());
		rateDrinkFrame.setIconImage(DC.icon);
		rateDrinkFrame.setTitle("YouCanMix");
		
		JPanel rating = new JPanel(new GridBagLayout());//panel to display list of drinks
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
		
		DC.searchBar();//GETS PANEL THAT DISPLAYS SEARCH BAR
		
		constraints.gridx = 0;
        constraints.gridy = 0;
        rating.add(DC.search, constraints);//ADDS SEARCH BAR TO SEARCHING PANEL
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        if(DC.Parameter != "*") {//IF SOMETHING HAS BEEN SEARCHED FOR
        	rating.add(DC.RatingPanel, constraints);//ADD TABLE OF RELEVANT DRINKS
		}
		else {//SETS BORDER TO THE SEARCHING PANEL
			rating.setBorder(BorderFactory.createTitledBorder(
	                BorderFactory.createEtchedBorder(), "Rating Drinks"));
		}   
		
		//add menu and exit button to panel
		constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.WEST;
        rating.add(DC.Menu, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.EAST;
        rating.add(DC.Exit, constraints);
			
        rateDrinkFrame.setSize(600, (DC.x*85));//SETS THE SIZE OF THE FRAME
        
        // add the panel to this frame
        rateDrinkFrame.add(rating);
         
      //SETS FRAME POSTITION TO THE MIDDLE OF THE SCREEN AND VISIBLE
        rateDrinkFrame.pack();
        rateDrinkFrame.setLocationRelativeTo(null);
        rateDrinkFrame.setVisible(true);
        rateDrinkFrame.validate();
	}
	
	@Override
	public void nextWindow() {
		rateDrinkFrame.setVisible(false);
		switch(DC.choice) {
		case RATE:
			DC.setWindow(DC.getRateDrinkWindow());
			return;
		case MENU:
			DC.setWindow(DC.getMenuWindow());
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
