package Drink;

public class Drink {
    private final String drinkName;
    private final String ingredients;
    private final String quantities;
    private int rating;

    public Drink(String drinkName, String ingredients, String quantities, int rate) {
        this.drinkName = drinkName;
        this.ingredients = ingredients;
        this.quantities = quantities;
        this.rating = rate;
    }
    
    public String getDrinkName() {
    	return this.drinkName;
    }
    public String getIngredients() {
    	return this.ingredients;
    }
    public String getQuantities() {
    	return this.quantities;
    }
    public int getRating() {
    	return this.rating;
    }
    public void setRating(int r) {
    	this.rating = r;
    }

}
