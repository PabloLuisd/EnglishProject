package com.pablosilva.englishproject;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlfabetoActivity extends BaseActivity {

    private RecyclerView recycler;
    private AlfabetoAdapter adapter;
    private List<Letra> lista = new ArrayList<>();
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfabeto);

        setupDrawer();

        recycler = findViewById(R.id.recyclerAlfabeto);
        recycler.setLayoutManager(new GridLayoutManager(this, 3)); // 3 colunas para letras

        // TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        gerarAlfabeto();

        adapter = new AlfabetoAdapter(lista, tts);
        recycler.setAdapter(adapter);
    }

    private void gerarAlfabeto() {
        String[] cores = {
                "#FF6B6B", "#FFD93D", "#6BCB77", "#4D96FF", "#9B59B6", "#FF9F43", "#FF85A2"
        };
        
        int corIndex = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            lista.add(new Letra(String.valueOf(c), Color.parseColor(cores[corIndex])));
            corIndex = (corIndex + 1) % cores.length;
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}