package com.pixelkaveman.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        //This will rotate the hamburger icon while slindinf=g in the navigation drawer.
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                    , new MessageFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                ,new MessageFragment()).commit();
                break;

            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                        ,new ChatFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                        ,new ProfileFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(MainActivity.this , "Share" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_send:
                Toast.makeText(MainActivity.this , "Send" , Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //TODO: if navigation draweer i open
            // close navigation drawer
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
