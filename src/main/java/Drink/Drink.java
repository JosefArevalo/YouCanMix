package Drink;

public class Drink {
    private final String drinkName;
    private final String ingredients;
    private final String quantities;
    private final String instructions;
    private int rating;
    
    public Drink(DrinkBuilder builder) {
    	this.drinkName = builder.getName();
    	this.ingredients = builder.getIngredients();
    	this.quantities = builder.getQuantities();
    	this.instructions = builder.getInstructions();
    	this.rating = builder.getRating();
    }
/*
    public Drink(String drinkName, String ingredients, String quantities, int rate, String instructions) {
        this.drinkName = drinkName;
        this.ingredients = ingredients;
        this.quantities = quantities;
        this.rating = rate;
        this.instructions = instructions;
    }*/
    
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
    public String getInstructions() {
    	return this.instructions;
    }
    /*
    public void setRating(int r) {
    	this.rating = r;
    }*/

}
