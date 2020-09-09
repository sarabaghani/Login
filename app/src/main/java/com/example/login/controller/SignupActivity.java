package com.example.login.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.R;

public class SignupActivity extends AppCompatActivity {

    public static final String EXTRA_CHOSENUSER = "chosenuser";
    public static final String EXTRA_CHOSENPASS = "chosenpass";
    private EditText mEditTextUser;
    private EditText mEditTextPass;
    private Button mButtonSignup;

String preUser;
String prePass;
    private String mUser;
    private String mPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intent startIntent = getIntent();
        findViews();
        if(startIntent.getStringExtra(MainActivity.EXTRA_PASS_SENDING_TO_SIGNUP)!=null&&
                startIntent.getStringExtra(MainActivity.EXTRA_USER_SENDING_TO_SIGNUP)!=null){
            preUser= startIntent.getStringExtra(MainActivity.EXTRA_USER_SENDING_TO_SIGNUP);
            prePass = startIntent.getStringExtra(MainActivity.EXTRA_PASS_SENDING_TO_SIGNUP);
            mEditTextUser.setText(preUser);
            mEditTextPass.setText(prePass);}
        setListeners();

    }

    private void setListeners() {
        mEditTextUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUser = mEditTextUser.getText().toString();
//                Toast.makeText(SignupActivity.this, mUser, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mUser = mEditTextUser.getText().toString();
            }
        });
        mEditTextPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPass = mEditTextPass.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {
mPass=mEditTextPass.getText().toString();
            }
        });
        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra(EXTRA_CHOSENUSER, mUser);
                data.putExtra(EXTRA_CHOSENPASS, mPass);
                if(mUser==null||mPass==null)
                    Toast.makeText(SignupActivity.this,
                            R.string.missed_field,Toast.LENGTH_SHORT).show();
                else{
                setResult(RESULT_OK, data);
                finish();}
            }
        });
    }

    private void findViews() {
        mEditTextUser = findViewById(R.id.username_signup);
        mEditTextPass = findViewById(R.id.pass_signup);
        mButtonSignup = findViewById(R.id.signup_btn_signup);
    }
}