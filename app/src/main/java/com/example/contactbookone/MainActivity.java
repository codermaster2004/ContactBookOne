package com.example.contactbookone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnphone;

    RecyclerView recyclerView;

    ArrayList<UserData> userDatalist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnphone = findViewById(R.id.btnphone);
        recyclerView = findViewById(R.id.recyclerView);

        getAllData();

        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ContactSubmitActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    void getAllData()
    {
        Datahelper datahelper = new Datahelper(MainActivity.this);

         Cursor cursor = datahelper.getMyData();

         while (cursor.moveToNext())
         {
            int userid = cursor.getInt(0);
            String name = cursor.getString(1);
            String contact = cursor.getString(2);

           UserData userData = new UserData(userid,name,contact);

           userDatalist.add(userData);

         }


//         userDatalist.sort(Comparator.comparing(UserData::getName));
//
//        Collections.sort(userDatalist, Collections.reverseOrder());

        Collections.sort(userDatalist, new Comparator<UserData>() {
            @Override
            public int compare(UserData o1, UserData o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });



        ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this,userDatalist);
         recyclerView.setAdapter(contactAdapter);

    }
}