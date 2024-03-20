package com.example.contactbookone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactSubmitActivity extends AppCompatActivity {

    EditText etname;
    EditText etcontact;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_submit);

        etname = findViewById(R.id.etname);
        etcontact = findViewById(R.id.etcontact);
        btnsave = findViewById(R.id.btnsave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etname.getText().toString();
                String contact = etcontact.getText().toString();

                Datahelper datahelper = new Datahelper(ContactSubmitActivity.this);
                datahelper.Mybook(name,contact);

                startActivity(new Intent(ContactSubmitActivity.this,MainActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(ContactSubmitActivity.this,MainActivity.class));
        finish();
    }
}