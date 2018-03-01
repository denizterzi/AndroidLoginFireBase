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

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private  EditText editTextPasswordR;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //Tanımlamalar Yapılıyor


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.register_button);
        editTextEmail = (EditText) findViewById(R.id.email_address);
        editTextPassword = (EditText) findViewById(R.id.password_edit_text);
        editTextPasswordR = (EditText) findViewById(R.id.verify_password_edit_text);

        //Click Eventi Tanımlanıyor
        buttonRegister.setOnClickListener(this);

    }

    //Fonksiyon Tanımlanıyor
    private void registerUser(){

        //değişkenler tanımlandı

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordR = editTextPasswordR.getText().toString().trim();

        //Şifre ve mail boş/ dolu kontrolü yapılıyor

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"Lütfen Mail Adresinizi Giriniz !",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password) && TextUtils.isEmpty(passwordR)){
            Toast.makeText(this,"Lütfen Şifrenizi Giriniz !",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.equals(password,passwordR)){

        }else{
            Toast.makeText(this,"Girilen Şifreler Farklı,Tekrar Deneyin !",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Kayıt Olunuyor Lütfen Bekleyin");
        progressDialog.show();

        //Kullanıcı Kaydı Oluşturuluyor

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Eğer Kayıt İşlemi Başarılı Olursa
                        
                        if(task.isSuccessful()){

                            if(firebaseAuth.getCurrentUser() !=null){
                                finish();
                                startActivity(new Intent(getApplicationContext(),KidsActivity.class));
                            }

                            Toast.makeText(RegActivity.this, "Kayıt Başarılı.", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }else{
                            Toast.makeText(RegActivity.this, "Kayıt Başarısız ! Tekrar Deneyin.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if(view == buttonRegister){

            registerUser();
        }

    }
}
