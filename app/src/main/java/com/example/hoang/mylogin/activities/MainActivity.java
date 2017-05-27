package com.example.hoang.mylogin.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hoang.mylogin.R;
import com.example.hoang.mylogin.fragments.SignInFragment;
import com.example.hoang.mylogin.fragments.SignUpFragment;

public class MainActivity extends AppCompatActivity {
    private SignUpFragment signUpFragment;
    private SignInFragment signInFragment;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("SIGN IN"));
        tabLayout.addTab(tabLayout.newTab().setText("SIGN UP"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("SIGN IN"))
                    changeScreen(new SignInFragment(), false);
                if (tab.getText().equals("SIGN UP"))
                    changeScreen(new SignUpFragment(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        displayStartScreen();
    }

    private void displayStartScreen() {
        signInFragment = new SignInFragment();
        changeScreen(signInFragment, false);
    }

    public void changeScreen(Fragment fragment, boolean addToBackStack) {
        if (addToBackStack)
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
    }
}
