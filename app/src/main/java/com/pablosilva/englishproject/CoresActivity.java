package com.pablosilva.englishproject;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CoresActivity extends BaseActivity {

    private RecyclerView recycler;
    private CorAdapter adapter;
    private List<Cor> lista = new ArrayList<>();
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cores);

        setupDrawer();

        recycler = findViewById(R.id.recyclerCores);
        recycler.setLayoutManager(new GridLayoutManager(this, 3));

        // TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        // CORES
        lista.add(new Cor("Vermelho", "Red", "#FF0000"));
        lista.add(new Cor("Azul", "Blue", "#2196F3"));
        lista.add(new Cor("Verde", "Green", "#4CAF50"));
        lista.add(new Cor("Amarelo", "Yellow", "#FFEB3B"));
        lista.add(new Cor("Preto", "Black", "#000000"));
        lista.add(new Cor("Branco", "White", "#FFFFFF"));
        lista.add(new Cor("Roxo", "Purple", "#9C27B0"));
        lista.add(new Cor("Laranja", "Orange", "#FF9800"));
        lista.add(new Cor("Cinza", "Gray", "#9E9E9E"));
        lista.add(new Cor("Rosa", "Pink", "#E91E63"));

// 🔥 NOVAS CORES
        lista.add(new Cor("Marrom", "Brown", "#795548"));
        lista.add(new Cor("Ciano", "Cyan", "#00BCD4"));
        lista.add(new Cor("Magenta", "Magenta", "#FF00FF"));
        lista.add(new Cor("Dourado", "Gold", "#FFD700"));
        lista.add(new Cor("Prata", "Silver", "#C0C0C0"));
        lista.add(new Cor("Bege", "Beige", "#F5F5DC"));
        lista.add(new Cor("Turquesa", "Turquoise", "#40E0D0"));
        lista.add(new Cor("Índigo", "Indigo", "#3F51B5"));
        lista.add(new Cor("Vinho", "Wine", "#722F37"));
        lista.add(new Cor("Verde Limão", "Lime", "#CDDC39"));


        adapter = new CorAdapter(lista, tts);
        recycler.setAdapter(adapter);
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