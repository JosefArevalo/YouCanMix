package main.java.GUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.Drink.Drink;

public class ViewDrinkWindow extends YouCanMixState{

private DrinkClient DC;
	
	private JFrame displayDrinkFrame = new JFrame(); //DRINK MENU FRAME
	
	public ViewDrinkWindow(DrinkClient client, int k) {
		this.DC = client;
		viewDrink(k);
	}
	
	//INTERFACE THAT DISPLAYS CURRENT DRINK
	public void viewDrink(int s) {
		
		//getContentPane().removeAll();//clears frame
		
		displayDrinkFrame = new JFrame();//NEW FRAME TO DISPLAY CURRENT DRINK
		displayDrinkFrame.setLayout(new FlowLayout());
		displayDrinkFrame.setIconImage(DC.icon);
		displayDrinkFrame.setTitle("Drink Recipe");

		
		JPanel displayCocktail = new JPanel(new GridBagLayout());//panel to display list of drinks
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 2, 10, 2);       
        
        //ADDS INGREDIENTS AND QUANTITIES TO RESPECTIVE LISTS
		Drink drink = DC.currentDrinks.get(s);
        DC.currentIngredient = drink.getIngredients().split(",");
        DC.currentQuantity = drink.getQuantities().split(",");

        //LABEL THAT DISPLAYS THE CURRENT COCKTAIL NAME
        JLabel cocktailName = new JLabel("Cocktail Name: " + drink.getDrinkName());
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        displayCocktail.add(cocktailName, constraints); //ADDS LABEL TO PANEL
        
        while( DC.x <= DC.currentIngredient.length ) {//WHILE THERE ARE STILL INGREDIENTS TO DISPLAY
        	
        	//Display ingredients and quantities
        	JLabel ingredient = new JLabel("Ingredient " + DC.x + ": " + DC.currentIngredient[DC.x-1]);
        	JLabel quantity = new JLabel("Quantity: " + DC.currentQuantity[DC.x-1]);        	

        	constraints.gridx = 0;
            constraints.gridy = DC.x;
            displayCocktail.add(ingredient, constraints); //ADDS LABEL FOR INGREDIENT X
                 
            constraints.gridx = 1;
            constraints.gridy = DC.x;
            displayCocktail.add(quantity, constraints); //ADDS LABEL FOR QUANTITIY X
            
      	    DC.x++;
        }
        
        DC.ratingGUI(s);//GETS THE RATING OF THE DRINK 
        
        
        constraints.gridx = 0;
        constraints.gridy = DC.x;
        constraints.gridwidth = 10;
        displayCocktail.add(DC.rate, constraints);

        
        //add menu and exit button to panel
        constraints.gridx = 0;
        constraints.gridy = DC.x + 1;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.WEST;
        displayCocktail.add(DC.Back, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = DC.x + 1 ;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.EAST;
        displayCocktail.add(DC.Exit, constraints);
      			
        
        displayDrinkFrame.setSize(600, (DC.x*85));//SETS THE SIZE OF THE FRAME
                
        // add the panel to this frame
        displayDrinkFrame.add(displayCocktail);
        
        //SETS LOCATION AND VISIBILITY OF FRAME
        displayDrinkFrame.pack();
        displayDrinkFrame.setLocationRelativeTo(null);
        displayDrinkFrame.setVisible(true);
        displayDrinkFrame.validate();
      		
		
	}
	
	
	@Override
	public void nextWindow() {
		displayDrinkFrame.setVisible(false);
		DC.setWindow(DC.getValidationWindow());	
	}
	
	@Override
	public void exit() {
		System.exit(0);
	}
}
