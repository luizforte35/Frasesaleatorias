package com.example.frasesaleatrias;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frases);
   }
    public void geraraFrase(View view) {
        Button botao = findViewById(R.id.buttonFrases);TextView texto = findViewById(R.id.textView);
        String url = "https://api.kanye.rest/"; // Corrected URL

        RequestQueue filaRequisicoes = Volley.newRequestQueue(this);
        StringRequest requisicaoString = new StringRequest(Request.Method.GET, url,response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String quote = jsonObject.getString("quote");
                texto.setText(quote);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
            }
        },
                error -> {
                    Toast.makeText(MainActivity.this, "Error fetching quote", Toast.LENGTH_SHORT).show();
                });

        filaRequisicoes.add(requisicaoString);
    }



}