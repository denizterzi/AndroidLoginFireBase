package com.app.diceroid.nerede.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.diceroid.nerede.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonReg;
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Button deneme;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(),KidsActivity.class));
        }

        deneme = (Button) findViewById(R.id.deneme_button);
        progressDialog = new ProgressDialog(this);
        editTextEmail = (EditText) findViewById(R.id.email_address);
        editTextPassword = (EditText) findViewById(R.id.password_text);
        buttonLogin = (Button) findViewById(R.id.login_now);
        buttonReg = (Button) findViewById(R.id.register_now);

        buttonReg.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        deneme.setOnClickListener(this);

    }

    public  void onClickBtnLogin(View view){

        //internet control
        //olumsuz toast
        // dolu bos
        // login oldu mu
        // olumlu olumsuz
        // intent ac
        showKidsActivity();
        // mesaj goster

    }

    private void showKidsActivity(){



            String email = editTextEmail.getText().toString().trim();
            String password  = editTextPassword.getText().toString().trim();


            //checking if email and passwords are empty
            if(TextUtils.isEmpty(email)){
                Toast.makeText(this,"Lütfen Email Giriniz",Toast.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(this,"Lütfen Şifrenizi Giriniz",Toast.LENGTH_LONG).show();
                return;
            }



            progressDialog.setMessage("Giriş Yapılıyor Lütfen Bekleyin...");
            progressDialog.show();


            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if(task.isSuccessful()){

                                finish();
                                startActivity(new Intent(getApplicationContext(), KidsActivity.class));
                            }else{
                                loginError();
                            }


                        }
                    });
    }


    private void showRegActivity(){
        startActivity(new Intent(getApplicationContext(),RegActivity.class));
    }

    private void showLocationActivity(){
        startActivity(new Intent(getApplicationContext(),LocationActivity.class));
//        Intent intent = new Intent(LoginActivity.this,LocationActivity.class);
    }

    private void loginError(){
        Toast.makeText(this,"Şifre yada Emailiniz Yanlış",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {

        if(view == deneme){
            showLocationActivity();
        }

        if(view == buttonReg){
            showRegActivity();
        }

        if(view == buttonLogin){
            showKidsActivity();
        }
    }
}
