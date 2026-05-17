package com.pablosilva.englishproject;

import android.content.Intent;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.appbar.MaterialToolbar;

public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected MaterialToolbar toolbar;

    protected void setupDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_food) {
                startActivity(new Intent(this, FoodActivity.class));
            } else if (id == R.id.nav_fruits) {
                startActivity(new Intent(this, FrutasActivity.class));
            } else if (id == R.id.nav_house) {
                startActivity(new Intent(this, HouseActivity.class));
            } else if (id == R.id.nav_alphabet) {
                startActivity(new Intent(this, AlfabetoActivity.class));
            } else if (id == R.id.nav_animais) {
                startActivity(new Intent(this, AnimaisActivity.class));
            } else if (id == R.id.nav_cores) {
                startActivity(new Intent(this, CoresActivity.class));
            } else if (id == R.id.nav_numeros) {
                startActivity(new Intent(this, NumerosActivity.class));
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}