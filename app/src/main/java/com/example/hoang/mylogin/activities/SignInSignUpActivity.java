package com.example.hoang.mylogin.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoang.mylogin.R;
import com.example.hoang.mylogin.fragments.SignInFragment;
import com.example.hoang.mylogin.fragments.SignUpFragment;

public class SignInSignUpActivity extends AppCompatActivity {
    private SignUpFragment signUpFragment;
    private SignInFragment signInFragment;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
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
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            if (i == 0)
                p.setMargins(0, 0, 100, 0);
            if (i == 1)
                p.setMargins(100, 0, 0, 0);
            tab.requestLayout();
        }
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
