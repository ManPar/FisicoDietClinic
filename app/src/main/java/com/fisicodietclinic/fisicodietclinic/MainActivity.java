package com.fisicodietclinic.fisicodietclinic;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.fisicodietclinic.fisicodietclinic.Fragments.BMIFragment;
import com.fisicodietclinic.fisicodietclinic.Fragments.BlogFragment;
import com.fisicodietclinic.fisicodietclinic.Fragments.DietFragment;
import com.fisicodietclinic.fisicodietclinic.Fragments.ProfileFragment;
import com.fisicodietclinic.fisicodietclinic.Fragments.QueryFragment;
import com.fisicodietclinic.fisicodietclinic.Fragments.UploadFragment;
import com.roughike.bottombar.BottomBar;

import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragment = new DietFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_container, fragment).commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_diet:
                                fragment = new DietFragment();
                                break;

                            case R.id.action_bmi:
                                fragment = new BlogFragment();
                                break;
                            case R.id.action_ask:
                                fragment = new ProfileFragment();
                                break;
                        }
                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_container, fragment).commit();
                        return true;
                    }
                });
    }
}
