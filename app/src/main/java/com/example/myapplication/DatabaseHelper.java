package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String CARTOON_COLUMN_ID = "ID";
    public static final String ANIMAL_COLUMN_ID = CARTOON_COLUMN_ID;
    public static final String COLUMN_ID = ANIMAL_COLUMN_ID;
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String COLUMN_ATTEMPT = "ATTEMPT";
    public static final String ATTEMPT_TABLE = COLUMN_ATTEMPT + "_TABLE";
    public static final String COLUMN_ATTEMPT_SCORE = "ATTEMPT_SCORE";
    public static String COLUMN_CUSTOMER_USERNAME = "CUSTOMER_USERNAME";
    public static final String ANIMAL_TABLE = "ANIMAL_TABLE";
    public static final String ANIMAL_COLUMN_NAME = "ANIMAL_NAME";
    public static final String CARTOON_TABLE = "CARTOON_TABLE";
    public static final String CARTOON_NAME_COLUMN = "CARTOON_NAME";
    public static final String ANIMAL_ANSWER_SMALLCASE_COLUMN = "ANIMAL_SMALLCASE";
    public static final String COLUMN_OVERALL_SCORE = "OVERALL_SCORE";
    static String username;
    List<UserModel> returnList;
    static List<String> animalList, cartoonList, attemptList;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUser = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_USERNAME +" TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT, " + COLUMN_OVERALL_SCORE + " INTEGER)";

        String createTableAnimal = "CREATE TABLE " + ANIMAL_TABLE + " (" + ANIMAL_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANIMAL_COLUMN_NAME + " TEXT, " + ANIMAL_ANSWER_SMALLCASE_COLUMN + " TEXT)";

        String createTableCartoon = "CREATE TABLE " + CARTOON_TABLE + " (" + CARTOON_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CARTOON_NAME_COLUMN + " TEXT)";

        String createTableAttempt = "CREATE TABLE " + ATTEMPT_TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_USERNAME + " TEXT, " + COLUMN_ATTEMPT + " TEXT, " + COLUMN_ATTEMPT_SCORE + " INTEGER)";

        db.execSQL(createTableUser);
        db.execSQL(createTableAnimal);
        db.execSQL(createTableCartoon);
        db.execSQL(createTableAttempt);

        // Add animals to the database
        ContentValues animal_cv = new ContentValues();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Dog");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "dog");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Elephant");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "elephant");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Fish");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "fish");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Fox");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "fox");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Kangaroo");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "kangaroo");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Koala");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "koala");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Lion");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "lion");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Bird");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "bird");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Mouse");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "mouse");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();
        animal_cv.put(ANIMAL_COLUMN_NAME, "Sloth");
        animal_cv.put(ANIMAL_ANSWER_SMALLCASE_COLUMN, "sloth");
        db.insert(ANIMAL_TABLE, null, animal_cv);
        animal_cv.clear();

        // Add cartoons' characters to the database
        ContentValues cartoon_cv = new ContentValues();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Bard");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Bugs");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Dora");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Jerry");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Spongebob");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Tom");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Micky");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Scooby");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Shrek");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
        cartoon_cv.put(CARTOON_NAME_COLUMN, "Pikachu");
        db.insert(CARTOON_TABLE, null, cartoon_cv);
        cartoon_cv.clear();
//        addAnimal();

        // put all the animals into a list from the database;
//        getAnimal();

        MainActivity mainActivity = new MainActivity();
        username = mainActivity.getThatUsername();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_EMAIL, userModel.getEmail());
        cv.put(COLUMN_CUSTOMER_USERNAME, userModel.getUsername());
        cv.put(COLUMN_CUSTOMER_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);

//        cv2.put(COLUMN_CUSTOMER_USERNAME, userModel.getUsername());
//        db.insert(ATTEMPT_TABLE, null, cv2);

        // if insert =! 1, insert user successfully
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addAttempt(String username, String s, int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_USERNAME, username);
        cv.put(COLUMN_ATTEMPT, s);
        cv.put(COLUMN_ATTEMPT_SCORE, score);
        long insert = db.insert(ATTEMPT_TABLE, null, cv);

        if(insert == -1){
            return false;
        } else {
            return true;
        }

    }

    public void updateOverallScore(String username, int overallScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_OVERALL_SCORE, overallScore);

        String selection = COLUMN_CUSTOMER_USERNAME + " = ?";
        String[] selectionArgs = { username };

        db.update(USER_TABLE, values, selection, selectionArgs);
    }


    public List<UserModel> getEveryone(){
        returnList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects. Put them into the list
            do{
                int id = cursor.getInt(0);
                String email = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);

                UserModel newCustomer = new UserModel(id, email, username, password);
                returnList.add(newCustomer);

            } while (cursor.moveToNext());
        }
        else {
            // failure. Do not add anything to the list.
        }
        cursor.close();
        db.close();
        return returnList;
    }


    public List<String> getAnimal(){
        animalList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT * FROM " + ANIMAL_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{
                String animalName = cursor.getString(1);
                animalList.add(animalName);
            }
            while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return animalList;
    }

    MainActivity mainActivity = new MainActivity();
    MainPage mainPage = new MainPage();
    public List<String> getAttempts(String username) {
        List<String> attemptList = mainPage.getAttemptList();
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT * FROM " + ATTEMPT_TABLE + " WHERE " + COLUMN_CUSTOMER_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String attempt = cursor.getString(2);
                    attemptList.add(attempt);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return attemptList;
    }

    public int getOverallScore(String username){
        int overallScore = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " + COLUMN_ATTEMPT_SCORE + " FROM " + ATTEMPT_TABLE + " WHERE " + COLUMN_CUSTOMER_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(queryString, new String[]{username});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    overallScore += cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return overallScore;
    }


    public List<String> getCartoons(){
        cartoonList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT * FROM " + CARTOON_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{
                String cartoonName = cursor.getString(1);
                cartoonList.add(cartoonName);
            }
            while(cursor.moveToNext());
        } else {}
        cursor.close();
        db.close();
        return cartoonList;
    }

    public List<String> getAnimaLowerCase(){
        animalList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT * FROM " + ANIMAL_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{
                String animalName = cursor.getString(2);
                animalList.add(animalName);
            }
            while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return animalList;
    }

    public void clearTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "DELETE FROM " + USER_TABLE;
        db.execSQL(queryString);
    }
}

