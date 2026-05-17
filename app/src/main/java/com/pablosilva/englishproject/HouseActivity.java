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

public class HouseActivity extends BaseActivity {

    private TextToSpeech tts;
    private Animation anim;
    private RecyclerView recycler;
    private ObjetoCasaAdapter adapter;
    private List<ObjetoCasa> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        setupDrawer();

        recycler = findViewById(R.id.recyclerHouse);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));

        anim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        carregarObjetos();

        adapter = new ObjetoCasaAdapter(this, lista, (objeto, view) -> {
            if (anim != null) view.startAnimation(anim);
            falarObjeto(objeto.nomeEn);
        });

        recycler.setAdapter(adapter);
    }

    private void carregarObjetos() {
        // Nota: Substitua R.drawable.ic_home pelos nomes das suas imagens reais de objetos da casa
        lista.add(new ObjetoCasa(R.drawable.cama, "Cama", "Bed"));
        lista.add(new ObjetoCasa(R.drawable.cadeira, "Cadeira", "Chair"));
        lista.add(new ObjetoCasa(R.drawable.mesa, "Mesa", "Table"));
        lista.add(new ObjetoCasa(R.drawable.sofa, "Sofá", "Sofa"));
        lista.add(new ObjetoCasa(R.drawable.geladeira, "Geladeira", "Fridge"));
        lista.add(new ObjetoCasa(R.drawable.fogao, "Fogão", "Stove"));
        lista.add(new ObjetoCasa(R.drawable.tv, "Televisão", "Television"));
        lista.add(new ObjetoCasa(R.drawable.janela, "Janela", "Window"));
        lista.add(new ObjetoCasa(R.drawable.porta, "Porta", "Door"));
        lista.add(new ObjetoCasa(R.drawable.espelho, "Espelho", "Mirror"));
    }

    private void falarObjeto(String nome) {
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