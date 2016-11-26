package com.example.dindin;

/**
 * Created by Kevin on 11/26/2016.
 */

public class Recipe {

    private String food_name;
    private String image_url;
    private String ingredients;
    private String recipe;

    public Recipe(String name, String url, String ingredients, String recipe)
    {
        this.food_name = name;
        this.image_url = url;
        this.ingredients = ingredients;
        this.recipe = recipe;
    }

    public String getFoodName()
    {
        return this.food_name;
    }

    public String getImage_url()
    {
        return this.image_url;

    }

    public String getIngredients()
    {
        return this.ingredients;
    }

    public String getRecipe()
    {
        return this.recipe;
    }

}
