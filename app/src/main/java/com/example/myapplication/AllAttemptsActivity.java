package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AllAttemptsActivity extends AppCompatActivity {
    ArrayAdapter attemptsArrayAdapter;
    ListView attempts_lv;
    TextView attempts_tv;
    static List<String> attemptList;
    static int overallScore;
    DrawerLayout drawerLayout;
    ImageView menu;
    ConstraintLayout animal, cartoon, log_off, home, attempts_lo;
    DatabaseHelper databaseHelper;
    Button sort_cartoon_btn, sort_animal_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_attempts);



        attempts_lv = findViewById(R.id.allAttempts_lv);
        MainPage mainPage = new MainPage();
        databaseHelper = new DatabaseHelper(AllAttemptsActivity.this);

        attemptList = mainPage.getAttemptList();

        MainActivity mainActivity = new MainActivity();
        String username = mainActivity.getThatUsername();

        overallScore = databaseHelper.getOverallScore(mainActivity.getThatUsername());
        List<Integer> overallScore_list = mainActivity.getOverallScoreList();
        for(int i = 0; i < overallScore_list.size(); i ++){
            overallScore += overallScore_list.get(i);
        }

        attempts_tv = findViewById(R.id.allAttempts_tv);
        attempts_tv.setText(String.format("Hi %s, you have earned %d points in the following attempts", mainActivity.getThatUsername(), databaseHelper.getOverallScore(mainActivity.getThatUsername())));

        showAttemptsOnListView(databaseHelper.getAttempts(mainActivity.getThatUsername()));

        // update database with the new overall score
        databaseHelper = new DatabaseHelper(AllAttemptsActivity.this);
        databaseHelper.updateOverallScore(username, databaseHelper.getOverallScore(username));

        menu = findViewById(R.id.menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        animal = findViewById(R.id.animal_lo);
        cartoon = findViewById(R.id.cartoons_lo);
        home = findViewById(R.id.home_lo);
        log_off = findViewById(R.id.log_off_lo);
        attempts_lo = findViewById(R.id.attempts_lo);

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
                redirectActivity(AllAttemptsActivity.this, MainPage.class);
            }
        });

        // when the user click animal, they will be directed to the Animal Activity
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AllAttemptsActivity.this, AnimalActivity.class);
            }
        });

        // when the user clicks cartoon, they will be directed to the Cartoon Activity
        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(AllAttemptsActivity.this, CartoonActivity.class);
            }
        });

        // when the customer click log off button
        log_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AllAttemptsActivity.this);
                builder.setTitle("Overall score").setMessage(String.format("%s, you have overall %d points.", username, databaseHelper.getOverallScore(username))).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectActivity(AllAttemptsActivity.this, MainActivity.class);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // when the user click to sort the cartoon area
        sort_cartoon_btn = findViewById(R.id.allAttempts_sorting_cartoon_btn);
        sort_cartoon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> cartoonAreaList = new ArrayList<>();
                for(String attempt: attemptList){
                    if(attempt.contains("“Cartoon” area")){
                        cartoonAreaList.add(attempt);
                    }
                }
                showAttemptsOnListView(cartoonAreaList);
            }
        });

        sort_animal_btn = findViewById(R.id.allAttempts_sorting_animal_btn);
        sort_animal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> animalAreaList = new ArrayList<>();
                for(String attempt: attemptList){
                    if(attempt.contains("“Animals” area")){
                        animalAreaList.add(attempt);
                    }
                }
                showAttemptsOnListView(animalAreaList);
            }
        });

        // when the user click to see all attempts
        attempts_lo = findViewById(R.id.attempts_lo);
        attempts_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer(drawerLayout);
            }
        });

    }

    private void showAttemptsOnListView(List<String> list) {
        attemptsArrayAdapter = new ArrayAdapter<>(AllAttemptsActivity.this, android.R.layout.simple_list_item_1, list);
        attempts_lv.setAdapter(attemptsArrayAdapter);
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