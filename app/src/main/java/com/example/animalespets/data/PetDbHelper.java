package com.example.animalespets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;

import static com.example.animalespets.data.pets.PetEntry._ID;

public class PetDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = PetDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "shelter.db";

    private static final int DATABASE_VERSION = 1;
    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
    String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + pets.PetEntry.TABLE_NAME + " ("
            + pets.PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +  pets.PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
            +  pets.PetEntry.COLUMN_PET_BREED + " TEXT, "
            +  pets.PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
            +  pets.PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

    // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
}

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}