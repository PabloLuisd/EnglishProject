package com.pablosilva.englishproject;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFood = findViewById(R.id.buttonFood);
        Button btnFruits = findViewById(R.id.buttonFruits);
        Button btnHouse = findViewById(R.id.buttonHouse);
        Button btnAlphabet = findViewById(R.id.buttonAlphabet);
        Button btnAnimais = findViewById(R.id.button);
        Button btnCores = findViewById(R.id.button2);
        Button btnNumeros = findViewById(R.id.button3);

        btnFood.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            startActivity(intent);
        });

        btnFruits.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FrutasActivity.class);
            startActivity(intent);
        });

        btnHouse.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HouseActivity.class);
            startActivity(intent);
        });

        btnAlphabet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlfabetoActivity.class);
            startActivity(intent);
        });

        btnAnimais.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnimaisActivity.class);
            startActivity(intent);
        });

        btnCores.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoresActivity.class);
            startActivity(intent);
        });

        btnNumeros.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NumerosActivity.class);
            startActivity(intent);
        });

        setupDrawer();
    }
}