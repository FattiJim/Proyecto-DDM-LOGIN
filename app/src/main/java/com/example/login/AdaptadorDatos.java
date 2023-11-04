package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AccessController;
import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos>{
    ArrayList<usuarioItem> listDatos;

    public AdaptadorDatos(ArrayList<usuarioItem> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public AdaptadorDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, null, false);
        return new ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));


    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txtNombre, txtCorreo, txtPass, txtID;
        ImageView imageView7;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtID = (TextView) itemView.findViewById(R.id.textView6);
            txtNombre = (TextView) itemView.findViewById(R.id.textView8);
            txtCorreo = (TextView) itemView.findViewById(R.id.textView12);
            txtPass = (TextView) itemView.findViewById(R.id.textView14);
            imageView7 = (ImageView) itemView.findViewById(R.id.imageView6);


        }

        public void asignarDatos(usuarioItem Item) {
            txtID.setText(Item.id.toString());
            txtNombre.setText(Item.nombre.toString());
            txtCorreo.setText(Item.correo.toString());
            txtPass.setText(Item.pass.toString());

            String idImg = Item.imagen.toString();
            if (idImg.equals("1")){
                imageView7.setImageResource(R.drawable.a1);
            }if (idImg.equals("2")){
                imageView7.setImageResource(R.drawable.a2);
            }
            if (idImg.equals("3")){
                imageView7.setImageResource(R.drawable.a3);
            }
            if (idImg.equals("4")){
                imageView7.setImageResource(R.drawable.a4);
            }
            if (idImg.equals("5")){
                imageView7.setImageResource(R.drawable.a5);
            }
        }
    }
}
