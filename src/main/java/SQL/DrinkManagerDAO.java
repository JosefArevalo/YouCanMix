package SQL;

import Drink.Drink;

import java.sql.*;
import java.util.ArrayList;

public class DrinkManagerDAO
{
	private Connection conn = null;

	public DrinkManagerDAO() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/youcanmix";
        String userId = "Mixer";
        String password = "Mixer1";

        this.conn = DriverManager.getConnection(dbURL, userId, password);
        this.createTables();
  }

    public void createTables() throws SQLException {
        String create_table_query = "create table if not exists drinks ( " +
                "Drink_Name varchar(30) not null, " +
                "Ingredients varchar(100) not null, " +
                "Quantity varchar(100) not null," +
                "Rating integer not null, " +
                "primary key(Drink_Name))";

        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(create_table_query);
    }

    //CONNECTS AND INSERTS DRINK INTO THE DATABASE
    public void insertDrink(Drink currentDrink) throws SQLException
    {
        //MAKES ENTRY PROMPT
        String SQL = "insert into drinks values (?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(SQL);

        //ENTERS DATA INTO PROPER COLUMN
        stmt.setString(1, currentDrink.getDrinkName());
        stmt.setString(2, currentDrink.getIngredients());
        stmt.setString(3, currentDrink.getQuantities());
        stmt.setInt(4, currentDrink.getRating());

        //PUSHS/CHECKS THAT DATABASE HAS BEEN UPDATED
        stmt.executeUpdate();
    }
    
    public void insertRate(Drink currentDrink, int r) throws SQLException {
        //MAKES ENTRY PROMPT
        String SQL = "update drinks set Rating = ? where Drink_Name = ?";
        PreparedStatement stmt = this.conn.prepareStatement(SQL);

        //ENTERS DATA INTO PROPER COLUMN
        stmt.setInt(1, r);
        stmt.setString(2, currentDrink.getDrinkName());

        //PUSHS/CHECKS THAT DATABASE HAS BEEN UPDATED
        stmt.executeUpdate();
    }
    
    //GETS DRINKS FROM THE DATABASE
    public ArrayList<Drink> getDrinks(String Parameter) throws SQLException {
        ArrayList<Drink> drinks = new ArrayList<>();
    	try {
	    	Statement stmt = this.conn.createStatement();

            ResultSet rs;
            if (Parameter == null) {
                rs = stmt.executeQuery("SELECT * FROM drinks");
            }
            else {
                rs = stmt.executeQuery("SELECT * FROM drinks WHERE Ingredients LIKE \"%" +
                        Parameter +"%\" or Drink_Name LIKE \"%" + Parameter +"%\"");
            }

	    	while ( rs.next() ) {
	    		 Drink d = new Drink(rs.getString("Drink_Name"),
	    				 rs.getString("Ingredients"), rs.getString("Quantity"),
	    				 rs.getInt("Rating"));
                 drinks.add(d);
	    	}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return drinks;
    }
}