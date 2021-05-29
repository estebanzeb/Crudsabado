package com.example.crudsabado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.viewHolder> {

    ArrayList<Trabajador> listadoTrabajadores;

    public Adaptador(ArrayList<Trabajador> listadoTrabajadores) {
        this.listadoTrabajadores = listadoTrabajadores;
    }

    @NonNull
    @Override
    public Adaptador.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vistaItem= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista,parent,false);
        return new viewHolder(vistaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.viewHolder holder, int position) {

        holder.actualizarItem(listadoTrabajadores.get(position));

    }

    @Override
    public int getItemCount() {
        return listadoTrabajadores.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView nombre,cedula,cargo;
        //Refactorizar agregar foto
        ImageView foto;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.itemNombre);
            cedula=itemView.findViewById(R.id.itemCedula);
            cargo=itemView.findViewById(R.id.itemCargo);
            foto=itemView.findViewById(R.id.itemFoto);

        }

        public void actualizarItem(Trabajador trabajador) {

            nombre.setText(trabajador.getNombre());
            cedula.setText(trabajador.getCedula());
            cargo.setText(trabajador.getCargo());

            Picasso.with(itemView.getContext())
                .load(trabajador.getFotoURL())
                    .into(foto);


        }
    }
}