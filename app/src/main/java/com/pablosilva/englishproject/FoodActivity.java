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

public class FoodActivity extends BaseActivity {

    private TextToSpeech tts;
    private Animation anim;
    private RecyclerView recycler;
    private ComidaAdapter adapter;
    private List<Comida> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        setupDrawer();

        recycler = findViewById(R.id.recyclerFood);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));

        anim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        carregarComidas();

        adapter = new ComidaAdapter(this, lista, (comida, view) -> {
            if (anim != null) view.startAnimation(anim);
            falarComida(comida.nomeEn);
        });

        recycler.setAdapter(adapter);
    }

    private void carregarComidas() {
        // Voltando a usar recursos locais (placeholder ic_home por enquanto)
        lista.add(new Comida(R.drawable.pao, "Pão", "Bread"));
        lista.add(new Comida(R.drawable.leite, "Leite", "Milk"));
        lista.add(new Comida(R.drawable.ovo, "Ovo", "Egg"));
        lista.add(new Comida(R.drawable.queijo, "Queijo", "Cheese"));
        lista.add(new Comida(R.drawable.arroz, "Arroz", "Rice"));
        lista.add(new Comida(R.drawable.feijao, "Feijão", "Beans"));
        lista.add(new Comida(R.drawable.carne, "Carne", "Meat"));
        lista.add(new Comida(R.drawable.suco     , "Suco", "Juice"));
    }

    private void falarComida(String nome) {
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
