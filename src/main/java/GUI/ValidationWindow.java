package main.java.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ValidationWindow extends YouCanMixState{
	
	private DrinkClient DC;
	
	private JFrame ValidationFrame = new JFrame(); //VALIDATE FRAME
	
	public ValidationWindow(DrinkClient client) {
		this.DC = client;
		Verify();
	}
	
	//INTERFACE TO VERIFY USERS AGE
	public void Verify() {
		ValidationFrame.setTitle("YouCanMix");
		
		JPanel verify = new JPanel(new GridBagLayout());	//panel to display menu
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        JLabel age = new JLabel("Are You 21 Year's of Age or Older?");
        
        constraints.gridx = 0;
        constraints.gridy = 0;  
        verify.add(age, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.WEST;
        verify.add(DC.Yes, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.EAST;
        verify.add(DC.No, constraints);
        
        // set border for the panel
        verify.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Verify"));
         
        // add the panel to this frame
        ValidationFrame.add(verify);
         
        ValidationFrame.pack();
        ValidationFrame.setLocationRelativeTo(null);
        ValidationFrame.setVisible(true);
        		
	}
	
	@Override
	public void nextWindow() {
		ValidationFrame.setVisible(false);
		DC.setWindow(DC.getMenuWindow());
	}
	
	@Override
	public void exit() {
		System.exit(0);
	}
}
