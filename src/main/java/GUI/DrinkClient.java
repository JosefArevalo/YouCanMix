package main.java.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
//import java.awt.Menu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import main.java.Drink.Drink;
import main.java.SQL.DrinkManagerDAO;



public class DrinkClient{
	enum Choice{SEARCH, RATE, CREATE, TABLE, VIEW, MENU};
	Choice choice;
	
	protected JPanel RatingPanel; //ACTIVE RATING PANEL
	protected JPanel rate; //RATE PANEL
	protected JPanel search; //SEARCH PANEL
	
	//text fields to search/ enter info
	public JTextField findTextField = new JTextField(30);
    public JTextField textCocktailName = new JTextField(30);
	
	public String Parameter = "*";
	public boolean bk = true;//TRUE GOES BACK TO MENU AND FALSE GOES BACK TO SEARCH
	public boolean sr = true;//TRUE GOES TO RATE FRAME AND FALSE GOES TO DRINK TABLE
	private boolean active = true;//TOGGLES LIST SELECTION LISTENER
	private int select;//HOLDS THE SELECTION FROM THE TABLE
	public int x = 1; //ingredient counter
	private int num_Drinks = 100;
	protected String FullIngredient = "";//Holds all the ingredients
	protected String FullQuantity = "";  //quantities
	
	public String[] currentIngredient = new String[num_Drinks];
    public String[] currentQuantity = new String[num_Drinks];
    
    //TEXT FIELDS FOR DIFFERENT INGREDIENTS/QUANTITIES
    public JTextField tIngredient = new JTextField(30);
    public JTextField tQuantity = new JTextField(30);
    public JTextArea tInstructions = new JTextArea(10,30);
    
	
	private DrinkManagerDAO manager; //DATABASE OBJECT ACCESSOR
	private Drink currentDrink; //HOLDS THE CURRENT DRINK WE ARE WORKING WITH
	public ArrayList<Drink> currentDrinks; //HOLDS THE CURRENT DRINKS WE ARE WORKING WITH
	public Image icon = Toolkit.getDefaultToolkit().getImage("src/resources/icon.jpg");
	public Image star = Toolkit.getDefaultToolkit().getImage("src/resources/Star.jpg");    
	public Image NoStar = Toolkit.getDefaultToolkit().getImage("src/resources/NoStar.jpg");
	
	public int currentSize = 20;
	//CREATES A DEFAULT TABLE MODEL AND AN JTABLE
	public DefaultTableModel defaultTableModel = new DefaultTableModel();
	public JTable drinkTable = new JTable(defaultTableModel);
	
	
	public JButton Yes = new JButton("Yes");
    public JButton No = new JButton("No");
 	public JButton Menu = new JButton("Main Menu");
 	public JButton Exit = new JButton("Exit");
 	//public	JButton View = new JButton("View Drinks");
 	public	JButton Search = new JButton("Search For Drinks");
 	public	JButton Rate = new JButton("Rate Drinks");
 	public	JButton Create = new JButton("Create a Drink");
 	public	JButton findButton = new JButton("Search");
 	public	JButton Back = new JButton("Back");
 	public JButton Next = new JButton("Next Drink");
 	
 	//ADD DRINK BUTTONS
 	public JButton Enter = new JButton("Enter");
    public JButton moreIngredients = new JButton("More Ingredients");
	
	YouCanMixState ValidationWindow;
	YouCanMixState DrinkMenuWindow;
	YouCanMixState SearchDrinkWindow;
	YouCanMixState RateDrinkWindow;
	YouCanMixState CreateDrinkWindow;
	YouCanMixState ViewDrinkWindow;
	
	YouCanMixState state;
	
	public DrinkClient() {
		
		ValidationWindow = new ValidationWindow(this);
		
		state = ValidationWindow;
		

		try {
			this.manager = new DrinkManagerDAO();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
	

		Yes.addActionListener(new ActionListener() {//	WHEN YES BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				BuildTable();
				/*try {
			    	currentDrinks = manager.getDrinks(null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				fillTable();*/
				bk = true;
				state.nextWindow();
			}
		});
 		
 		No.addActionListener(new ActionListener() {//	WHEN NO BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				state.exit();
			}
		});
 		
