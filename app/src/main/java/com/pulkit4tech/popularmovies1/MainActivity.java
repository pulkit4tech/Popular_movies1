package com.pulkit4tech.popularmovies1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.pulkit4tech.popularmovies1.fragement.MainActivityFragement;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainActivityFragement()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}