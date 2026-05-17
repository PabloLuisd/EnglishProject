package com.pablosilva.englishproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablosilva.englishproject.R;

import java.util.List;

public class ObjetoCasaAdapter extends RecyclerView.Adapter<ObjetoCasaAdapter.ViewHolder> {

    private final List<ObjetoCasa> lista;
    private final Context context;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(ObjetoCasa objeto, View view);
    }

    public ObjetoCasaAdapter(Context context, List<ObjetoCasa> lista, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView pt, en;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imgAnimal); // Reusing animal item layout ids
            pt = view.findViewById(R.id.txtNomePt);
            en = view.findViewById(R.id.txtNomeEn);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ObjetoCasa objeto = lista.get(position);

        holder.img.setImageResource(objeto.imagem);
        holder.pt.setText(objeto.nomePt);
        holder.en.setText(objeto.nomeEn);

        holder.itemView.setOnClickListener(v -> listener.onClick(objeto, holder.img));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}