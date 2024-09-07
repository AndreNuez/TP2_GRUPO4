package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class FormActivity1 extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion, txtFecNac;
    private Spinner spItems1, spItems2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form1);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtFecNac = (EditText) findViewById(R.id.txtFecNac);
        spItems1 = (Spinner) findViewById(R.id.spItems1);
        spItems2 = (Spinner) findViewById(R.id.spItems2);

    }

    public void btnContinue_click (View view) {

        String valores = "";
        valores = txtNombre.getText().toString();
        valores += ";" + txtApellido.getText().toString();
        valores += ";" +  txtTelefono.getText().toString();
        valores += ";" +  spItems1.getSelectedItem().toString();
        valores += ";" +  txtEmail.getText().toString();
        valores += ";" +  spItems2.getSelectedItem().toString();
        valores += ";" +  txtDireccion.getText().toString();
        valores += ";" +  txtFecNac.getText().toString();

        Intent intent = new Intent( this, FormActivity2.class);
        intent.putExtra("ValoresContact", valores);
        startActivity(intent);
    }

}