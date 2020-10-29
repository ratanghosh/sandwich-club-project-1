package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            /*the selected sandwich will call from the maimActivity and the string will
            come here as a JSONObject. */
            JSONObject selectedSandwich = new JSONObject(json);
            JSONObject name = selectedSandwich.getJSONObject("name");

            // string for main name
            String mainName = name.getString("mainName");

            // Array of string for alsoKnownAs
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            ArrayList<String> alsoKnownAsList = new ArrayList<>();
            for(int i=0; i<alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            // string for place of origin
            String placeOfOrigin = selectedSandwich.getString("placeOfOrigin");

            // string for Description
            String description = selectedSandwich.getString("description");

            // string for Image resource
            String image = selectedSandwich.getString("image");

            // Array of string for ingredients
            JSONArray ingredients = selectedSandwich.getJSONArray("ingredients");

            ArrayList<String> ingredientsList = new ArrayList<>();
            for(int i=0; i<ingredients.length(); i++){
                ingredientsList.add(ingredients.getString(i));
            }



            return new Sandwich(mainName,alsoKnownAsList, placeOfOrigin, description,image, ingredientsList);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
