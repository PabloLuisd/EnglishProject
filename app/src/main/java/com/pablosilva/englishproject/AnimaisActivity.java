package com.pablosilva.englishproject;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnimaisActivity extends BaseActivity {

    private TextToSpeech tts;
    private Animation anim;

    private RecyclerView recycler;
    private AnimalAdapter adapter;
    private List<Animal> lista = new ArrayList<>();

    private LinearLayout layoutPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animais);

        setContentView(R.layout.activity_animais);
        setupDrawer();

        layoutPrincipal = findViewById(R.id.layoutPrincipal);

        recycler = findViewById(R.id.recyclerAnimais);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));

        anim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        adapter = new AnimalAdapter(this, lista, (animal, view) -> {
            if (anim != null) view.startAnimation(anim);
            falarAnimal(animal.nomeEn);
        });

        recycler.setAdapter(adapter);

        RadioGroup radioGroup = findViewById(R.id.radioGroupCategorias);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbTerrestre) {
                carregarAnimais(getTerrestres(), "#D3D3D3");
            } else if (checkedId == R.id.rbAquatico) {
                carregarAnimais(getAquaticos(), "#B3E5FC");
            } else if (checkedId == R.id.rbAereo) {
                carregarAnimais(getAereos(), "#F5F5F5");
            }
        });

        radioGroup.check(R.id.rbTerrestre);
    }

    // 🔥 MÉTODO CENTRAL (evita repetição)
    private void carregarAnimais(List<Animal> animais, String corFundo) {
        lista.clear();
        lista.addAll(animais);

        layoutPrincipal.setBackgroundColor(Color.parseColor(corFundo));

        adapter.notifyDataSetChanged();
    }

    // 📦 LISTAS ORGANIZADAS

    private List<Animal> getTerrestres() {
        List<Animal> animais = new ArrayList<>();
        animais.add(new Animal(R.drawable.leao, "Leão", "Lion"));
        animais.add(new Animal(R.drawable.elefante, "Elefante", "Elephant"));
        animais.add(new Animal(R.drawable.tigre, "Tigre", "Tiger"));
        animais.add(new Animal(R.drawable.cachorro, "Cachorro", "Dog"));
        animais.add(new Animal(R.drawable.lobo, "Lobo", "Wolf"));
        animais.add(new Animal(R.drawable.gato, "Gato", "Cat"));
        animais.add(new Animal(R.drawable.cavalo, "Cavalo", "Horse"));
        animais.add(new Animal(R.drawable.urso, "Urso", "Bear"));
        animais.add(new Animal(R.drawable.camelo, "Camelo", "Camel"));
        animais.add(new Animal(R.drawable.girafa, "Girafa", "Giraffe"));
        animais.add(new Animal(R.drawable.cobra, "Cobra", "Snake"));
        animais.add(new Animal(R.drawable.vaca, "Vaca", "Cow"));
        animais.add(new Animal(R.drawable.boi, "Boi", "Ox"));
        animais.add(new Animal(R.drawable.ovelha, "Ovelha", "Sheep"));
        return animais;
    }

    private List<Animal> getAquaticos() {
        List<Animal> animais = new ArrayList<>();
        animais.add(new Animal(R.drawable.peixe, "Peixe", "Fish"));
        animais.add(new Animal(R.drawable.tubarao, "Tubarão", "Shark"));
        animais.add(new Animal(R.drawable.baleia, "Baleia", "Whale"));
        animais.add(new Animal(R.drawable.golfinho, "Golfinho", "Dolphin"));
        animais.add(new Animal(R.drawable.tartaruga, "Tartaruga", "Turtle"));
        animais.add(new Animal(R.drawable.polvo, "Polvo", "Octopus"));
        animais.add(new Animal(R.drawable.lula, "Lula", "Squid"));
        return animais;
    }

    private List<Animal> getAereos() {
        List<Animal> animais = new ArrayList<>();
        animais.add(new Animal(R.drawable.aguia, "Águia", "Eagle"));
        animais.add(new Animal(R.drawable.passaro, "Pássaro", "Bird"));
        animais.add(new Animal(R.drawable.coruja, "Coruja", "Owl"));
        animais.add(new Animal(R.drawable.papagaio, "Papagaio", "Parrot"));
        animais.add(new Animal(R.drawable.abelha, "Abelha", "Bee"));
        animais.add(new Animal(R.drawable.morcego, "Morcego", "Bat"));
        return animais;
    }

    private void falarAnimal(String nome) {
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