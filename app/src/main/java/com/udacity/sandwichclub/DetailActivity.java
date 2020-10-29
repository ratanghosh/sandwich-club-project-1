package com.udacity.sandwichclub;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView originTV = findViewById(R.id.origin_tv);
        TextView alsoKnownAsTV = findViewById(R.id.also_known_tv);
        TextView descriptionTV = findViewById(R.id.description_tv);
        TextView ingredientsTV = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        descriptionTV.setText(sandwich.getDescription());
        //originTV.setText(sandwich.getPlaceOfOrigin());

        String   origin = sandwich.getPlaceOfOrigin();

        if (origin.length() != 0) {
            originTV.setText(origin);

        }

        else {
            originTV.setText("Unknown");
        }

        // also known as list setting in the alsoKnownAsTV
        int aksComma =0;

        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();

        String alsoKnownAsString = "";
        for (String t : alsoKnownAsList) {
            alsoKnownAsString +=  t ;
            if(aksComma  < ( alsoKnownAsList.size()-1 ) ){ // comma for every item in the AKS list except last item
                alsoKnownAsString += ",";
                aksComma++;
            }
        }

        if (alsoKnownAsString != "") {
            alsoKnownAsTV.setText(alsoKnownAsString);
        } else {
            TextView alsoKnownAsTitleTV = (TextView) findViewById(R.id.also_known_title_tv);
            alsoKnownAsTV.setVisibility(View.GONE);
            alsoKnownAsTitleTV.setVisibility(View.GONE);
        }


        // ingredients list setting in the ingredientsTV

        List<String> ingredientsList = sandwich.getIngredients();
        String ingredients = "";
        int i = 1;
        for (String t : ingredientsList) {
            ingredients += i + "." + t + "  ";
            i++;
        }

        ingredientsTV.setText(ingredients);


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }


}
