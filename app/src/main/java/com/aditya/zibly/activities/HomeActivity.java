package com.aditya.zibly.activities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aditya.zibly.R;
import com.aditya.zibly.adapters.AdapterPlacesHome;
import com.aditya.zibly.data.StaticData;
import com.aditya.zibly.network.VolleySingleton;
import com.aditya.zibly.pojo.Calculators;
import com.aditya.zibly.pojo.ExploreCard;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gc.materialdesign.views.ProgressBarIndeterminate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements StaticData {
    private RequestQueue requestQueue;
    private VolleySingleton volleySingleton;
    private RecyclerView rv_places_list_1;
    private RecyclerView rv_places_list_2;
    private RecyclerView rv_places_list_3;
    ArrayList<ExploreCard> exploreCardArrayList = new ArrayList<>();
    private String iconSize = "44";
    Calculators calculators;
    AdapterPlacesHome adapterPlacesHome;
    Toolbar toolbar;
    TextView tv_title;
    TextView tv_title_1;
    TextView tv_title_2;
    TextView tv_title_3;
    Typeface typeface_title_1;
    Typeface typeface_title_2;
    ScrollView sv_master;
    TextView tv_loader;
    ProgressBarIndeterminate progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        sv_master = (ScrollView) findViewById(R.id.sv_master);
        tv_loader = (TextView) findViewById(R.id.tv_loading_text);
        progressBar = (ProgressBarIndeterminate) findViewById(R.id.progressBar);
        calculators = new Calculators(this);
        //setup and initialize typefaces
        setupCustomFontsAndTextView();

        //initialize recycler view
        int itemHeight = getItemHeight();
        setupRecyclerViews(itemHeight);

        //getting json data
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendJsonRequest(this);

    }

    private void setupRecyclerViews(int itemHeight) {

        rv_places_list_1 = (RecyclerView)findViewById(R.id.rv_places_1);
        rv_places_list_1.setLayoutManager(new LinearLayoutManager(this));
        rv_places_list_1.getLayoutParams().height = itemHeight*3;
        rv_places_list_2 = (RecyclerView)findViewById(R.id.rv_places_2);
        rv_places_list_2.setLayoutManager(new LinearLayoutManager(this));
        rv_places_list_2.getLayoutParams().height = itemHeight*3;
        rv_places_list_3 = (RecyclerView)findViewById(R.id.rv_places_3);
        rv_places_list_3.setLayoutManager(new LinearLayoutManager(this));
        rv_places_list_3.getLayoutParams().height = itemHeight*3;

    }

    private void setupCustomFontsAndTextView() {
        typeface_title_1 = Typeface.createFromAsset(getAssets(), "font_title_1.ttf");
        typeface_title_2 = Typeface.createFromAsset(getAssets(), "font_title_2.ttf");
        tv_title = (TextView) findViewById(R.id.tv_top_heading);
        tv_title_1 = (TextView) findViewById(R.id.tv_title_1);
        tv_title_2 = (TextView) findViewById(R.id.tv_title_2);
        tv_title_3 = (TextView) findViewById(R.id.tv_title_3);
        tv_title_1.setTypeface(typeface_title_2);
        tv_title_2.setTypeface(typeface_title_2);
        tv_title_3.setTypeface(typeface_title_2);
        tv_loader.setTypeface(typeface_title_1);
        tv_title.setTypeface(typeface_title_1);

    }

    private int getItemHeight() {
        DisplayMetrics displaymetrics = Resources.getSystem().getDisplayMetrics();
        int width = displaymetrics.widthPixels;
        return (width*3)/5+15;
    }

    private void sendJsonRequest(final Context context) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, buildUrl(3),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        exploreCardArrayList = parseJsonResponse(response);
                        adapterPlacesHome = new AdapterPlacesHome(context);
                        adapterPlacesHome.setExploreCardsList(exploreCardArrayList);
                        rv_places_list_1.setAdapter(adapterPlacesHome);
                        rv_places_list_2.setAdapter(adapterPlacesHome);
                        rv_places_list_3.setAdapter(adapterPlacesHome);
                        tv_loader.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        sv_master.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);
    }


    private ArrayList<ExploreCard> parseJsonResponse(JSONObject response) {
        ArrayList<ExploreCard> exploreCardArrayList = new ArrayList<>();
        if(response==null || response.length()==0){
            return exploreCardArrayList;
        }
        //need to add code to parse json feed

        try {
            JSONArray itemsJsonArray = response.getJSONObject(RESPONSE)
                    .getJSONArray(GROUPS)
                    .getJSONObject(0)
                    .getJSONArray(ITEMS);
            for(int i = 0; i<itemsJsonArray.length();i++){
                JSONObject venueJsonObject = itemsJsonArray.getJSONObject(i).getJSONObject(VENUE);
                JSONObject photoItem = venueJsonObject.getJSONObject(PHOTOS).getJSONArray(GROUPS).getJSONObject(0).getJSONArray(ITEMS).getJSONObject(0);
                JSONObject locationJsonObject = venueJsonObject.getJSONObject(LOCATION);
                JSONArray categoryJsonObject = venueJsonObject.getJSONArray(CATEGORIES);
                JSONObject cateInfoJsonObject = categoryJsonObject.getJSONObject(0);
                JSONObject iconJsonObject = cateInfoJsonObject.getJSONObject(ICON);

                //adding data into array list

                ExploreCard currentCard = new ExploreCard();
                currentCard.setId(venueJsonObject.getString(ID)); //add id
                currentCard.setPlace_name(venueJsonObject.getString(NAME)); //add name
                currentCard.setPlace_address(locationJsonObject.getString(ADDRESS)); //add address
                currentCard.setRating((float)(venueJsonObject.getDouble(RATING)/2)); //add rating
                currentCard.setPlace_distance(calculators.calculateDistance(locationJsonObject.getDouble(LATITUDE),
                      locationJsonObject.getDouble(LONGITUDE))); //calculate distance and add the value
                //getting like status by first checking if it exist or not
                if(venueJsonObject.has(LIKE)){
                    currentCard.setHeart_status(venueJsonObject.getBoolean(LIKE));
                }
                else{
                    //this means the user is not signed in

                    currentCard.setHeart_status(false);

                }

                currentCard.setPlace_type_icon(build_url(iconJsonObject.getString(PREFIX),
                        iconJsonObject.getString(SUFFIX)));
                currentCard.setPlace_image_url(setupCoverUrl(photoItem.getString(PREFIX), photoItem.getString(SUFFIX)));

                //adding the current card to the array list

                exploreCardArrayList.add(currentCard);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exploreCardArrayList;
    }



    private String setupCoverUrl(String prefix, String suffix) {
        String resolution = "500x300";
        return prefix+resolution+suffix;
    }

    private String build_url(String prefix, String suffix) {
        String ret_icon_url;
        ret_icon_url = prefix+iconSize+suffix;
        return ret_icon_url;
    }

    private String buildUrl(int limit) {
        String limit_ = Integer.toString(limit);
        Uri built_uri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(PATH_VENUES)
                .appendPath(PATH_EXPLORE)
                .appendPath("")
                .appendQueryParameter(VERSION_KEY, VERSION)
                .appendQueryParameter(CLIENT_ID_KEY, CLIENT_ID)
                .appendQueryParameter(CLIENT_SECRET_KEY, CLIENT_SECRET)
                .appendQueryParameter(VENUE_PHOTO_KEY, TRUE)
                .appendQueryParameter(NAER_KEY, NEAR_VALUE)
                .appendQueryParameter(LIMIT_KEY, limit_ ).build();
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
