package com.fisicodietclinic.fisicodietclinic.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fisicodietclinic.fisicodietclinic.AskQuery;
import com.fisicodietclinic.fisicodietclinic.MainActivity;
import com.fisicodietclinic.fisicodietclinic.R;
import com.fisicodietclinic.fisicodietclinic.SignIn;
import com.fisicodietclinic.fisicodietclinic.adapters.QueryAdapter;
import com.fisicodietclinic.fisicodietclinic.adapters.diet_adapter;
import com.fisicodietclinic.fisicodietclinic.models.Diet;
import com.fisicodietclinic.fisicodietclinic.models.Query;
import com.fisicodietclinic.fisicodietclinic.utils.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class QueryFragment extends Fragment {

    private List<Query> queryList = new ArrayList<>();


    private QueryAdapter mAdapter;
    EditText searchEdit;

    String url="http://arogyam.herokuapp.com/api_query/";
    String term;
    FloatingActionButton fab;
    String username,date_string;
    SharedPreferences sharedpreferences;

    public static final String MyPREFERENCES = "MyPrefs" ;

    private RecyclerView recyclerView;

    ProgressDialog dialog;


    public static QueryFragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.query_fragment, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            String datenew = extras.getString("date");


            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            try {
                Date date = sdf.parse(datenew);

                date_string = new SimpleDateFormat("MM").format(date) + new SimpleDateFormat("dd").format(date) + new SimpleDateFormat("yy").format(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
            fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AskQuery.class);
                startActivity(intent);
            }
        });
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString("user_name","manya" );
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_id);
        //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
     //   String name = sharedpreferences.getString("username", "abc");
       // url = "https://medimedi.herokuapp.com/api/getdata/"+name+"/";

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {




                    }
                })
        );


        sendR();



        //sendR("http://arogyam.herokuapp.com/dietdata/");
        return view;
    }


    public void sendR()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       try {
                           Log.d("mm",response);

                           JSONObject object =  new JSONObject(response);
                           JSONObject jsonObject=null;
                           JSONArray array = object.getJSONArray("objects");
                           Query query1;
                           for(int i=0;i<array.length();i++)
                           {
                               jsonObject= array.getJSONObject(i);
                               String title= jsonObject.getString("topic");
                               String query = jsonObject.getString("query");
                               String answer = jsonObject.getString("answer");
                               query1 = new Query(title,query,answer);
                               queryList.add(query1);

                               Log.d("mm",answer);

                           }


                           mAdapter = new QueryAdapter(queryList);
                           recyclerView.setAdapter(mAdapter);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);



    }
}
