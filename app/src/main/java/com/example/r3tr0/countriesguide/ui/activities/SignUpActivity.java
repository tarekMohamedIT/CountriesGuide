package com.example.r3tr0.countriesguide.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r3tr0.countriesguide.R;
import com.example.r3tr0.countriesguide.core.Regex;
import com.example.r3tr0.countriesguide.core.events.OnFirebaseAuthProcessEndListener;
import com.example.r3tr0.countriesguide.core.models.User;
import com.example.r3tr0.countriesguide.interactors.managers.SignInManager;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends BaseAuthActivity {
    private SignInManager manager;

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        initManager();
    }

    void initViews(){
        emailEditText = findViewById(R.id.loginEmailEditText);
        passwordEditText = findViewById(R.id.loginPasswordEditText);
    }

    void initManager() {
        manager = new SignInManager(this);
        manager.setOnFirebaseAuthSignupEndListener(new OnFirebaseAuthProcessEndListener() {
            @Override
            public void onSuccess(FirebaseUser user) {
                hideProgressDialog();
                new AlertDialog.Builder(SignUpActivity.this)
                        .setTitle("Success")
                        .setMessage("Your account is created!")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .show();
            }

            @Override
            public void onFail() {
                hideProgressDialog();
                new AlertDialog.Builder(SignUpActivity.this)
                        .setTitle("Failed")
                        .setMessage("Invalid email or password!")
                        .setNeutralButton("Ok", null)
                        .show();
            }
        });
    }

    public void signUp(View v){
        User user = new User(
                emailEditText.getText().toString()
                , passwordEditText.getText().toString());

        if (user.getEmail().equals("")) //The email is empty!
            Toast.makeText(this, "The e-mail text is empty!", Toast.LENGTH_SHORT).show();

        else if (!user.getEmail().matches(Regex.mailRegex)) //The email doesn't match the email regex!
            Toast.makeText(this, "This e-mail is invalid!", Toast.LENGTH_SHORT).show();

        else if (user.getPassword().equals("")) //The password is empty!
            Toast.makeText(this, "The password text is empty!", Toast.LENGTH_SHORT).show();

        else if (user.getPassword().length() < 4 || user.getPassword().length() > 12) // The password is too short or too long
            Toast.makeText(this, "The password must be between 4 and 12 characters!", Toast.LENGTH_SHORT).show();

        else { //All in order!
            showProgressDialog();
            manager.signUp(user);
        }

    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
