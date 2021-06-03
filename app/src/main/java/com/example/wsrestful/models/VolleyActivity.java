package com.example.wsrestful.models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wsrestful.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    EditText edtJ_id;
    Button btnBuscar;
    TextView txtResultado;

    RequestQueue requestQueue;
    private static final String URL1 = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=2";

    private static final String URL2 = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        requestQueue = Volley.newRequestQueue(this);

        edtJ_id = findViewById(R.id.edtJ_id_v);
        btnBuscar = findViewById(R.id.btnBuscar_v);
        txtResultado = findViewById(R.id.txtResultado_v);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultado.setText("");
                jsonArrayRequest();
                //stringRequest();

            }
        });


    }

    private void jsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL2+edtJ_id.getText().toString(),
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int size = response.length();
                        for(int i=0; i<size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());

                                String title = jsonObject.getString("title");
                                String issue_id = jsonObject.getString("issue_id");
                                String volume = jsonObject.getString("volume");
                                String number = jsonObject.getString("number");
                                String year = jsonObject.getString("year");
                                String date_published = jsonObject.getString("date_published");

                                txtResultado.append("Title :" + title + "\n");
                                txtResultado.append("Issue_id :" + issue_id + "\n");
                                txtResultado.append("Volume :" + volume + "\n");
                                txtResultado.append("Number :" + number + "\n");
                                txtResultado.append("Year :" + year + "\n");
                                txtResultado.append("Date Published :" + date_published + "\n" + "\n");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }
    private void stringRequest(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtResultado.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);
    }

}