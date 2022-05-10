package SQL;

import Drink.Drink;
import Drink.DrinkBuilder;

import java.sql.*;
import java.util.ArrayList;

public abstract class AbstractSQLDatabase implements IDatabase {
    protected Connection conn;

    protected AbstractSQLDatabase() {

    }

    @Override
    public void connect() throws SQLException {
        throw new RuntimeException("Unimplemented connection logic for AbstractSQLDatabase!");
    }

    @Override
    public void addDrink(Drink drink) throws SQLException {
        //MAKES ENTRY PROMPT
        String SQL = "insert into drinks values (?,?,?,?,?);";
        PreparedStatement stmt = this.conn.prepareStatement(SQL);

        //ENTERS DATA INTO PROPER COLUMN
        stmt.setString(1, drink.getDrinkName());
        stmt.setString(2, drink.getIngredients());
        stmt.setString(3, drink.getQuantities());
        stmt.setInt(4, drink.getRating());
        stmt.setString(5, drink.getInstructions());

        //PUSHS/CHECKS THAT DATABASE HAS BEEN UPDATED
        stmt.executeUpdate();
    }

    @Override
    public void addRating(Drink drink, int rating) throws SQLException {
        //MAKES ENTRY PROMPT
        String SQL = "update drinks set Rating = ? where Drink_Name = ?";
        PreparedStatement stmt = this.conn.prepareStatement(SQL);

        //ENTERS DATA INTO PROPER COLUMN
        stmt.setInt(1, rating);
        stmt.setString(2, drink.getDrinkName());

        //PUSHS/CHECKS THAT DATABASE HAS BEEN UPDATED
        stmt.executeUpdate();
    }

    @Override
    public ArrayList<Drink> getDrinks(String optional_search) throws SQLException {
        ArrayList<Drink> drinks = new ArrayList<>();
        Statement stmt = this.conn.createStatement();

        ResultSet rs;
        if (optional_search == null) {
            rs = stmt.executeQuery("SELECT * FROM drinks");
        }
        else {
            rs = stmt.executeQuery("SELECT * FROM drinks WHERE Ingredients LIKE \"%" +
                    optional_search +"%\" or Drink_Name LIKE \"%" + optional_search +"%\"");
        }

        while (rs.next()) {
            Drink d = new DrinkBuilder(rs.getString("Drink_Name"),
                    rs.getString("Ingredients"), rs.getString("Quantity"))
                    .withInstruction(rs.getString("Instructions"))
                    .withRating(rs.getInt("Rating"))
                    .build();
            drinks.add(d);
        }

        return drinks;
    }

    void createTables() throws SQLException {
        String create_table_query = "create table if not exists drinks ( " +
                "Drink_Name varchar(30) not null, " +
                "Ingredients varchar(100) not null, " +
                "Quantity varchar(100) not null," +
                "Rating integer not null, " +
                "Instructions varchar(300) not null," +
                "primary key(Drink_Name))";

        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(create_table_query);
    }
}
