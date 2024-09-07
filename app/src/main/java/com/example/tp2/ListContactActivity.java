package com.example.tp2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ListContactActivity extends AppCompatActivity {

    private ListView lvItems;
    private ArrayAdapter<String> adapter;
    //private TextView tvFullContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_contact);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        //tvFullContact = findViewById(R.id.tvFullContact);
        lvItems = findViewById(R.id.listViewDatos);

        // Leer los datos del archivo
        List<String> listaDatos = leerDatos();

        List<String> lsDatosFiltro = filtrarNombresMail(listaDatos);
        // Crear un ArrayAdapter para mostrar los datos en el ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsDatosFiltro);
        lvItems.setAdapter(adapter);

        // Configurar el listener para los ítems del ListView
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el dato original correspondiente al ítem clickeado
                String datosItem = listaDatos.get(position);

                // Mostrar los detalles en el TextView
                mostrarDetallesDialog(datosItem); // Asegúrate de que el TextView sea visible

            }
        });
    }

    private void mostrarDetallesDialog(String datosItem) {
        // Crear un AlertDialog para mostrar los detalles
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalles del Contacto");

        // Mostrar el dato completo en el cuerpo del diálogo
        builder.setMessage(datosItem);

        // Agregar un botón para cerrar el diálogo
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private List<String> filtrarNombresMail(List<String> lsDatos) {
        int num = 0;
        List<String> valores = new ArrayList<>(lsDatos.size());
       for(String datos : lsDatos) {
           String[] cadena = datos.split(";");
           String valor = cadena[0] + " " + cadena[1] + "-" + cadena[4] + "\n";
           valores.add(valor);
       }
       return  valores;
    }

    private List<String> leerDatos() {
        List<String> listaDatos = new ArrayList<>();
        FileInputStream fis = null;

        try {
            fis = openFileInput("datos.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                listaDatos.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaDatos;
    }
}