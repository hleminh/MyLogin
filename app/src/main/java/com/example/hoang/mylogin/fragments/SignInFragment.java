package com.example.hoang.mylogin.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.hoang.mylogin.R;
import com.example.hoang.mylogin.activities.TasksActivity;
import com.example.hoang.mylogin.networks.LoginRequest;
import com.example.hoang.mylogin.networks.LoginResponse;
import com.example.hoang.mylogin.networks.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private TextInputEditText etUsername;
    private TextInputEditText etPassword;
    private CheckBox cbRemember;
    private Button btLogin;
    private ProgressDialog progressDialog;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        etUsername = (TextInputEditText) view.findViewById(R.id.et_username);
        etPassword = (TextInputEditText) view.findViewById(R.id.et_password);
        cbRemember = (CheckBox) view.findViewById(R.id.cb_remember);
        btLogin = (Button) view.findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        loadPref();
        progressDialog = new ProgressDialog(getContext());
        return view;
    }

    private void login() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Processing...");
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);
        loginService.login(new LoginRequest(etUsername.getText().toString(), etPassword.getText().toString())).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getStatus_code() == 0) {
                        Toast.makeText(getContext(), "You have logged in", Toast.LENGTH_SHORT).show();
                        if (cbRemember.isChecked()) {
                            savePref(etUsername.getText().toString(), etPassword.getText().toString());
                        }
                        if (progressDialog.isShowing()) progressDialog.dismiss();
                        Intent intent = new Intent(getActivity(), TasksActivity.class);
                        intent.putExtra("token", response.body().getAccess_token());
                        startActivity(intent);
                    }
                }
                if (response.code() == 401) {
                    if (progressDialog.isShowing()) progressDialog.dismiss();
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                if (progressDialog.isShowing()) progressDialog.dismiss();
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void savePref(String username, String password) {
        SharedPreferences sharedPreferences = SignInFragment.this.getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    private void loadPref() {
        SharedPreferences sharedPreferences = SignInFragment.this.getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        etUsername.setText(sharedPreferences.getString("username", ""));
        etPassword.setText(sharedPreferences.getString("password", ""));
        System.out.println(etUsername.getText());
        System.out.println(etPassword.getText());
        if (!etUsername.getText().toString().equals("") && !etPassword.getText().toString().equals("")) {
            cbRemember.setChecked(true);
        }
    }
}
