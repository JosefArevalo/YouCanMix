package Drink;

public class DrinkBuilder {
	private final String name;
	private String ingredients;
    private String quantities;
    private String instructions;
    private int rating;
    
    public DrinkBuilder(String name, String ingredient, String quantity) {
    	if (name == null)throw new IllegalArgumentException("Name can not be null");
    	this.name = name;
    	this.ingredients = ingredient;
    	this.quantities = quantity;
    	this.instructions = "";
    	this.rating = 5;
    }
    
    public DrinkBuilder withIngredient(String ingredient) {
    	this.ingredients += ", " + ingredient;
    	return this;
    }
    public DrinkBuilder withQuantity(String quantity) {
    	this.quantities += ", " + quantity;
    	return this;
    }
    public DrinkBuilder withInstruction(String instruction) {
    	this.instructions = instruction;
    	return this;
    }
    public DrinkBuilder withRating(int rating) {
    	this.rating = rating;
    	return this;
    }
    
    public Drink build() {
        return new Drink(this);
      }

    
    public String getName() {
        return name;
    }
    public String getIngredients() {
        return ingredients;
    }
    public String getQuantities() {
        return quantities;
    }
    public String getInstructions() {
        return instructions;
    }
    public int getRating() {
        return rating;
    }

}
