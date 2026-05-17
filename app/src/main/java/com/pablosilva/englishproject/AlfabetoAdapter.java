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

public class AlfabetoAdapter extends RecyclerView.Adapter<AlfabetoAdapter.ViewHolder> {

    private final List<Letra> lista;
    private final TextToSpeech tts;

    public AlfabetoAdapter(List<Letra> lista, TextToSpeech tts) {
        this.lista = lista;
        this.tts = tts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLetra;
        MaterialCardView cardLetra;

        public ViewHolder(View itemView) {
            super(itemView);
            txtLetra = itemView.findViewById(R.id.txtLetra);
            cardLetra = itemView.findViewById(R.id.cardLetra);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_letra, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Letra letra = lista.get(position);

        holder.txtLetra.setText(letra.letra);
        holder.cardLetra.setCardBackgroundColor(letra.corFundo);

        holder.itemView.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak(letra.letra, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}