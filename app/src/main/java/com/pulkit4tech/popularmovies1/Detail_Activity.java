package com.pulkit4tech.popularmovies1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pulkit4tech.popularmovies1.adapter.MyPageAdapter;
import com.pulkit4tech.popularmovies1.data.Data_item;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

/**
 * Created by PULKIT on 2/13/2016.
 */
public class Detail_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private TextView release_date;
    private TextView ratings;
    private RatingBar rbar;
    private Data_item data;
    private ImageButton fab;
    private ViewPager pager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_activity);

        data = (Data_item) getIntent().getParcelableExtra(getString(R.string.MY_EXTRA));

        //initializing
        initialize();
        //populating fields
        populate();

        FragmentManager fm = getSupportFragmentManager();
        MyPageAdapter adapter = new MyPageAdapter(fm,data);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);

//        pager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

      //  synopsis.setText(data.overview);
    }

    private void populate() {

        title.setText(data.movie_name);

        //using high quality poster for detail activity
        String poster = data.poster_url;
        //poster = poster.replace(getString(R.string.thumbnail_quality),getString(R.string.detail_quality));


        Picasso.with(getBaseContext()).load(poster).placeholder(getResources().getDrawable(R.drawable.placeholder))
                .error(getResources().getDrawable(R.drawable.ic_error_black_48dp))
                .into(
                        (ImageView) findViewById(R.id.main_poster));

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
        release_date = (TextView) findViewById(R.id.release_date);
        ratings = (TextView) findViewById(R.id.rating);
        rbar = (RatingBar)findViewById(R.id.ratingbar);
        fab = (ImageButton)findViewById(R.id.fab);
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

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
