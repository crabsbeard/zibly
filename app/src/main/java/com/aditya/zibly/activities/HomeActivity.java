package com.aditya.zibly.activities;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aditya.zibly.R;
import com.aditya.zibly.data.StaticData;
import com.aditya.zibly.network.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class HomeActivity extends ActionBarActivity {

    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private VolleySingleton volleySingleton;
    StaticData staticData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                        parseJsonRequest(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);
    }

    private void parseJsonRequest(JSONObject response) {
        if(response==null || response.length()==0){
            return;
        }

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
