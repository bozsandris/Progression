package com.example.bozsi.progression;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.overridePendingTransition(R.anim.lefttoright,R.anim.stayincenter);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.back) {
            Intent intent = new Intent(this, Back.class);
            startActivity(intent);
        } else if (id == R.id.chest) {
            Intent intent = new Intent(this, Chest.class);
            startActivity(intent);

        } else if (id == R.id.biceps) {
            Intent intent = new Intent(this, Biceps.class);
            startActivity(intent);

        } else if (id == R.id.triceps) {
            Intent intent = new Intent(this, Triceps.class);
            startActivity(intent);

        } else if (id == R.id.frontlever) {
            Intent intent = new Intent(this, Frontlever.class);
            startActivity(intent);

        } else if (id == R.id.planche) {
            Intent intent = new Intent(this, Planche.class);
            startActivity(intent);

        }
        else if (id == R.id.routine){
            Intent intent = new Intent(this, Routine.class);
            startActivity(intent);
        }
        else if (id == R.id.timer){
            Intent intent = new Intent(this, Timer.class);
            startActivity(intent);
        }
        else if (id == R.id.plancheprogression){
            Intent intent = new Intent(this, Plancheprogression.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
