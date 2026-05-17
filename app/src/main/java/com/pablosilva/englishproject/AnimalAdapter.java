package com.pablosilva.englishproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.pablosilva.englishproject.R;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    List<Animal> lista;
    Context context;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(Animal animal, View view);
    }

    public AnimalAdapter(Context context, List<Animal> lista, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView pt, en;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imgAnimal);
            pt = view.findViewById(R.id.txtNomePt);
            en = view.findViewById(R.id.txtNomeEn);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal = lista.get(position);

        holder.img.setImageResource(animal.imagem);
        holder.pt.setText(animal.nomePt);
        holder.en.setText(animal.nomeEn);

        holder.itemView.setOnClickListener(v -> listener.onClick(animal, holder.img));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}