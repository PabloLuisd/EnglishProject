package com.pablosilva.englishproject;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class NumeroAdapter extends RecyclerView.Adapter<NumeroAdapter.ViewHolder> {

    private final List<Numero> lista;
    private final TextToSpeech tts;

    public NumeroAdapter(List<Numero> lista, TextToSpeech tts) {
        this.lista = lista;
        this.tts = tts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNumero, txtNome;
        MaterialCardView cardNumero;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNumero = itemView.findViewById(R.id.txtNumero);
            txtNome = itemView.findViewById(R.id.txtNome);
            cardNumero = itemView.findViewById(R.id.cardNumero);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_numero, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Numero numero = lista.get(position);

        holder.txtNumero.setText(numero.numero);
        holder.txtNome.setText(numero.nomeEn);
        holder.cardNumero.setCardBackgroundColor(numero.corFundo);

        holder.itemView.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak(numero.nomeEn, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}