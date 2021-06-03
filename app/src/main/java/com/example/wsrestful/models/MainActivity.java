package com.example.wsrestful.models;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wsrestful.interfaces.revistaService;
import com.example.wsrestful.models.revista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wsrestful.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText edtJ_id;
    Button btnBuscar;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtJ_id = findViewById(R.id.edtJ_id);
        btnBuscar = findViewById(R.id.btnBuscar);
        txtResultado = findViewById(R.id.txtResultado);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultado.setText("");
                resultadoRetrofit(edtJ_id.getText().toString());


            }
        });
    }


    public void resultadoRetrofit(String J_id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        revistaService postService = retrofit.create(revistaService.class);
        Call<List<revista>> call = postService.findO(J_id);
        call.enqueue(new Callback<List<revista>>() {
            @Override
            public void onResponse(Call<List<revista>> call, Response<List<revista>> response) {
                List<revista> postList = response.body();
                for(revista p: postList){
                    String resultado = "";
                    resultado+="Title: " + p.getTitle() + "\n";
                    resultado+="Issue_id: " + p.getIssue_id() + "\n";
                    resultado+="Volume: " + p.getVolume() + "\n";
                    resultado+="Number: " + p.getNumber() + "\n";
                    resultado+="Year: " + p.getYear() + "\n";
                    resultado+="Date Published: " + p.getDate_published() + "\n" + "\n";
                    txtResultado.append(resultado);
                }
            }

            @Override
            public void onFailure(Call<List<revista>> call, Throwable t) {

            }
        });


    }
}