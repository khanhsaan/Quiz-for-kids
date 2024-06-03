package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class CartoonResultActivity extends AppCompatActivity {

    Button tryAgain_btn, home_btn;
    TextView username_tv, point_tv, overallPoint_tv;
    DrawerLayout drawerLayout;
    ImageView menu;
    ConstraintLayout animal, cartoon, log_off, home, attempts_lo;
    public String thatUsername;
    static int numCorrect, numIncorrect, score, numTotal, overall_score;
    static List<Integer> overallScoreList;
    DatabaseHelper databaseHelper;
    static int animalPoint, cartoonPoint, overallScore;
    static List<String> attemptList;



    public CartoonResultActivity() {
    }

    public int getNumCorrect(){
        return numCorrect;
    }

    public int getOverall_score(){
        return overall_score;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal_result);

        tryAgain_btn = findViewById(R.id.animal_back_btn);
        home_btn = findViewById(R.id.animal_next_btn);
        databaseHelper = new DatabaseHelper(CartoonResultActivity.this);


        MainActivity mainActivity = new MainActivity();

        username_tv = findViewById(R.id.animalResult_username_tv);
        // get the username
        String username = mainActivity.getThatUsername();
        username_tv.setText(String.format("Well done %s", mainActivity.getThatUsername()));

        CartoonActivity4 cartoonActivity =  new CartoonActivity4();
        numCorrect = cartoonActivity.getNumCorrect();
        // get the number of incorrect answer
        numIncorrect = 4 - numCorrect;


        // calculate the point
        score = numCorrect * 3 - numIncorrect * 1;
        if(score < 0){
            score = 0;
        }

        // store the score of this attempt to the list
        overallScoreList = mainActivity.getOverallScoreList();
        overallScoreList.add(score);


        // calculate the sum of all the scores from all of the attempts
        for(int i = 0; i < overallScoreList.size(); i++){
            overall_score += overallScoreList.get(i);
        }

        point_tv = findViewById(R.id.animalResult_point_tv);


        // update database with the new overall score
        databaseHelper.updateOverallScore(username, databaseHelper.getOverallScore(username));

        // when the user clicks home button
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartoonResultActivity.this, MainPage.class);
                startActivity(intent);
            }
        });

        // when the user clicks try again btn
        tryAgain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartoonResultActivity.this, CartoonActivity.class);
                startActivity(intent);
            }
        });

        // add to the attemptList
        CartoonResultActivity cartoonResultActivity = new CartoonResultActivity();
        CartoonActivity4 cartoonActivity4 = new CartoonActivity4();
        cartoonPoint = cartoonResultActivity.getScore();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());
        String cartoonAttemptString = String.format("“Cartoon” area - attempt started on %s – points earned %d", currentDateAndTime, cartoonPoint);

        MainPage mainPage = new MainPage();
        attemptList = mainPage.getAttemptList();
        if(cartoonActivity4.getCheck()){
            attemptList.add(cartoonAttemptString);
            // add attempt to the database
            boolean add = databaseHelper.addAttempt(mainActivity.getThatUsername(), cartoonAttemptString, cartoonResultActivity.getScore());
            cartoonActivity4.setCheck(false);
        }

        // display the result to the user
        point_tv.setText(String.format("with %d correct and %d incorrect answers or %d points for this attempt", numCorrect, numIncorrect, score));

        overallPoint_tv = findViewById(R.id.animalResult_overallPoint_tv);
        overallPoint_tv.setText(String.format("Overall you have %d points", databaseHelper.getOverallScore(username)));

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
                redirectActivity(CartoonResultActivity.this, MainPage.class);
            }
        });

        // when the user click animal, they will be directed to the Animal Activity
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(CartoonResultActivity.this, AnimalActivity.class);
            }
        });

        // when the user clicks cartoon, they will be directed to the Cartoon Activity
        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(CartoonResultActivity.this, CartoonActivity.class);
            }
        });

        // when the customer click log off button
        log_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartoonResultActivity.this);
                builder.setTitle("Overall score").setMessage(String.format("%s, you have overall %d points.", username, databaseHelper.getOverallScore(username))).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectActivity(CartoonResultActivity.this, MainActivity.class);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // when the customer click to see all attempts
        attempts_lo = findViewById(R.id.attempts_lo);
        attemptList = new ArrayList<>();
        attempts_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(CartoonResultActivity.this, AllAttemptsActivity.class);
            }
        });
    }

    public int getScore(){
        return score;
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