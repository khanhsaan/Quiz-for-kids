package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AnimalActivity extends AppCompatActivity {

    ListView animal_lv;
    Button back, next_btn;
    DatabaseHelper databaseHelper;
    ArrayAdapter userArrayAdapter;
    EditText dog, elephant, fish, fox;
    static List<String> animalList, animalListLowerCase;
    public static int numCorrect, numIncorrect, score;
    static boolean check;
    static HashMap<String, String> animalMap;
    List<String> attemptList;
    DrawerLayout drawerLayout;
    ImageView menu, animal_img_1, animal_img_2, animal_img_3, animal_img_4;
    ConstraintLayout animal, cartoon, log_off, home, attempts_lo;

    public AnimalActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal);

        AlertDialog.Builder builder = new AlertDialog.Builder(AnimalActivity.this);
        builder.setTitle("Quiz instruction").setMessage(String.format("Enter the names of each kind of animal below each picture.")).setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

//        animal_lv = findViewById(R.id.animal_lv);
        back = findViewById(R.id.animal_back_btn);
        databaseHelper = new DatabaseHelper(AnimalActivity.this);

        // when the user clicks back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalActivity.this, MainPage.class);
                startActivity(intent);
            }
        });

        dog = findViewById(R.id.animal_et_1);
        elephant = findViewById(R.id.animal_et_2);
        fish = findViewById(R.id.animal_et_3);
        fox = findViewById(R.id.animal_et_4);

        animalList = databaseHelper.getAnimal();
        animalListLowerCase = databaseHelper.getAnimaLowerCase();

        // fill the map
        animalMap = new HashMap<>();
        for(int i = 0; i < animalList.size(); i ++){
            animalMap.put(animalList.get(i), animalListLowerCase.get(i));
        }

        // shuffle the map
        List<String> keys = new ArrayList<>(animalMap.keySet());
        Collections.shuffle(keys);

        animal_img_1 = findViewById(R.id.animal_img_1);
        if(keys.get(0).equals("Dog")){
            animal_img_1.setImageResource(R.drawable.dog);
        }
        if(keys.get(0).equals("Elephant")){
            animal_img_1.setImageResource(R.drawable.elephant);
        }
        if(keys.get(0).equals("Fish")){
            animal_img_1.setImageResource(R.drawable.fish);
        }
        if(keys.get(0).equals("Fox")){
            animal_img_1.setImageResource(R.drawable.fox);
        }
        if(keys.get(0).equals("Kangaroo")){
            animal_img_1.setImageResource(R.drawable.kangaroo);
        }
        if(keys.get(0).equals("Lion")){
            animal_img_1.setImageResource(R.drawable.lion);
        }
        if(keys.get(0).equals("Bird")){
            animal_img_1.setImageResource(R.drawable.magpie);
        }
        if(keys.get(0).equals("Mouse")){
            animal_img_1.setImageResource(R.drawable.mouse);
        }
        if(keys.get(0).equals("Sloth")){
            animal_img_1.setImageResource(R.drawable.sloth);
        }
        if(keys.get(0).equals("Koala")){
            animal_img_1.setImageResource(R.drawable.koala);
        }

        animal_img_2 = findViewById(R.id.animal_img_2);
        if(keys.get(1).equals("Dog")){
            animal_img_2.setImageResource(R.drawable.dog);
        }
        if(keys.get(1).equals("Elephant")){
            animal_img_2.setImageResource(R.drawable.elephant);
        }
        if(keys.get(1).equals("Fish")){
            animal_img_2.setImageResource(R.drawable.fish);
        }
        if(keys.get(1).equals("Fox")){
            animal_img_2.setImageResource(R.drawable.fox);
        }
        if(keys.get(1).equals("Kangaroo")){
            animal_img_2.setImageResource(R.drawable.kangaroo);
        }
        if(keys.get(1).equals("Lion")){
            animal_img_2.setImageResource(R.drawable.lion);
        }
        if(keys.get(1).equals("Bird")){
            animal_img_2.setImageResource(R.drawable.magpie);
        }
        if(keys.get(1).equals("Mouse")){
            animal_img_2.setImageResource(R.drawable.mouse);
        }
        if(keys.get(1).equals("Sloth")){
            animal_img_2.setImageResource(R.drawable.sloth);
        }
        if(keys.get(1).equals("Koala")){
            animal_img_2.setImageResource(R.drawable.koala);
        }

        animal_img_3 = findViewById(R.id.animal_img_3);
        if(keys.get(2).equals("Dog")){
            animal_img_3.setImageResource(R.drawable.dog);
        }
        if(keys.get(2).equals("Elephant")){
            animal_img_3.setImageResource(R.drawable.elephant);
        }
        if(keys.get(2).equals("Fish")){
            animal_img_3.setImageResource(R.drawable.fish);
        }
        if(keys.get(2).equals("Fox")){
            animal_img_3.setImageResource(R.drawable.fox);
        }
        if(keys.get(2).equals("Kangaroo")){
            animal_img_3.setImageResource(R.drawable.kangaroo);
        }
        if(keys.get(2).equals("Lion")){
            animal_img_3.setImageResource(R.drawable.lion);
        }
        if(keys.get(2).equals("Bird")){
            animal_img_3.setImageResource(R.drawable.magpie);
        }
        if(keys.get(2).equals("Mouse")){
            animal_img_3.setImageResource(R.drawable.mouse);
        }
        if(keys.get(2).equals("Sloth")){
            animal_img_3.setImageResource(R.drawable.sloth);
        }
        if(keys.get(2).equals("Koala")){
            animal_img_3.setImageResource(R.drawable.koala);
        }

        animal_img_4 = findViewById(R.id.animal_img_4);
        if(keys.get(3).equals("Dog")){
            animal_img_4.setImageResource(R.drawable.dog);
        }
        if(keys.get(3).equals("Elephant")){
            animal_img_4.setImageResource(R.drawable.elephant);
        }
        if(keys.get(3).equals("Fish")){
            animal_img_4.setImageResource(R.drawable.fish);
        }
        if(keys.get(3).equals("Fox")){
            animal_img_4.setImageResource(R.drawable.fox);
        }
        if(keys.get(3).equals("Kangaroo")){
            animal_img_4.setImageResource(R.drawable.kangaroo);
        }
        if(keys.get(3).equals("Lion")){
            animal_img_4.setImageResource(R.drawable.lion);
        }
        if(keys.get(3).equals("Bird")){
            animal_img_4.setImageResource(R.drawable.magpie);
        }
        if(keys.get(3).equals("Mouse")){
            animal_img_4.setImageResource(R.drawable.mouse);
        }
        if(keys.get(3).equals("Sloth")){
            animal_img_4.setImageResource(R.drawable.sloth);
        }
        if(keys.get(3).equals("Koala")){
            animal_img_4.setImageResource(R.drawable.koala);
        }

        numCorrect = 0;
        check = false;
        // when the user clicks next button
        next_btn = findViewById(R.id.animal_next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = true;
                // numCorrect start counting if only if the user clicks next button
                if(dog.getText().toString().equals(keys.get(0)) || dog.getText().toString().equals(animalMap.get(keys.get(0)))){
                    numCorrect++;
                }
                if(elephant.getText().toString().equals(keys.get(1)) || elephant.getText().toString().equals(animalMap.get(keys.get(1)))){
                    numCorrect++;
                }
                if(fish.getText().toString().equals(keys.get(2)) || fish.getText().toString().equals(animalMap.get(keys.get(2)))){
                    numCorrect++;
                }
                if(fox.getText().toString().equals(keys.get(3)) || fox.getText().toString().equals(animalMap.get(keys.get(3)))){
                    numCorrect++;
                }
                Toast.makeText(AnimalActivity.this, String.format("You have answered %d question(s) correctly", numCorrect), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AnimalActivity.this, AnimalResultActivity.class);
                startActivity(intent);
            }
        });

        menu = findViewById(R.id.menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        animal = findViewById(R.id.animal_lo);
        cartoon = findViewById(R.id.cartoons_lo);
        home = findViewById(R.id.home_lo);
        log_off = findViewById(R.id.log_off_lo);

        // when the user click the menu icon, the menu will open
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        // when the user click home, they will be directed to the main page
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AnimalActivity.this, MainPage.class);
            }
        });

        // when the user click animal, they will be directed to the Animal Activity
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AnimalActivity.this, AnimalActivity.class);
            }
        });

        // when the user clicks cartoon, they will be directed to the Cartoon Activity
        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AnimalActivity.this, CartoonActivity.class);
            }
        });

        // when the customer click log off button
        MainActivity mainActivity = new MainActivity();
        String username = mainActivity.getThatUsername();
        log_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AnimalActivity.this);
                builder.setTitle("Overall score").setMessage(String.format("%s, you have overall %d points.", username, databaseHelper.getOverallScore(username))).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectActivity(AnimalActivity.this, MainActivity.class);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // when the customer click to see all attempts
        attemptList = new ArrayList<>();
        attempts_lo = findViewById(R.id.attempts_lo);
        attempts_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AnimalActivity.this, AllAttemptsActivity.class);
            }
        });
    }

    public boolean getCheck(){
        return check;
    }

    public void setCheck(boolean set){
        check = set;
    }

    public int getNumCorrect(){
        return numCorrect;
    }

    public int getNumIncorrect(){
        return numIncorrect;
    }

    public int getScore(){
        return score;
    }

    public List<String> getAnimalList(){
        return animalList;
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}