package GUI;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SearchDrinkWindow extends YouCanMixState{

private DrinkClient DC;
	
	private JFrame SearchDrinkFrame = new JFrame(); //DRINK MENU FRAME
	
	public SearchDrinkWindow(DrinkClient client) {
		this.DC = client;
		searchDrinks();
	}

	//INTERFACE THAT DISPLAYS SEARCH BAR AND SEARCH RESULTS
	public void searchDrinks() { 
		
		//getContentPane().removeAll();//clears frame
		//setVisible(false);

		//Search for drinks
		SearchDrinkFrame = new JFrame();//NEW FRAME TO DISPLAY SEARCH FOR DRINKS
		SearchDrinkFrame.setLayout(new FlowLayout());
		SearchDrinkFrame.setIconImage(DC.icon);
		SearchDrinkFrame.setTitle("YouCanMix");
			
		JPanel searching = new JPanel(new GridBagLayout());//panel to display list of drinks
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
		
		DC.searchBar();//GETS PANEL THAT DISPLAYS SEARCH BAR
		
		constraints.gridx = 0;
        constraints.gridy = 0;
        searching.add(DC.search, constraints);//ADDS SEARCH BAR TO SEARCHING PANEL
        
        constraints.gridx = 0;
        constraints.gridy = 1;
		if(DC.Parameter != "*") {//IF SOMETHING HAS BEEN SEARCHED FOR
			searching.add(new JScrollPane(DC.drinkTable), constraints);//ADD TABLE OF RELEVANT DRINKS
		}
		else {//SETS BORDER TO THE SEARCHING PANEL
			searching.setBorder(BorderFactory.createTitledBorder(
	                BorderFactory.createEtchedBorder(), "Search For Drinks"));
		}
		
		//ADD MENU AND EXIT BUTTON TO PANEL
		constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.WEST;
        searching.add(DC.Menu, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.EAST;
        searching.add(DC.Exit, constraints);
			
        SearchDrinkFrame.setSize(800, (DC.currentSize*75));//SETS THE SIZE OF THE FRAME
        
        //add the panel to this frame
        SearchDrinkFrame.add(searching);
         
        //SETS FRAME POSTITION TO THE MIDDLE OF THE SCREEN AND VISIBLE
        SearchDrinkFrame.pack();
        SearchDrinkFrame.setLocationRelativeTo(null);
        SearchDrinkFrame.setVisible(true);
        SearchDrinkFrame.validate();
		
	}
	
	
	@Override
	public void nextWindow() {
		SearchDrinkFrame.setVisible(false);
		switch(DC.choice) {
		case SEARCH:
			DC.setWindow(DC.getSearchWindow());
			return;
		case MENU:
			DC.setWindow(DC.getMenuWindow());
			return;
		case VIEW:
			DC.setWindow(DC.getViewDrinkWindow());
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
