package edu.volstate.wade_colton_assign11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import models.Pokemon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //You will complete the methods below where you see Javadocs and "COMPLETE THIS METHOD" text.

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;
    private final ArrayList<Pokemon> pokemonList = new ArrayList<>();
    private StringRequest myRequest;
    private final String BASE_API_URL = "https://pokeapi.co/api/v2/pokemon?";
    private int offset = 0;
    private final int limit = 20;

    //Do NOT modify code in onCreate. You may add a line or two near the bottom of the method,
    //if needed.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //Commented lines were used for initial debugging. I left them here just to show
                //you a few new methods
                if (dy > 0) {
                    //int visibleItemCount = layoutManager.getChildCount();
                    //int totalItemCount = layoutManager.getItemCount();
                    //int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    //If recyclerView CANNOT scroll vertically (1 is vertical), then load more data...
                    if (!recyclerView.canScrollVertically(1)) {
                        //Log.d("visible", String.valueOf(visibleItemCount));
                        //Log.d("past", String.valueOf(pastVisibleItems));
                        //Log.d("total", String.valueOf(totalItemCount));
                        offset += 20;
                        getData(limit, offset);
                    }
                }
            }
        });

        offset = 0;
        getData(limit, offset);
        //You could (but don't have to) add code here.
        //Do not modify the code above because you think it's wrong. It's right. It works.
        //See the Zoom session.
    }

    /**
     * getData uses Android's Volley to load the URL of the Pokemon JSON feed and gather the
     * JSON from the Poke API, such as this: https://pokeapi.co/api/v2/pokemon?limit=10&offset=0
     *
     * After the data is retrieved and isolated into variables, the a Pokemon object  is
     * instantiated for each Pokemon in the feed. For example, the above feed link contains 10
     * pokemon. Thus, 10 Pokemon would be instantiated. As shown in the link above, each Pokemon
     * has two pieces of data, name and url. These are used to populate the Pokemon class (see that
     * class). Each Pokemon also has more data, too, as described in the getProfileData method below.
     *
     * After each Pokemon is instantiated, the Pokemon is added to the pokemonList declared at the
     * top of this class.
     *
     * At some point, this method (or some method) should use the url data gained above (see name and
     * url data points above). You will finish populating the Pokemon objects using that url. See
     * the getProfileData method for more information.
     *
     * @param  limit  Integer. The maximum number of Pokemon to retrieve
     * @param  offset Integer. The numerical location to begin retrieving Pokemon.
     */

    @SuppressLint("NotifyDataSetChanged")
    private void getData(int limit, int offset) {
        //COMPLETE THIS METHOD - See the Zoom Class.

        //Build the URL using the BASE_API_URL and the two input parameters, limit and offset.
        String url = BASE_API_URL + "limit=" + limit + "&offset=" + offset;
        Log.d("DEBUG_URL", url);

        //Use Android Volley to load the Pokemon API URL and parse the JSON Objects/Arrays
        //as needed.
        myRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject myJsonObject = new JSONObject(response);

                        // Loops through the JSONArray, and gets the name/url
                        JSONArray Pokemon = myJsonObject.getJSONArray("results");

                        for (int i = 0; i < Pokemon.length(); i++) {
                            JSONObject tempObj = Pokemon.getJSONObject(i);
                            String pName = tempObj.getString("name");
                            String pUrl = tempObj.getString("url");
                            //Log.d("DEBUG_TempObject", String.format("Name: %s, URL: %s", pName, pUrl));

                            //Instantiate a Pokemon object with name and url retrieved from JSON.
                            Pokemon pokemon = new Pokemon();
                            pokemon.setName(pName);
                            pokemon.setUrl(pUrl);
                            getProfileData(pUrl, pokemon); // Calls getProfileData to get the ID/BaseExperience


                            //Add the Pokemon object to the pokemonList instance variable.
                            pokemonList.add(pokemon);
                        }


                        //Debug with the Logcat messages to verify output.
//                        for (Pokemon p : pokemonList)
//                        {
//                            Log.d("DEBUG_pokemonList", p.toString());
//                        }

                        //At some point, you will need to feed the pokemon list to the array adapter with this
                        pokemonAdapter.addListPokemon(pokemonList);


                    } catch (JSONException e) {
                        Log.d("API_EXCEPTION", e.getMessage());
                    }
                },

                volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);

        requestQueue.addRequestFinishedListener(request -> {
            Log.d("DEBUG_myRequest", "myRequest is done");
        });

    }

    /**
     * getData uses Android's Volley to load the URL of the Pokemon profile data JSON feed and
     * gather the JSON from the URL. Using this data, you will populate baseExperience and id for
     * the Pokemon character in the URL provided.
     *
     * Whereas the getData method gets a URL where multiple Pokemon are listed, THIS method focuses
     * only on the one Pokemon character in the URL. There is a LOT of data in this URL. It's ok.
     * We only want the id and the baseExperience data.
     *
     * If you want to populate more of the Pokemon data, add additional fields to the Pokemon
     * class.
     *
     * baseExperience will be populated for the Pokemon character. But it doesn't display anywhere
     * currently. So, use Logcat to output the Pokemon's base experience with the tag "experience".
     *
     * You decide where this method will be called. You could call it from getData before or after
     * you add a Pokemon to the array list.
     *
     * @param  url  String. The URL of one Pokemon character's data, such as
     *              https://pokeapi.co/api/v2/pokemon/6/. This url is obtained through the getData
     *              method above, which gets the name and url of each pokemon.
     */


    private void getProfileData(String url, Pokemon p) {
        //COMPLETE THIS METHOD
        //Use Android Volley to load the Pokemon API URL and parse the JSON Objects/Arrays
        //as needed. See method description above.

        StringRequest profileData = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject myJsonObject = new JSONObject(response);
                        int pId = myJsonObject.getInt("id");
                        int pBaseExperience = myJsonObject.getInt("base_experience");
                        int pHeight = myJsonObject.getInt("height");
                        int pWeight = myJsonObject.getInt("weight");

                        //Log.d("getProfileData", String.valueOf(pId) + " " + String.valueOf(pBaseExperience) + ""
                        //+ String.valueOf(pHeight) + " " + String.valueOf(pWeight));

                        // Set's additional Pokemon fields
                        p.setId(pId);
                        p.setBaseExperience(pBaseExperience);
                        p.setHeight(pHeight);
                        p.setWeight(pWeight);

                        // Gets abilities of Pokemon by looping through the Abilities Array, getting the object ability, and
                        // then getting the abilities name and adding it to the Abilities ArrayList

                        ArrayList<String> Abilities = new ArrayList<>();
                        JSONArray abilitiesArray = myJsonObject.getJSONArray("abilities");
                        for (int i = 0; i < abilitiesArray.length(); i++)
                        {
                            JSONObject Obj = abilitiesArray.getJSONObject(i);
                            JSONObject Ability = Obj.getJSONObject("ability");
                            String ability = Ability.getString("name");
                            Abilities.add(ability);
                        }

                        // I had to wrap it in this for String.join to work
                        String pAbilities = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            pAbilities = String.join(", ", Abilities);
                        }

                        // Set's abilities
                        p.setAbilities(pAbilities);

                        // Log.d("DEBUG ABILITY", pAbilities);
                        // Log.d("FullPokemonData", p.toString());

                    } catch (JSONException e) {
                        Log.d("API_EXCEPTION", e.getMessage());
                    }
                },

                volleyError -> Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show()
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(profileData);

    }
}