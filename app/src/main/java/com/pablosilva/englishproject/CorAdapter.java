package com.pablosilva.englishproject;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CorAdapter extends RecyclerView.Adapter<CorAdapter.ViewHolder> {

    private final List<Cor> lista;
    private final TextToSpeech tts;

    public CorAdapter(List<Cor> lista, TextToSpeech tts) {
        this.lista = lista;
        this.tts = tts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome;
        MaterialCardView cardCor;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            cardCor = itemView.findViewById(R.id.cardCor);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cor cor = lista.get(position);

        holder.txtNome.setText(cor.nomeEn);

        int colorValue = Color.parseColor(cor.corHex);
        holder.cardCor.setCardBackgroundColor(colorValue);

        // 🔥 AJUSTE DE COR DO TEXTO PARA LEGIBILIDADE
        if (cor.nomeEn.equalsIgnoreCase("White") || 
            cor.nomeEn.equalsIgnoreCase("Yellow") || 
            cor.nomeEn.equalsIgnoreCase("Beige") ||
            cor.nomeEn.equalsIgnoreCase("Silver")) {
            holder.txtNome.setTextColor(Color.BLACK);
        } else {
            holder.txtNome.setTextColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak(cor.nomeEn, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}