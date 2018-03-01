package com.app.diceroid.nerede.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.diceroid.nerede.R;
import com.app.diceroid.nerede.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KidsAddActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private RadioButton one_hours;
    private RadioButton third_minutes;
    private RadioButton two_hours;
    private RadioButton eight_hours;
    private Button kidsAddButon;
    private EditText child_name;
    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_add);



        firebaseAuth = FirebaseAuth.getInstance();
        one_hours = (RadioButton) findViewById(R.id.one_hours);
        third_minutes = (RadioButton) findViewById(R.id.third_minutes);
        two_hours = (RadioButton) findViewById(R.id.two_hours);
        eight_hours = (RadioButton) findViewById(R.id.eight_hours);
        kidsAddButon = (Button) findViewById(R.id.kidsAddButon);
        child_name = (EditText) findViewById(R.id.child_name);
        name = (TextView) findViewById(R.id.name);




        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();





        kidsAddButon.setOnClickListener(this);

    }

    private void kidsAdd(){
        String name = child_name.getText().toString().trim();
        UserInformation userInformation = new UserInformation(name);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Kayıt Başarılı",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {

      if(view == kidsAddButon){
          kidsAdd();
      }
    }
}
