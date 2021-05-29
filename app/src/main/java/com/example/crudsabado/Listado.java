package com.example.crudsabado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ArrayList<Trabajador> listadoTrabajadores=new ArrayList<>();
    RecyclerView listado;
    //Atributo que almacena la conexion a la bd
    FirebaseFirestore baseDatos = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);


        listado=findViewById(R.id.listado);
        listado.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        crearListado();

    }

    private void crearListado(){

        baseDatos.collection("empleados")
                .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){

                               for(QueryDocumentSnapshot document : task.getResult()){

                                   String nombre = document.get("nombre").toString();
                                   String cedula = document.get("cedula").toString();
                                   String cargo = document.get("cargo").toString();
                                   String foto=document.get("foto").toString();
                                   listadoTrabajadores.add(new Trabajador(nombre,cedula,cargo,foto));
                               }
                                Adaptador adaptador = new Adaptador(listadoTrabajadores);
                                listado.setAdapter(adaptador);
                            }else{
                                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                            }
                        }
                    });



    }
}