package com.example.dindin;

import java.util.ArrayList;

/**
 * Created by Kevin on 11/26/2016.
 */

public class Recipe {

    private String food_name;
    private String image_url;
    private String ingredients;
    private String recipe;

    public Recipe(){

    }

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

    public static ArrayList<Recipe> generateRecipes(){
        Recipe Bacon = new Recipe("Stuffing with Bacon","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2013/2/22/1/GH0503H_christmas-stuffing-with-bacon-recipe_s4x3.jpg.rend.sni12col.landscape.jpeg"
                ,"Total Time:\n" +
                "1 hr 40 min\n" +
                "Prep:\n" +
                "30 min\n" +
                "Inactive:\n" +
                "10 min\n" +
                "Cook:\n" +
                "1 hr\n" +
                "\n" +
                "\n" +
                "Read more at: http://www.foodnetwork.com/recipes/giada-de-laurentiis/christmas-stuffing-with-bacon-recipe.html?oc=linkback","");

        Recipe Panini = new Recipe("Cobb Salada Panini","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2011/11/4/0/CCFOO121_Cobb-Salad-Panini_s4x3.jpg.rend.sni12col.landscape.jpeg",
                "Total Time:\n" +
                        "30 min\n" +
                        "Prep:\n" +
                        "20 min\n" +
                        "Cook:\n" +
                        "10 min\n" +
                        "\n" +
                        "Read more at: http://www.foodnetwork.com/recipes/tyler-florence/cobb-salad-panini-recipe.html?oc=linkback","");
        Recipe PotPie = new Recipe("Turkey Pot Pie","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2013/6/21/0/FNK_Turkey-Pot-Pie_s4x3.jpg.rend.sni12col.landscape.jpeg"
                ,"Total Time:\n" +
                "1 hr 10 min\n" +
                "Prep:\n" +
                "20 min\n" +
                "Cook:\n" +
                "50 min\n" +
                "\n" +
                "Read more at: http://www.foodnetwork.com/recipes/turkey-pot-pie-recipe.html?oc=linkback","");
        Recipe AppleCobbler = new Recipe("Apple Cobbler for Two","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2015/12/8/1/FNK_Apple-Cobbler-for-Two_s4x3.jpg.rend.sni12col.landscape.jpeg"
                ,"Total Time:\n" +
                "1 hr 15 min\n" +
                "Prep:\n" +
                "15 min\n" +
                "Inactive:\n" +
                "10 min\n" +
                "Cook:\n" +
                "50 min\n" +
                "\n" +
                "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/apple-cobbler-for-two.html?oc=linkback","");

        Recipe CarrotCake = new Recipe("Carrot Cake","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2015/12/8/1/FNK_Carrot-Cake-for-Two_s4x3.jpg.rend.sni12col.landscape.jpeg" ,
                "Total Time:\n" +
                        "2 hr 55 min\n" +
                        "Prep:\n" +
                        "30 min\n" +
                        "Inactive:\n" +
                        "1 hr 50 min\n" +
                        "Cook:\n" +
                        "35 min\n" +
                        "\n" +
                        "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/carrot-cake-for-two.html?oc=linkback","");
        Recipe TurkeyBolognese = new Recipe("Turkey Bolognese","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2011/8/10/1/Thanksgiving-2011_EI0707-turkey-bolognese_s4x3.jpg.rend.sni12col.landscape.jpeg"
                ,"Total Time:\n" +
                "50 min\n" +
                "Prep:\n" +
                "20 min\n" +
                "Cook:\n" +
                "30 min\n" +
                "\n" +
                "Read more at: http://www.foodnetwork.com/recipes/giada-de-laurentiis/turkey-bolognese-recipe.html?oc=linkback","");

        Recipe ShrimpScampi = new Recipe("Baked Shrimp Scampi","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2012/11/12/0/FN_Ina-Garten-Baked-Shrimp-Scampi_s4x3.jpg.rend.sni12col.landscape.jpeg"
                ,"Total Time:\n" +
                "43 min\n" +
                "Prep:\n" +
                "30 min\n" +
                "Cook:\n" +
                "13 min\n" +
                "\n" +
                "Read more at: http://www.foodnetwork.com/recipes/ina-garten/baked-shrimp-scampi-recipe.html?oc=linkback","");

        Recipe ChickenParm = new Recipe("Skillet Chicken Parmesan with Artichokes","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2016/5/11/1/FNM_060116-Skillet-Chicken-Parmesan-with-Artichokes_s4x3.jpg.rend.sni12col.landscape.jpeg"
                ,"Total Time:\n" +
                "40 min\n" +
                "Prep:\n" +
                "15 min\n" +
                "Inactive:\n" +
                "5 min\n" +
                "Cook:\n" +
                "20 min\n" +
                "\n" +
                "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/skillet-chicken-parmesan-with-artichokes.html?oc=linkback","");

        Recipe PorkChop = new Recipe("Pork Chop with Pear Chutney","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2014/4/28/0/FNK_Pork-Chops-with-Pear-Chutney_s4x3.jpg.rend.sni12col.landscape.jpeg",
                "Total Time:\n" +
                        "30 min\n" +
                        "Prep:\n" +
                        "15 min\n" +
                        "Cook:\n" +
                        "15 min\n" +
                        "\n" +
                        "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/pork-chops-with-pear-chutney-recipe.html?oc=linkback","");

            ArrayList<Recipe> RecipeList = new ArrayList<>();
            // MatchedRecipeList = matchData.getMatches();



        RecipeList.add(Bacon);
        RecipeList.add(Panini);
        RecipeList.add(PotPie);
        RecipeList.add(AppleCobbler);
        RecipeList.add(CarrotCake);
        RecipeList.add(TurkeyBolognese);
        RecipeList.add(ShrimpScampi);
        RecipeList.add(ChickenParm);
        RecipeList.add(PorkChop);
        return RecipeList;
    }
}
