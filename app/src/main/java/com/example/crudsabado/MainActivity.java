package com.example.crudsabado;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //ATRIBUTOS

    EditText cajaNombre,cajaCedula,cajaCargo;
    Button botonRegistrar,botonListar;

    //Atributo que almacena la conexion a la bd
    FirebaseFirestore baseDatos = FirebaseFirestore.getInstance();

    //Atributo que cree la estructura de la colección a almacenar
    Map<String, Object> empleado = new HashMap<>();



    //METODOS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se crean las instancias de las vistas enlazandolas con la logica;
        cajaNombre=findViewById(R.id.nombre);
        cajaCedula=findViewById(R.id.cedula);
        cajaCargo=findViewById(R.id.cargo);

        botonRegistrar=findViewById(R.id.botonRegistrar);
        botonListar=findViewById(R.id.botonBuscar);

        //Escuchador de clic del boton registrar
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Se obtienen los valores de cada EDITTEXT
                String nombre=cajaNombre.getText().toString();
                String cedula=cajaCedula.getText().toString();
                String cargo=cajaCargo.getText().toString();

                //Conectandome a la BD

                //1. Armar mi coleccion
                empleado.put("nombre",nombre);
                empleado.put("cedula",cedula);
                empleado.put("cargo",cargo);

                //2. Llamar al metodo registrarEMpleado
                registrarEmpleado();
            }
        });

        botonListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(MainActivity.this,Listado.class);
               startActivity(intent);


            }
        });

    }

    private void registrarEmpleado(){

        baseDatos.collection("empleados")
                .add(empleado)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        cajaNombre.setText("");
                        Toast.makeText(getApplicationContext(),"Exito agregando el empleado",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"Error: "+e,Toast.LENGTH_LONG).show();

                    }
                });


    }

}