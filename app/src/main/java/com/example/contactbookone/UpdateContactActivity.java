package com.example.contactbookone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContactActivity extends AppCompatActivity {

    EditText etname;
    EditText etcontact;
    Button btnsave;

    String oldname;
    String oldcontact;
    int userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        etname = findViewById(R.id.etname);
        etcontact = findViewById(R.id.etcontact);
        btnsave = findViewById(R.id.btnsave);

        userid = getIntent().getIntExtra("id",0);
        oldname = getIntent().getStringExtra("name");
        oldcontact = getIntent().getStringExtra("contact");

        etname.setText(oldname);
        etcontact.setText(oldcontact);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newname = etname.getText().toString();
                String newcontact = etcontact.getText().toString();

                Datahelper datahelper = new Datahelper(UpdateContactActivity.this);
                datahelper.updateCOntact(userid,newname,newcontact);

                startActivity(new Intent(UpdateContactActivity.this,MainActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(UpdateContactActivity.this,MainActivity.class));
        finish();
    }
}