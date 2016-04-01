package com.pulkit4tech.popularmovies1.fragement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pulkit4tech.popularmovies1.data.Data_item;
import com.pulkit4tech.popularmovies1.Detail_Activity;
import com.pulkit4tech.popularmovies1.KEY;
import com.pulkit4tech.popularmovies1.R;
import com.pulkit4tech.popularmovies1.adapter.ImageAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PULKIT on 2/12/2016.
 */
public class MainActivityFragement extends Fragment {


    private RequestQueue requestQueue;
    private String LOG_TAG = "testing";
    public ImageAdapter imageAdapter;
    public ArrayList<Data_item> data_to_be_filled;
    GridView gridView;

    public MainActivityFragement() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.sort_by,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.popularity){
            //updating sharedpreference
            SharedPreferences pref = getActivity().getSharedPreferences(getString(R.string.Pref), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(getString(R.string.param1),getString(R.string.popularity_p1));
            editor.putString(getString(R.string.param2),getString(R.string.popularity_p2));
            editor.commit();
            //refreshin fragement
            MainActivityFragement fragement = new MainActivityFragement();
            getFragmentManager().beginTransaction().detach(this).commit();
            getFragmentManager().beginTransaction().add(R.id.container,fragement).commit();

        }
        else if(id==R.id.highest_rated){

            //updating shared preference
            SharedPreferences pref = getActivity().getSharedPreferences(getString(R.string.Pref), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(getString(R.string.param1),getString(R.string.rating_p1));
            editor.putString(getString(R.string.param2),getString(R.string.rating_p2));
            editor.commit();
            //refreshin fragement
            MainActivityFragement fragement = new MainActivityFragement();
            getFragmentManager().beginTransaction().detach(this).commit();
            getFragmentManager().beginTransaction().add(R.id.container,fragement).commit();
        }
        else{
            Toast.makeText(getActivity(), "Settings!!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragement_main, container, false);

        imageAdapter = new ImageAdapter(getActivity());
        requestQueue = Volley.newRequestQueue(getActivity());
        data_to_be_filled = new ArrayList<>();

        //getting last stored sort type
        SharedPreferences pref = this.getActivity().getSharedPreferences(getString(R.string.Pref), Context.MODE_PRIVATE);
        getMovies(
                pref.getString(getString(R.string.param1),getString(R.string.popularity_p1)),
                pref.getString(getString(R.string.param2),getString(R.string.popularity_p2)));

        //setting adapter
        gridView = (GridView) rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(imageAdapter);

        //setting onClick
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(),Detail_Activity.class);

                i.putExtra(getString(R.string.MY_EXTRA),(Parcelable)data_to_be_filled.get(position));
                startActivity(i);
            }
        });

        return rootView;
    }

    public void getMovies(String param1, String param2) {


         String baseurl = "http://api.themoviedb.org/3/discover/movie?sort_by=" + param1 + "&" + param2
                + "&api_key=" + new KEY().getkey();
         final String baseimgurl = "http://image.tmdb.org/t/p/"+getString(R.string.thumbnail_quality)+"/";

        //clearing data
        clear_buffer();

        requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jor = new JsonObjectRequest(baseurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray items = response.getJSONArray("results");
                            JSONObject movieObj;
                            for (int i=0; i<items.length(); i++){
                                movieObj = items.getJSONObject(i);
                                Data_item movie = new Data_item();
                                movie.id = movieObj.getInt("id");
                                movie.movie_rating = (float) movieObj.getDouble("vote_average");
                                movie.movie_name = movieObj.getString("original_title");
                                movie.poster_url = baseimgurl + movieObj.getString("poster_path");
                                movie.movie_released_date = movieObj.getString("release_date");
                                movie.overview = movieObj.getString("overview");
                                movie.movie_popularity = movieObj.getDouble("popularity");
                                data_to_be_filled.add(movie);
                                //adding image in adapter
                                imageAdapter.add_images(movie.poster_url);
                            }
                        } catch (Exception e) {

                            Log.e(LOG_TAG,e.toString());
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gridView.setAdapter(imageAdapter);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(LOG_TAG,"error parsing JSON");
            }
        });

        requestQueue.add(jor);
    }

    private void clear_buffer() {
        imageAdapter.clear_images();
        data_to_be_filled.clear();
    }
}
