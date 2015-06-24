package com.aditya.zibly.activities;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.aditya.zibly.R;
import com.aditya.zibly.data.StaticData;
import com.aditya.zibly.location.LocationService;
import com.aditya.zibly.network.VolleySingleton;
import com.aditya.zibly.pojo.Calculators;
import com.aditya.zibly.pojo.ExploreCard;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;


public class HomeActivity extends ActionBarActivity {

    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private VolleySingleton volleySingleton;
    StaticData staticData;
    private RecyclerView rv_places_list;
    ArrayList<ExploreCard> exploreCardArrayList = new ArrayList<>();
    private String iconSize = "44";
    Calculators calculators;
    private String imageUrlFromApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        calculators = new Calculators(this);
        //initialize recycler view

        rv_places_list = (RecyclerView)findViewById(R.id.rv_places);
        rv_places_list.setLayoutManager(new LinearLayoutManager(this));

        //getting json data

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendJsonRequest();
    }

    private void sendJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, buildUrl(10),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);
    }

    private void parseJsonResponse(JSONObject response) {
        if(response==null || response.length()==0){
            return;
        }
        //need to add code to parse json feed

        try {
            JSONArray itemsJsonArray = response.getJSONArray(staticData.getGroups()).getJSONObject(0).getJSONArray(staticData.getItems());
            for(int i = 0; i<itemsJsonArray.length();i++){
                JSONObject venueJsonObject = itemsJsonArray.getJSONObject(i).getJSONObject(staticData.getVenue());
                JSONObject locationJsonObject = venueJsonObject.getJSONObject(staticData.getLocation());
                JSONArray categoryJsonObject = venueJsonObject.getJSONArray(staticData.getCategories());
                JSONObject cateInfoJsonObject = categoryJsonObject.getJSONObject(0);
                JSONObject iconJsonObject = cateInfoJsonObject.getJSONObject(staticData.getIcon());

                //adding data into array list

                ExploreCard currentCard = new ExploreCard();
                currentCard.setId(venueJsonObject.getString(staticData.getId())); //add id
                currentCard.setPlace_name(venueJsonObject.getString(staticData.getName())); //add name
                currentCard.setPlace_address(locationJsonObject.getString(staticData.getAddress())); //add address
                currentCard.setRating((float)(venueJsonObject.getDouble(staticData.getRating())/2)); //add rating
                currentCard.setPlace_distance(calculators.calculateDistance(locationJsonObject.getDouble(staticData.getLatitude()),
                        locationJsonObject.getDouble(staticData.getLongitude()))); //calculate distance and add the value
                //getting like status by first checking if it exist or not
                if(venueJsonObject.has(staticData.getLike())){
                    currentCard.setHeart_status(venueJsonObject.getBoolean(staticData.getLike()));
                }
                else{
                    //this means the user is not signed in
                    currentCard.setHeart_status(false);
                }
                int limit = 10;
                currentCard.setPlace_type_icon(build_url(iconJsonObject.getString(staticData.getPrefix()),
                        iconJsonObject.getString(staticData.getSuffix())));
                //setting up the cover image url using id and stuff
                currentCard.setPlace_image_url(getImageUrlFromApi(venueJsonObject.getString(staticData.getId()),limit));

                //adding the ccurrent card to the array list

                exploreCardArrayList.add(currentCard);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getImageUrlFromApi(String id, final int limit) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, imageUrlSet(id, limit),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                imageUrlFromApi = parseImageJsonResponse(response, limit);
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
        return imageUrlFromApi;
    }

    private String parseImageJsonResponse(JSONObject jsonObject, int limit) {
        String imageUrl = null;
        if(jsonObject==null || jsonObject.length()==0){
            return null;
        }
        try {
            JSONObject randomPhotoItem = jsonObject.getJSONArray(staticData.getItems()).getJSONObject(randomIndex(limit));
            imageUrl = coverImageBuilder(randomPhotoItem.getString(staticData.getPrefix()),
                    randomPhotoItem.getString(staticData.getSuffix()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

    private String coverImageBuilder(String prefix, String suffix) {
        String resolution = "500x300";
        return prefix+resolution+suffix;
    }

    private int randomIndex(int maxLimit){
        Random rand = new Random();
        return rand.nextInt(maxLimit) + 1;
    }

    private String imageUrlSet(String id, int limit) {
        Uri uri = Uri.parse(staticData.getBase_url()).buildUpon()
                .appendPath(staticData.getVenues())
                .appendPath(id)
                .appendPath(staticData.getPhotos())
                .appendQueryParameter(staticData.getVersion_key(), staticData.getVersion())
                .appendQueryParameter("limit", Integer.toString(limit))
                .appendQueryParameter(staticData.getClient_id_key(), staticData.getClient_id())
                .appendQueryParameter(staticData.getClient_secret_key(), staticData.getClient_secret()).build();

        return uri.toString();
    }

    private String build_url(String prefix, String suffix) {
        String ret_icon_url;
        ret_icon_url = prefix+getIconSize()+suffix;
        return ret_icon_url;
    }

    private String buildUrl(int limit) {
        String limit_ = Integer.toString(limit);
        Uri built_uri = Uri.parse(staticData.getBase_url_explore()).buildUpon()
                .appendQueryParameter(staticData.getVersion_key(), staticData.getVersion())
                .appendQueryParameter(staticData.getClient_id_key(), staticData.getClient_id())
                .appendQueryParameter(staticData.getClient_secret_key(), staticData.getClient_secret())
                .appendQueryParameter(staticData.getNear_key(), staticData.getNear_value())
                .appendQueryParameter("limit", limit_).build();
        return built_uri.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getIconSize() {
        return iconSize;
    }

    public void setIconSize(String iconSize) {
        this.iconSize = iconSize;
    }

}
