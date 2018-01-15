package com.fisicodietclinic.fisicodietclinic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fisicodietclinic.fisicodietclinic.adapters.diet_adapter;
import com.fisicodietclinic.fisicodietclinic.models.Diet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manyamadan on 07/01/18.
 */

public class BMIActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private diet_adapter adapter;
    private List<Diet> dietsList = new ArrayList<>();
    ProgressDialog dialog;
    String response_string;
    Button calculate;
    EditText edit_weight,edit_height;
    String weight,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bmi);


        calculate = (Button) findViewById(R.id.id_calculate);
        edit_height = (EditText) findViewById(R.id.input_edit_height);
        edit_weight = (EditText) findViewById(R.id.input_edit_weight);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weight = edit_weight.getText().toString();
                height = edit_height.getText().toString();
                Intent intent = new Intent(getApplicationContext(), BMIDetails.class);
                intent.putExtra("weight",weight);
                intent.putExtra("height",height);
                startActivity(intent);

            }
        });





    }




}
