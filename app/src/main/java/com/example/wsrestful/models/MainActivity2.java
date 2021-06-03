package com.example.wsrestful.models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wsrestful.R;

public class MainActivity2 extends AppCompatActivity {

    Button btn_retrofit;
    Button btn_volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_retrofit = findViewById(R.id.btnRetrofit);
        btn_volley = findViewById(R.id.btnVolley);

        btn_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btn_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VolleyActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }


}