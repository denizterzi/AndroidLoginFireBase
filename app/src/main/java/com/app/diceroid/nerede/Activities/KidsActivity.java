package com.app.diceroid.nerede.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.app.diceroid.nerede.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class KidsActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button buttonLogout;
    private Button buttonKidsAdd;
    private DatabaseReference databaseReference;



    private String[] myKids =
            {"Alber", "Eşref", "Ozan", "Ogün", "Feyyaz", "Esa", "Aykut", "Batu", "Alex", "Guard", "Read", "Gladi", "Run Or Dead"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids);

        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonKidsAdd = (Button) findViewById(R.id.buttonKidsAdd);

        buttonKidsAdd.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);


        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> dataAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, myKids);
        listView.setAdapter(dataAdaptor);
    }



    private void kidsAdd() {
        Intent intent = new Intent(this, KidsAddActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {


        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        if(view == buttonKidsAdd){
            kidsAdd();
        }

    }
}
