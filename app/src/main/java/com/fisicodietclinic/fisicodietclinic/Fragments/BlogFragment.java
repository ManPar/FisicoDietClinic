package com.fisicodietclinic.fisicodietclinic.Fragments;


import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.fisicodietclinic.fisicodietclinic.R;

import com.fisicodietclinic.fisicodietclinic.models.Blog;


import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;


public class BlogFragment  extends Fragment{

    private List<Blog> blogList;
Blog blog;
    ProgressDialog dialog;
    CardView cardOne,cardTwo,cardThree,cardFour;
    TextView dateOne,dateTwo,dateThree,dateFour,titleOne,titleTwo,titleThree,titleFour;
    public static BMIFragment newInstance() {
        BMIFragment fragment = new BMIFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        dialog=new ProgressDialog(getActivity());
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitle("Blog");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        cardOne = (CardView) view.findViewById(R.id.card_one);
        cardTwo = (CardView) view.findViewById(R.id.card_two);
        cardThree = (CardView) view.findViewById(R.id.card_three);
        cardFour = (CardView) view.findViewById(R.id.card_four);
        dateOne = (TextView) view.findViewById(R.id.text_date_one);
        dateTwo = (TextView) view.findViewById(R.id.text_date_two);
        dateThree = (TextView) view.findViewById(R.id.text_date_three);
        dateFour = (TextView) view.findViewById(R.id.text_date_four);
        titleOne = (TextView) view.findViewById(R.id.text_title_one);
        titleTwo = (TextView) view.findViewById(R.id.text_title_two);
        titleThree = (TextView) view.findViewById(R.id.text_title_three);
        titleFour = (TextView) view.findViewById(R.id.text_title_four);


            sendR("http://arogyam.rjchoreography.com/wp-json/wp/v2/posts?orderby=date&per_page=4");

        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChrome(blogList.get(0).getLink());
            }
        });

        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChrome(blogList.get(3).getLink());
            }
        });

        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChrome(blogList.get(1).getLink());
            }
        });

        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChrome(blogList.get(2).getLink());
            }
        });



        return view;
    }



    public void sendR(String url)
    {
        dialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        blogList=new ArrayList<>();
                        try {


                            JSONArray array = new JSONArray(response);
                            for (int i=0;i<4;i++) {
                                JSONObject object = array.getJSONObject(i);
                                String date= object.getString("date");
                                String link = object.getString("link");
                                JSONObject title1 = object.getJSONObject("title");
                                String title = title1.getString("rendered");
                                blog = new Blog(title,date,link);
                                blogList.add(blog);
                                Log.d("lolol",title);


                            }
                            dialog.dismiss();
                            dateOne.setText(blogList.get(0).getDate());
                            dateTwo.setText(blogList.get(1).getDate());
                            dateThree.setText(blogList.get(2).getDate());
                            dateFour.setText(blogList.get(3).getDate());
                            titleOne.setText(blogList.get(0).getTitle());
                            titleTwo.setText(blogList.get(1).getTitle());
                            titleThree.setText(blogList.get(2).getTitle());
                            titleFour.setText(blogList.get(3).getTitle());




                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){


        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    public  void openChrome(String url)
    {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        catch(ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        }
    }
}





