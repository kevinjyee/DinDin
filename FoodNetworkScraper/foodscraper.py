import csv
import argparse
import logging
from food_network_wrapper import recipe_search, get_n_recipes, scrape_recipe

def parse_args():

    parser = argparse.ArgumentParser(description='Food Network Parser')
    parser.add_argument('-f', '--food', type=str, help='foodtosearch')

    return parser.parse_args()


def main():
    args = parse_args()

    rthumbnails = get_n_recipes(args.food)

    recipes = []
    recipe_list = []
    for i in rthumbnails:
        print("Scraping ...")
        print(i.title)
        print(i.url)
        print(i.author)
        print(i.picture_url)
        print(i.total_time)
        print(i.rating)
        print(i.review_count)

        recipe = scrape_recipe(i.url)
        recipes.append(recipe)



    for i in recipes:
        title = i.title
        #print(i.author)
        picture = i.picture_url
        totaltime = i.total_time
        preptime = i.prep_time
        cooktime = i.cook_time
        servings = i.servings
        level = i.level
        ingredients = i.ingredients
        #print(i.directions)
        #print(i.categories)

        list = [title,picture,totaltime,preptime,cooktime,servings,level,ingredients]
        recipe_list.append(list)

    with open (args.food +'.csv','wb') as file:
        writer=csv.writer(file)
        for row in recipe_list:
            writer.writerow(row)




if __name__ == "__main__":
    main()