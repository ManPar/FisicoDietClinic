package com.fisicodietclinic.fisicodietclinic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.fisicodietclinic.fisicodietclinic.adapters.GridAdapter;
import com.fisicodietclinic.fisicodietclinic.models.Dates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AskQuery extends AppCompatActivity {

    EditText title,description;
    public static final String MyPREFERENCES = "MyPrefs" ;
    Button send;
    String username;
    String title_text,description_text;
    SharedPreferences sharedpreferences;
    String url = "http://arogyam.herokuapp.com/post_query/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_query);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString("user_name","manya" );
        title = (EditText) findViewById(R.id.input_edit_title);
        description = (EditText) findViewById(R.id.input_edit_discription);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_text = title.getText().toString();
                description_text = description.getText().toString();
                sendR(url);


            }
        });


    }


    public void sendR(String url_string)
    {





        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_string,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();

                        try{

                            if(response.equalsIgnoreCase("done"))
                            {
                                Intent in = new Intent(AskQuery.this,MainActivity.class);
                                startActivity(in);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"There was some error",Toast.LENGTH_LONG).show();
                            }





                            //adapter = new GridAdapter(getApplicationContext(), dateList);
                        }catch (Exception e){
                            Log.d("mytag",e.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user",username);
                params.put("topic",title_text);
                params.put("desc",description_text);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
