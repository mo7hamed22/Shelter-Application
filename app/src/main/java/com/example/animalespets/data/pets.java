package com.example.animalespets.data;

import android.provider.BaseColumns;

public final  class pets {

    public final  class pets_entry implements BaseColumns {

        public static final String Table_Name = "shelter";

        public static final String _ID ="id";
        public static final String COLUMN_PET_NAME ="Name";
        public static final String COLUMN_PET_BREED ="Breed";
        public static final String COLUMN_PET_GENDER ="Gender";
        public static final String COLUMN_PET_WEIGHT ="weight";


        public  static  final int GENDER_UNKNOWN=0;
        public  static  final int GENDER_MALE=1;
        public  static  final int GENDER_FEMALE=2;




    }
}
