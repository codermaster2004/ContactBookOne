package com.example.contactbookone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Datahelper extends SQLiteOpenHelper {

    public Datahelper(@Nullable Context context) {
        super(context, "contactdatabase",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry = "create table MyContacts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, contact TEXT)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Mybook(String myname, String mycontact ) {

        String query = "Insert into MyContacts (name,contact) values('"+myname+"','"+mycontact+"')";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(query);

    }

    public Cursor getMyData() {

        String qry = "select * from MyContacts";

//        String searchname = "ABC";
//        String a = "select * from MyContacts where name = '"+searchname+"'";
//
//        int userid = 10;
//        String b = "select * from MyContacts where id = '"+userid+"'";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(qry,null);

        return cursor;
    }

    public void deleteMyContact(int userid) {
        String qry = "delete from MyContacts where id='"+userid+"'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(qry);
    }

    public void updateCOntact(int userid, String newname, String newcontact) {

        String qry = "update MyContacts set name = '"+newname+"', contact = '"+newcontact+"' where id = '"+userid+"'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(qry);
    }
}
