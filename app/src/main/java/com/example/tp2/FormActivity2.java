package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;

public class FormActivity2 extends AppCompatActivity {

    private Intent intentValoresContact;
    private TextView tvInfoContact;

    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private CheckBox cb1, cb2, cb3, cb4;
    private Switch swSi;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form2);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        intentValoresContact = getIntent();
        tvInfoContact = (TextView) findViewById(R.id.tvInfoContact);
        tvInfoContact.setText(intentValoresContact.getStringExtra("ValoresContact"));
        tvInfoContact.setVisibility(TextView.INVISIBLE);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        swSi = (Switch) findViewById(R.id.swSi);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

    }

    public void btnGuardar_click(View view) {

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String radioButtonValue = selectedRadioButton.getText().toString();

        String valores = "";
        valores += tvInfoContact.getText().toString();
        valores += ";" + radioButtonValue;
        valores += ";" + (cb1.isChecked() ? "true" : "false");
        valores += ";" + (cb2.isChecked() ? "true" : "false");
        valores += ";" + (cb3.isChecked() ? "true" : "false");
        valores += ";" + (cb4.isChecked() ? "true" : "false");
        valores += ";" + (swSi.isChecked() ? "true" : "false");

        grabarTxt(valores);
    }

    private void grabarTxt(String valores) {
        String data = valores + "\n";
        FileOutputStream fos = null;
        try {
            // Abre o crea el archivo "datos.txt" en el almacenamiento interno
            fos = openFileOutput("datos.txt", MODE_APPEND);
            fos.write(data.getBytes());
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}