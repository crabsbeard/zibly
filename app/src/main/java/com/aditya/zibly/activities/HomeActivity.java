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
import com.aditya.zibly.network.VolleySingleton;
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


public class HomeActivity extends ActionBarActivity {

    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private VolleySingleton volleySingleton;
    StaticData staticData;
    private RecyclerView rv_places_list;
    ArrayList<ExploreCard> exploreCardArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                //adding data into array list

                ExploreCard currentCard = new ExploreCard();
                currentCard.setId(venueJsonObject.getString(staticData.getId()));
                currentCard.setPlace_name(venueJsonObject.getString(staticData.getName()));
                currentCard.setPlace_address(locationJsonObject.getString(staticData.getAddress()));
                currentCard.setRating((float)(venueJsonObject.getDouble(staticData.getRating())));
                currentCard.setPlace_distance(calculateDistance(locationJsonObject.getDouble(staticData.getLatitude()),
                        locationJsonObject.getDouble(staticData.getLongitude())));

                /*
                String id;
                String name;
                String address;
                Float latitude;
                Float longitude;
                Float rating;
                String icon_url;
                Boolean like_status;
                */

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String calculateDistance(double lat, double lon) {

        return null;
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
}
