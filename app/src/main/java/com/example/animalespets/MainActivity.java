package com.example.animalespets;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.animalespets.data.PetDbHelper;
import com.example.animalespets.data.pets;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private  static final int Pet_loader=0;
    PetCursorAdapter mPetCursorAdapter;

    private   long newRowId=0;
     private PetDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(i);
            }
        });
        // Find the ListView which will be populated with the pet data
        ListView petListView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);

        mPetCursorAdapter= new PetCursorAdapter(this,null);
        petListView.setAdapter(mPetCursorAdapter);
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
              Intent intent=new Intent(MainActivity.this,EditorActivity.class);
              Uri currentPeturi= ContentUris.withAppendedId(pets.PetEntry.CONTENT_URI,id);

                intent.setData(currentPeturi);
                startActivity(intent);
            }

        });

        //Kick off the Loader
        getSupportLoaderManager().initLoader(Pet_loader,null, this);
    }
    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(pets.PetEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }


    private void insertPet() {
        // Gets the database in write mode
/*        SQLiteDatabase db = mDbHelper.getWritableDatabase();*/

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(pets.PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(pets.PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(pets.PetEntry.COLUMN_PET_GENDER, pets.PetEntry.GENDER_MALE);
        values.put(pets.PetEntry.COLUMN_PET_WEIGHT, 7);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        Uri newuri = getContentResolver().insert(pets.PetEntry.CONTENT_URI, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                //displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        //selecte the specific column that we care About
      String [] Projection ={
              pets.PetEntry._ID,
              pets.PetEntry.COLUMN_PET_NAME,
              pets.PetEntry.COLUMN_PET_BREED
      };
      //this loader will execute ContentProvider's in background thread
      return new CursorLoader(this,
              pets.PetEntry.CONTENT_URI,
              Projection,
              null,null,null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            //update newCursor to update Containing New Data
        mPetCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        //CallBack when the data need to be Deleted
        mPetCursorAdapter.swapCursor(null);
    }
}