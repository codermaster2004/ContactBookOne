package com.example.contactbookone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnphone;
    ImageView ivNoData;
    RecyclerView recyclerView;
    ArrayList<UserData> userDatalist = new ArrayList<>();

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnphone = findViewById(R.id.btnphone);
        recyclerView = findViewById(R.id.recyclerView);
        ivNoData = findViewById(R.id.ivNoData);
        searchView = findViewById(R.id.searchView);

        getAllData();

        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ContactSubmitActivity.class);
                startActivity(intent);
                finish();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                // sort the userdatalist
                // 10 data
                // ha or 98

                searchText = searchText.replace(" ","");
                if(searchText.trim().isEmpty())
                {
                    ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this,userDatalist);
                    recyclerView.setAdapter(contactAdapter);
                }
                else {
                    ArrayList<UserData> tempList = new ArrayList<>();
                    for (int i=0;i<userDatalist.size();i++)
                    {
                        String name = userDatalist.get(i).getName(); // Ajay, Sanjay, Haresh,Vijay
                        String contact = userDatalist.get(i).getContact();

                        if(name.toLowerCase().contains(searchText.trim().toLowerCase()) ||  contact.toLowerCase().contains(searchText.trim().toLowerCase()))
                        {
                            tempList.add(userDatalist.get(i));
                        }
                    }
                    ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this,tempList);
                    recyclerView.setAdapter(contactAdapter);
                }

                return false;
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

        if(userDatalist.size()>0){
            Collections.sort(userDatalist, new Comparator<UserData>() {
                @Override
                public int compare(UserData o1, UserData o2) {
                    return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                }
            });

            ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this,userDatalist);
            recyclerView.setAdapter(contactAdapter);

            recyclerView.setVisibility(View.VISIBLE);
            ivNoData.setVisibility(View.GONE);
        }
        else {

            recyclerView.setVisibility(View.GONE);
            ivNoData.setVisibility(View.VISIBLE);
        }


    }
}