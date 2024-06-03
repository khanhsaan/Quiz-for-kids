package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {
    Button animal_btn, cartoon_btn, log_off_btn;
    DrawerLayout drawerLayout;
    ImageView menu;
    ConstraintLayout animal, cartoon, log_off, home, attempts_lo;
    public static String username;
    public static int overall_score;
    static List<String> attemptList;

    public MainPage() {
    }

    public List<String> getAttemptList(){
        return attemptList;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);

        menu = findViewById(R.id.menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        animal = findViewById(R.id.animal_lo);
        cartoon = findViewById(R.id.cartoons_lo);
        home = findViewById(R.id.home_lo);
        log_off = findViewById(R.id.log_off_lo);

        // when the user clicks log off button
        log_off_btn = findViewById(R.id.main_log_off_btn);
        log_off_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Log off!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
                redirectActivity(MainPage.this, MainPage.class);
            }
        });

        // when the user click animal, they will be directed to the Animal Activity
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainPage.this, AnimalActivity.class);
            }
        });

        // when the user clicks cartoon, they will be directed to the Cartoon Activity
        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainPage.this, CartoonActivity.class);
            }
        });

        // when the customer click log off button
        log_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this, "Log off!", Toast.LENGTH_SHORT).show();

                redirectActivity(MainPage.this, MainActivity.class);
            }
        });

        // when the customer click animal button
        animal_btn = findViewById(R.id.main_animals_btn);
        animal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, AnimalActivity.class);
                startActivity(intent);
            }
        });

        // when the customer click cartoon button
        cartoon_btn = findViewById(R.id.main_Cartoons_btn);
        cartoon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, CartoonActivity.class);
                startActivity(intent);
            }
        });

        // when the customer click to see all attempts
        AllAttemptsActivity allAttemptsActivity = new AllAttemptsActivity();
        attempts_lo = findViewById(R.id.attempts_lo);
        attemptList = new ArrayList<>();
        attempts_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainPage.this, AllAttemptsActivity.class);
            }
        });
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