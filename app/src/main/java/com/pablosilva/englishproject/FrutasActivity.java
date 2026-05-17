package com.pablosilva.englishproject;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FrutasActivity extends BaseActivity {

    private TextToSpeech tts;
    private Animation anim;
    private RecyclerView recycler;
    private FrutaAdapter adapter;
    private List<Fruta> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);

        setupDrawer();

        recycler = findViewById(R.id.recyclerFruits);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));

        anim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        carregarFrutas();

        adapter = new FrutaAdapter(this, lista, (fruta, view) -> {
            if (anim != null) view.startAnimation(anim);
            falarFruta(fruta.nomeEn);
        });

        recycler.setAdapter(adapter);
    }

    private void carregarFrutas() {
        lista.add(new Fruta(R.drawable.maca, "Maçã", "Apple"));
        lista.add(new Fruta(R.drawable.banana, "Banana", "Banana"));
        lista.add(new Fruta(R.drawable.laranja, "Laranja", "Orange"));
        lista.add(new Fruta(R.drawable.morango, "Morango", "Strawberry"));
        lista.add(new Fruta(R.drawable.uva, "Uva", "Grape"));
        lista.add(new Fruta(R.drawable.abacaxi, "Abacaxi", "Pineapple"));
        lista.add(new Fruta(R.drawable.melancia, "Melancia", "Watermelon"));
        lista.add(new Fruta(R.drawable.pera, "Pêra", "Pear"));
    }

    private void falarFruta(String nome) {
        if (tts != null) {
            tts.speak(nome, TextToSpeech.QUEUE_FLUSH, null, null);
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
