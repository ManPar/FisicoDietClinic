package com.fisicodietclinic.fisicodietclinic.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fisicodietclinic.fisicodietclinic.BMIActivity;
import com.fisicodietclinic.fisicodietclinic.BMIDetails;
import com.fisicodietclinic.fisicodietclinic.QueryActivity;
import com.fisicodietclinic.fisicodietclinic.R;
import com.fisicodietclinic.fisicodietclinic.UploadActivity;

public class ProfileFragment extends Fragment {

    CardView query_card,upload_card,bmi_card;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);
        query_card = (CardView) view.findViewById(R.id.action_ask);
        upload_card= (CardView) view.findViewById(R.id.action_upload);
        bmi_card = (CardView) view.findViewById(R.id.action_bmi);

        query_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(getActivity(), QueryActivity.class);
                startActivity(in);

            }
        });


        upload_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(getActivity(), UploadActivity.class);
                startActivity(in);


            }
        });

        bmi_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(getActivity(), BMIActivity.class);
                startActivity(in);

            }
        });




        return view;
    }
}
