package com.pablosilva.englishproject;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NumerosActivity extends BaseActivity {

    private RecyclerView recycler;
    private NumeroAdapter adapter;
    private List<Numero> lista = new ArrayList<>();
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);

        setupDrawer();

        recycler = findViewById(R.id.recyclerNumeros);
        recycler.setLayoutManager(new GridLayoutManager(this, 3)); // 3 colunas como o Alfabeto

        // TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        gerarNumeros();

        adapter = new NumeroAdapter(lista, tts);
        recycler.setAdapter(adapter);
    }

    private void gerarNumeros() {
        String[] cores = {
                "#FF6B6B", "#FFD93D", "#6BCB77", "#4D96FF", "#9B59B6", "#FF9F43", "#FF85A2"
        };

        int corIndex = 0;
        // 🔥 GERAR 1 A 100 AUTOMATICAMENTE COM CORES
        for (int i = 1; i <= 100; i++) {
            lista.add(new Numero(
                    String.valueOf(i),
                    numeroPorExtenso(i),
                    Color.parseColor(cores[corIndex])
            ));
            corIndex = (corIndex + 1) % cores.length;
        }
    }

    // 🔥 CONVERTE NÚMERO PARA INGLÊS
    private String numeroPorExtenso(int numero) {

        String[] unidades = {
                "Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"
        };

        String[] especiais = {
                "Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen",
                "Sixteen","Seventeen","Eighteen","Nineteen"
        };

        String[] dezenas = {
                "", "", "Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"
        };

        if (numero < 10) {
            return unidades[numero];
        } else if (numero < 20) {
            return especiais[numero - 10];
        } else if (numero < 100) {
            int dezena = numero / 10;
            int unidade = numero % 10;

            if (unidade == 0) {
                return dezenas[dezena];
            } else {
                return dezenas[dezena] + " " + unidades[unidade];
            }
        } else {
            return "One Hundred";
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