 		Exit.addActionListener(new ActionListener() {//	WHEN EXIT BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				state.exit();
			}
		});
 		
 		Menu.addActionListener(new ActionListener() {//	WHEN MENU BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				deleteAllRows();
				bk = true;
				choice = Choice.MENU;
				state.nextWindow();
				//mainMenu();
			}
		});
 		
 		Search.addActionListener(new ActionListener() {//	WHEN SEARCH FOR DRINKS BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				bk = false;
				sr = false;
				choice = Choice.SEARCH;
				state.nextWindow();
				//searchDrinks();
			}
		});
 		
		Rate.addActionListener(new ActionListener() {//	WHEN RATE DRINKS BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				sr = true;
				choice = Choice.RATE;
				state.nextWindow();
				//rateDrinks();
			}
		});
		
		Create.addActionListener(new ActionListener() {//	WHEN CREATE DRINKS BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				choice = Choice.CREATE;
				state.nextWindow();
				//createDrinks();
			}
		});
		
		
		
		//NEEDS WORK------------------------------------------------------------------------------------
		findButton.addActionListener(new ActionListener() {//	WHEN SEARCH BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				deleteAllRows();
				Parameter = findTextField.getText();
				try {
					currentDrinks = manager.getDrinks(Parameter);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				findTextField.setText(null);
				if(sr == false) {
					fillTable();
					//searchDrinks();
					choice = Choice.SEARCH;
					state.nextWindow();
					Parameter = "*";
				}
				else {
					//System.out.println("Rating selection");
					choice = Choice.RATE;
					ratingSelection();
					state.nextWindow();
					//rateDrinks();
				}
				
			}
		}); 
		
		ListSelectionModel click = drinkTable.getSelectionModel();
		click.addListSelectionListener(new ListSelectionListener() {// WHEN AN ROW IS SELECTED
			@Override
	        public void valueChanged(ListSelectionEvent le) {
				if(active) {
					select = click.getMinSelectionIndex();
					System.out.println("Selection: " + select);
					if(select >= 0 && select < 100) {
						choice = Choice.VIEW;
						state.nextWindow();
					}
						//viewDrink(select);
				}
	        }
	    });
		Back.addActionListener(new ActionListener() {//	WHEN BACK BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				deleteAllRows();
				if(bk == true)choice = Choice.MENU;
				else choice = Choice.SEARCH;	
				state.nextWindow();
				//searchDrinks();
				Parameter = "*";
				x = 1;
			}
		});
		
		Next.addActionListener(new ActionListener() {//	WHEN NEXT DRINK BUTTON IS PRESSED
			@Override
			public void actionPerformed(ActionEvent ae) {
				/*
				x++;
				if (x <= manager.getCurrentSize()) {
					ratingSelection();
					rateDrinks();
				}
				else {
					noDrinkError();
					x = 1;
					rateDrinks();
				}*/
			}
		}); 
		
		moreIngredients.addActionListener(new ActionListener() {//WHEN MORE INGREDIENTS BUTTON IS PRESSED
	        @Override
	        public void actionPerformed(ActionEvent event) {
	    		x++;
	    		FullIngredient += tIngredient.getText() + ", ";
	    		FullQuantity += tQuantity.getText() + ", ";
	    		tIngredient.setText(null);//clears out ingredient and quantity text box
		        tQuantity.setText(null);
	    		if(x < 10) {//createDrinks();//LIMITS THE AMOUNT OF INGREDIENTS THAT CAN BE ADDED
					choice = Choice.CREATE;
					state.nextWindow();
	    		}
	    		else Error();
	        }
	    });
		Enter.addActionListener(new ActionListener() {//WHEN ENTER BUTTON IS PRESSED
	        @Override
	        public void actionPerformed(ActionEvent event) {
	        	
	        	//PUTS ALL INGREDIENTS/QUANTITIES EACH INTO ONE STRING
	        	FullIngredient += tIngredient.getText(); 
	        	FullQuantity += tQuantity.getText();
	        	System.out.print(tInstructions);
		        try {
		        	//MAKES NEW DRINK AND PUTS IT INTO CURRENT DRINK
					currentDrink = new Drink(textCocktailName.getText(), FullIngredient, 
							FullQuantity, 5, tInstructions.getText() );
					
					//CONNECTS TO THE DATABASE AND INSERTS NEW DRINK INTO IT
					//RETURNS IF IT WAS ADDED OR NOT
					manager.insertDrink(currentDrink);
					drinkAdded();
				} catch (SQLException e) {//CATCHES ERROR
					drinkNotAdded();
					e.printStackTrace();

				}
		        
		        //clears out ingredient, quantity and cocktail name text box
		        tIngredient.setText(null);
		        tQuantity.setText(null);
		        textCocktailName.setText(null);
		        
		        //clear out FullIngredient, FullQuantity and resets x counter
		        FullIngredient = "";
		        FullQuantity = "";
				x = 1;
				//mainMenu();
	        }
	    });
	}

	
	
	public YouCanMixState getValidationWindow() {
		ValidationWindow = new ValidationWindow(this);
		return ValidationWindow;
	}
	
	public YouCanMixState getMenuWindow() {
		try {
	    	currentDrinks = manager.getDrinks(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fillTable();
		DrinkMenuWindow = new DrinkMenuWindow(this);
		return DrinkMenuWindow;
	}
	
	public YouCanMixState getSearchWindow() {
		SearchDrinkWindow = new SearchDrinkWindow(this);
		return SearchDrinkWindow;
	}
	
	public YouCanMixState getRateDrinkWindow() {
		RateDrinkWindow = new RateDrinkWindow(this);
		return RateDrinkWindow;
	}
	
	public YouCanMixState getCreateDrinkWindow() {
		CreateDrinkWindow = new CreateDrinkWindow(this);
		return CreateDrinkWindow;
	}
	
	public YouCanMixState getViewDrinkWindow() {
		ViewDrinkWindow = new ViewDrinkWindow(this, select);
		return ViewDrinkWindow;
	}
	
	public void setWindow(YouCanMixState state) {
		this.state = state;
	}
	
	//MAKE AN JTABLE THAT HAS A COLUMN FOR DRINK NAME AND INGREDIENTS
	public void BuildTable() {

			
		//makes Table to Display all the drinks
		drinkTable.setPreferredScrollableViewportSize(new Dimension(600, 200));
		drinkTable.setFillsViewportHeight(true);
		defaultTableModel.addColumn("Drink Name");
		defaultTableModel.addColumn("Ingredients");    
		       	
		//sets the width of each column
		TableColumnModel colModel = drinkTable.getColumnModel();
		TableColumn col = colModel.getColumn(0);
		col.setPreferredWidth(200);
		col = colModel.getColumn(1);
		col.setPreferredWidth(200);
	}
	
	//FILLS THE JTABLE THAT HAS A COLUMN FOR DRINK NAME AND INGREDIENTS 
	public void fillTable() {
		if (this.currentDrinks.isEmpty()) {
			noDrinkError();
		}
		else {
			for (Drink d : this.currentDrinks) {
				defaultTableModel.addRow(new Object[]{d.getDrinkName(),
						d.getIngredients()});
			}
		}
	}
	
	//CLEARS THE JTABLE AND ASSOCIATED VALUES
	public void deleteAllRows() {
		active = false;
		for(int g = currentIngredient.length-1; g >= 0; g--) {
			currentIngredient[g] = "";
			currentQuantity[g] = "";
		}
		while(defaultTableModel.getRowCount() > 0) {
			defaultTableModel.removeRow(0);
		}
		currentSize = 0;
		active = true;
		Parameter = "*";
		x = 1;
	}


	//CREATES A PANEL THAT HOLDS THE SEARCH BAR
	public void searchBar() {
	
		search = new JPanel(new GridBagLayout());//PANEL TO DISPLAY THE SEARCH BAR
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		JLabel findLabel = new JLabel("Search:");//LABEL TO DISPLAY SEARCH
    
		constraints.gridx = 0;
		constraints.gridy = 0;     
		search.add(findLabel, constraints);//ADD LABEL TO PANEL

		constraints.gridx = 1;
		search.add(findTextField, constraints);//ADD TEXT FIELD TO PANEL
    
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.EAST;
		search.add(findButton, constraints);//ADDS BUTTON TO SEARCH FOR DRINKS TO PANEL
 
	}
	
	//CREATES A PANEL THAT HOLD THE DRINK rate
	public void ratingGUI(int s) {
		
		rate = new JPanel(new GridBagLayout());//PANEL TO DISPLAY THE RATE OF A DRINK
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
		
        //CREATES AND ADD rate LABEL TO PANEL
		JLabel title = new JLabel("Rating: ");
        constraints.gridx = 0;
        rate.add(title);
        
        //CREATES AND ADDS APPROPRIATE RATING ICON LABEL TO PANEL
        for(int x = 1; x <= 5; x++) {
        	JLabel Rate = new JLabel();
        	if(x <= currentDrinks.get(s).getRating()) {
        		Rate.setIcon(new ImageIcon(star));
           		constraints.gridx = x;
           		rate.add(Rate);
        	}
        	else {
        		Rate.setIcon(new ImageIcon(NoStar));
           		constraints.gridx = x;
           		rate.add(Rate);
        	}
        	
        }
		
	}
	
	public void ratingSelection(){
		
		RatingPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
		
        JLabel title = new JLabel("Cocktail Name:  " + 
        		currentDrinks.get(x-1).getDrinkName() + "    Rate: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        RatingPanel.add(title, constraints);
        
        
        JButton one = new JButton();
        one.setIcon(new ImageIcon(NoStar));
					
        constraints.gridx = 1;
        RatingPanel.add(one);
        one.addActionListener(new ActionListener() {//WHEN ONE STAR IS PRESSED
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		try {
					manager.insertRate(currentDrinks.get(x-1), 1);
					rateAdded();
				} catch (SQLException e) {
					rateNotAdded();
					e.printStackTrace();
				}
        	}
				
        });
        
        
        JButton two = new JButton();
        two.setIcon(new ImageIcon(NoStar));
					
        constraints.gridx = 2;
        RatingPanel.add(two);
        two.addActionListener(new ActionListener() {//WHEN TWO STAR IS PRESSED
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		try {
        			manager.insertRate(currentDrinks.get(x-1), 2);
					rateAdded();
				} catch (SQLException e) {
					rateNotAdded();
					e.printStackTrace();
				}
        	}
        	
        });
        
        
        JButton three = new JButton();
        three.setIcon(new ImageIcon(NoStar));
					
        constraints.gridx = 3;
        RatingPanel.add(three);
        three.addActionListener(new ActionListener() {//WHEN THREE STAR IS PRESSED
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		try {
        			manager.insertRate(currentDrinks.get(x-1), 3);
					rateAdded();
				} catch (SQLException e) {
					rateNotAdded();
					e.printStackTrace();
				}
        	}
				
        });
        
        
        JButton four = new JButton();
        four.setIcon(new ImageIcon(NoStar));
					
        constraints.gridx = 4;
        RatingPanel.add(four);
        four.addActionListener(new ActionListener() {//WHEN FOUR STAR IS PRESSED
        	@Override
        	public void actionPerformed(ActionEvent event) {
				try {
					manager.insertRate(currentDrinks.get(x - 1), 4);
					rateAdded();
				} catch (SQLException e) {
					rateNotAdded();
					e.printStackTrace();
				}
			}
        });
        
        
        JButton five = new JButton();
        five.setIcon(new ImageIcon(NoStar));
					
        constraints.gridx = 5;
        RatingPanel.add(five);
        five.addActionListener(new ActionListener() {//WHEN FIVE STAR IS PRESSED
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		try {
        			manager.insertRate(currentDrinks.get(x-1), 5);
					rateAdded();
				} catch (SQLException e) {
					rateNotAdded();
					e.printStackTrace();
				}  
        	}
				
        });
                
        constraints.gridx = 6;
        constraints.gridy = 6;
        constraints.gridwidth = 5;
        constraints.anchor = GridBagConstraints.CENTER;
        RatingPanel.add(Next);
		
	}    
	
	//ERROR MESSAGE WHEN USER TRIES TO ADD TOO MANY INGREDIENTS
	public void Error() {
		JFrame eFrame = new JFrame();
        JOptionPane.showMessageDialog(eFrame, "Can't Add Anymore Ingredients!", "ERROR", JOptionPane.ERROR_MESSAGE);	
	}
	
	//ERROR MESSAGE WHEN NO MATCHING DRINKS ARE FOUND 
	public void  noDrinkError() {
		JFrame nDEFrame = new JFrame();
        JOptionPane.showMessageDialog(nDEFrame, "No matching drinks found", "ERROR", JOptionPane.ERROR_MESSAGE);
        Parameter = "*";
	}
	
	//ERROR MESSAGE IF DRINK WAS NOT ADDED TO DATABASE
	public void drinkNotAdded() {
		JFrame dNAFrame = new JFrame();
        JOptionPane.showMessageDialog(dNAFrame, "Drink.Drink Not Added!", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	//SUCCESS MESSAGE IF DRINK WAS ADDED TO DATABASE
	public void drinkAdded() {
		JFrame dAFrame = new JFrame();
        JOptionPane.showMessageDialog(dAFrame, "Drink.Drink Succesfully Added to Database!", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
	}
	
	//ERROR MESSAGE IF RATING WAS NOT ADDED TO DATABASE
	public void rateNotAdded() {
		JFrame rNAFrame = new JFrame();
        JOptionPane.showMessageDialog(rNAFrame, "Rating Not Added!", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	//SUCCESS MESSAGE IF RATING WAS ADDED TO DATABASE
	public void rateAdded() {
		JFrame rAFrame = new JFrame();
        JOptionPane.showMessageDialog(rAFrame, "Rating Succesfully Added to Database!", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
	}
}