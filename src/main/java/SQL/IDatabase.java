package SQL;

import Drink.Drink;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabase {
    void connect() throws SQLException;
    void addDrink(Drink drink) throws SQLException;
    void addRating(Drink drink, int rating) throws SQLException;
    ArrayList<Drink> getDrinks(String optional_search) throws SQLException;
}
