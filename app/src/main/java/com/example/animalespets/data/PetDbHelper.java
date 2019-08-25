package com.example.animalespets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;

import static com.example.animalespets.data.pets.pets_entry._ID;

public class PetDbHelper extends SQLiteOpenHelper {
    private static final int db_version =1;

    public static final String db_Name="petsdb";
    private static final String SQL_CREATE_ENTERIES = "CREATE TABLE "+ pets.pets_entry.Table_Name + "("
            + _ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + pets.pets_entry.COLUMN_PET_NAME +"text not null ,"
            + pets.pets_entry.COLUMN_PET_BREED +"TEXT  ,"
            + pets.pets_entry.COLUMN_PET_GENDER+"INTGER NOT NULL ,"
            + pets.pets_entry.COLUMN_PET_WEIGHT+"INTGER NOT NULL DEFAULT 0);" ;

    public PetDbHelper(Context context) {
        super(context, db_Name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTERIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_CREATE_ENTERIES);
        onUpgrade(db,oldVersion,newVersion);


    }
}
