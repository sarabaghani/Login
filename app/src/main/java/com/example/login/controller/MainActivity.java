package com.example.login.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.login.R;
import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.Snackbar.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SIGNUP = 0;
    public static final String EXTRA_USER_SENDING_TO_SIGNUP = "userSendingToSignup";
    public static final String EXTRA_PASS_SENDING_TO_SIGNUP = "passSendingToSignup";
    private Button mButtonSignup;
    private Button mButtonLogin;
    private EditText mUser;
    private EditText mPass;
    private LinearLayout mMainAct;

    private String mValidUser;
    private String mToValidateUser;
    private String mValidPass;
    private String mToValidatePass;


    private String mUserToSend;
    private String mPassToSend;
    private boolean mIsSignedup= false;

    Snackbar mSnackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
/*        if(mValidUser!=null)
            mUser.setText(mValidUser);*/
    }

    private void setListeners() {
        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                if (mUser.getText().toString() != null) {
                    mUserToSend = mUser.getText().toString();
                    intent.putExtra(EXTRA_USER_SENDING_TO_SIGNUP, mUserToSend);
                }
                if (mPass.getText().toString() != null) {
                    mPassToSend = mPass.getText().toString();
                    intent.putExtra(EXTRA_PASS_SENDING_TO_SIGNUP, mPassToSend);
                }
                startActivityForResult(intent, REQUEST_CODE_SIGNUP);
            }
        });

        mUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mToValidateUser = mUser.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mToValidatePass = mPass.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mIsSignedup){
                    mSnackbar=Snackbar.make(mMainAct,R.string.signup_required,LENGTH_LONG);
                    mSnackbar.show();}
                else if (mValidUser.equals(mToValidateUser) && mValidPass.equals(mToValidatePass)) {
                    mSnackbar = Snackbar.make(mMainAct, R.string.successful_login,
                            LENGTH_LONG);
                    mSnackbar.show();
                } else{
                    Toast.makeText(MainActivity.this,
                            R.string.mismatched_values,
                            Toast.LENGTH_SHORT).show();}

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_SIGNUP) {
            mIsSignedup=true;
            mValidUser = data.getStringExtra(SignupActivity.EXTRA_CHOSENUSER);
            mUser.setText(mValidUser);
            mValidPass = data.getStringExtra(SignupActivity.EXTRA_CHOSENPASS);
            mPass.setText(mValidPass);
        }
    }

    private void findViews() {
        mButtonSignup = findViewById(R.id.signup_btn);
        mButtonLogin = findViewById(R.id.login_btn);
        mUser = findViewById(R.id.username_txtedit);
        mPass = findViewById(R.id.pass_txtedit);
        mMainAct = findViewById(R.id.main_layout);
    }
}