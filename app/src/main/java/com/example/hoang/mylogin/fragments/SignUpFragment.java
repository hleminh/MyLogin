package com.example.hoang.mylogin.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.hoang.mylogin.R;
import com.example.hoang.mylogin.networks.RegisterRequest;
import com.example.hoang.mylogin.networks.RegisterResponse;
import com.example.hoang.mylogin.networks.RegisterService;
import com.example.hoang.mylogin.utils.InputValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private TextInputEditText etUsername;
    private TextInputEditText etPassword;
    private TextInputEditText etEmail;
    private Button btRegister;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        etUsername = (TextInputEditText) view.findViewById(R.id.et_username);
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!InputValidator.usernameCharValidation(etUsername.getText().toString())) {
                    etUsername.setError("Username must contains minimum 8 characters and at least 1 Alphabet, 1 Number and 1 Special Character");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPassword = (TextInputEditText) view.findViewById(R.id.et_password);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!InputValidator.passwordCharValidation(etPassword.getText().toString())) {
                    etPassword.setError("Password must contains minimum 8 characters and at least 1 Alphabet, 1 Number and 1 Special Character");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        etEmail = (TextInputEditText) view.findViewById(R.id.et_email);
        btRegister = (Button) view.findViewById(R.id.bt_register);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        return view;
    }

    private void register() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterService registerService = retrofit.create(RegisterService.class);
        registerService.register(new RegisterRequest(etUsername.getText().toString(), etPassword.getText().toString())).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 307) {
                    RegisterResponse registerResponse = response.body();
                    Toast.makeText(getContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                }else if (response.code() == 400){
                    Toast.makeText(getContext(), "User already exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getContext(), "No connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
