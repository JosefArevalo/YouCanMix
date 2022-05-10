package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateDrinkWindow extends YouCanMixState {

private DrinkClient DC;
	
	private JFrame CreateDrinkFrame = new JFrame(); //DRINK MENU FRAME
	
	public CreateDrinkWindow(DrinkClient client) {
		this.DC = client;
		createDrinks();
	}	
	
	//interface for user to create a drink
	public void createDrinks() { 
		
		//getContentPane().removeAll();//clears frame

		//Create Drinks		
		JPanel creating = new JPanel(new GridBagLayout());//panel to prompt user entry		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
		
		//Display prompt for cocktail name
		JLabel cocktailName = new JLabel("Cocktail Name:");

		//Display prompt for ingredients
	    JLabel ingredient = new JLabel("Enter Ingredient " + DC.x + ": ");
	    JLabel quantity = new JLabel("Enter Ingredient " + DC.x + "\'s Quantity: ");
	    
	    //Display prompt for cocktail name
	    JLabel instructions = new JLabel("Cocktail Instructions:");
	    
	    //initial components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;     
        creating.add(cocktailName, constraints); //LABEL FOR NAME OF DRINK
 
        constraints.gridx = 1;
        creating.add(DC.textCocktailName, constraints); //TEXT BOX FOR NAME OF DRINK
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        creating.add(ingredient, constraints); //LABEL FOR INGREDIENT X
         
        constraints.gridx = 1;
        creating.add(DC.tIngredient, constraints);//TEXT BOX FOR INGREDIENT X
        
        constraints.gridx = 0;
        constraints.gridy = 2;     
        creating.add(quantity, constraints); //LABEL FOR QUANTITIY X
        
        constraints.gridx = 1;
        creating.add(DC.tQuantity, constraints); //TEXT BOX FOR QUANTITIY X
        
        constraints.gridx = 2;
        creating.add(DC.moreIngredients, constraints); //BUTTON TO ADD MORE INGREDIENTS
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        creating.add(instructions, constraints);
        
        DC.tInstructions.setLineWrap(true);
        DC.tInstructions.setWrapStyleWord(true);

        constraints.gridx = 1;
        constraints.gridy = 3;
        creating.add(DC.tInstructions, constraints);
        
        //PUTS THE ENTER BUTTON IN THE MIDDLE OF THE PANEL
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        //constraints.anchor = GridBagConstraints.CENTER;
        creating.add(DC.Enter, constraints);
        
        //ADDS MENU AND EXIT BUTTON TO THE BOTTOM OF THE PANEL
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.WEST;
        creating.add(DC.Menu, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.EAST;
        creating.add(DC.Exit, constraints);
         
        // set border for the panel
        creating.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Create a Drink"));
         
        // add the panel to this frame
        CreateDrinkFrame.add(creating);
        
        //SETS THE LOCATION OF THE FRAME
        CreateDrinkFrame.pack();
        CreateDrinkFrame.setLocationRelativeTo(null);
        CreateDrinkFrame.setVisible(true);
	 
	}
	
	@Override
	public void nextWindow() {
		CreateDrinkFrame.setVisible(false);
		switch(DC.choice) {
		case MENU:
			DC.setWindow(DC.getMenuWindow());
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
