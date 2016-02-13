package com.pulkit4tech.popularmovies1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

/**
 * Created by PULKIT on 2/13/2016.
 */
public class Detail_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private TextView synopsis;
    private TextView release_date;
    private TextView ratings;
    private RatingBar rbar;
    private Data_item data;
    private ImageButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_activity);

        data = (Data_item) getIntent().getParcelableExtra(getString(R.string.MY_EXTRA));

        //initializing
        initialize();
        //populating fields
        populate();
    }

    private void populate() {

        title.setText(data.movie_name);
        Picasso.with(getBaseContext()).load(data.poster_url).placeholder(getResources().getDrawable(R.drawable.placeholder))
                .error(getResources().getDrawable(R.drawable.ic_error_black_48dp))
                .into(
                (ImageView) findViewById(R.id.main_poster));
        synopsis.setText(data.overview);
        rbar.setRating(data.movie_rating / 2f);
        ratings.setText((float) Math.round(data.movie_rating*10d)/10d + "/10");

        SimpleDateFormat output = new SimpleDateFormat("dd MMM, yyyy");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        String releasedDate;
        try {
            releasedDate = output.format(input.parse(data.movie_released_date));
        } catch (Exception e){
            e.printStackTrace();
            releasedDate = data.movie_released_date;
        }
        release_date.setText(releasedDate);


        fab.setOnClickListener(this);
    }

    private void initialize() {


        title = (TextView) findViewById(R.id.movie_title);
        synopsis = (TextView) findViewById(R.id.synopsis);
        release_date = (TextView) findViewById(R.id.release_date);
        ratings = (TextView) findViewById(R.id.rating);
        rbar = (RatingBar)findViewById(R.id.ratingbar);
        fab = (ImageButton)findViewById(R.id.fab);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.fab){
            Toast.makeText(this,"FAB!!",Toast.LENGTH_SHORT).show();
        }
    }
}
