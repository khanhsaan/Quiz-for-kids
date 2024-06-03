package com.example.myapplication;

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
import java.util.Collections;
import java.util.List;

public class CartoonActivity extends AppCompatActivity {
    public static List<String> cartoonList;
    DatabaseHelper databaseHelper;
    ImageView img1;
    DrawerLayout drawerLayout;
    ImageView menu;
    ConstraintLayout animal, cartoon, log_off, home, attempts_lo;
    Button next_btn, back_btn, choice1, choice2, choice3;
    public static int numCorrect;
    List<String> attemptList;

    public CartoonActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cartoon);

        AlertDialog.Builder builder = new AlertDialog.Builder(CartoonActivity.this);
        builder.setTitle("Quiz instruction").setMessage(String.format("Select the correct name of each the following cartoon character below each picture.")).setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        databaseHelper = new DatabaseHelper(CartoonActivity.this);

        // get the list of cartoons' characters from the database
        cartoonList = databaseHelper.getCartoons();

        // shuffle the list
        Collections.shuffle(cartoonList);

        img1 = findViewById(R.id.cartoon_img_1);

        if(cartoonList.get(0).equals("Bard")){
            img1.setImageResource(R.drawable.bard);
        }
        if(cartoonList.get(0).equals("Bugs")){
            img1.setImageResource(R.drawable.bugs);
        }
        if(cartoonList.get(0).equals("Dora")){
            img1.setImageResource(R.drawable.dora);
        }
        if(cartoonList.get(0).equals("Jerry")){
            img1.setImageResource(R.drawable.jerry);
        }
        if(cartoonList.get(0).equals("Spongebob")){
            img1.setImageResource(R.drawable.spongebob);
        }
        if(cartoonList.get(0).equals("Tom")){
            img1.setImageResource(R.drawable.tom);
        }
        if(cartoonList.get(0).equals("Micky")){
            img1.setImageResource(R.drawable.micky);
        }
        if(cartoonList.get(0).equals("Scooby")){
            img1.setImageResource(R.drawable.scooby);
        }
        if(cartoonList.get(0).equals("Shrek")){
            img1.setImageResource(R.drawable.shrek);
        }
        if(cartoonList.get(0).equals("Pikachu")){
            img1.setImageResource(R.drawable.pokemon);
        }

        // Get the first 3 answers
        List<String> firstThreeAnswers = new ArrayList<>();
        for(int i = 0; i < 3; i ++){
            firstThreeAnswers.add(cartoonList.get(i));
        }

        Collections.shuffle(firstThreeAnswers);

        choice1 = findViewById(R.id.cartoon_choice_1);
        String choice1_text = firstThreeAnswers.get(0);
        choice1.setText(choice1_text);

        choice2 = findViewById(R.id.cartoon_choice_2);
        String choice2_text = firstThreeAnswers.get(1);
        choice2.setText(choice2_text);

        choice3 = findViewById(R.id.cartoon_choice_3);
        String choice3_text = firstThreeAnswers.get(2);
        choice3.setText(choice3_text);

        numCorrect = 0;

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choice1_text.equals(cartoonList.get(0))){
                    // increment the numCorrect
                    numCorrect++;
                    // notify the user that they're correct and switch to the next activity
                    Toast.makeText(CartoonActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartoonActivity.this, CartoonActivity2.class);
                    startActivity(intent);
                } else {
                    // take the choice out of the list
                    cartoonList.remove(0);
                    // notify the user that they're icorrect and switch to the next activity
                    Toast.makeText(CartoonActivity.this, "Incorrect.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartoonActivity.this, CartoonActivity2.class);
                    startActivity(intent);
                }
                // take the choice out of the list
                cartoonList.remove(0);
            }
        });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choice2_text.equals(cartoonList.get(0))){
                    // increment the numCorrect
                    numCorrect++;
                    // notify the user that they're correct and switch to the next activity
                    Toast.makeText(CartoonActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartoonActivity.this, CartoonActivity2.class);
                    startActivity(intent);
                } else {
                    // take the choice out of the list
                    cartoonList.remove(0);
                    // notify the user that they're icorrect and switch to the next activity
                    Toast.makeText(CartoonActivity.this, "Incorrect.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartoonActivity.this, CartoonActivity2.class);
                    startActivity(intent);
                }
                // take the choice out of the list
                cartoonList.remove(0);
            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choice3_text.equals(cartoonList.get(0))){
                    // increment the numCorrect
                    numCorrect++;
                    // notify the user that they're correct and switch to the next activity
                    Toast.makeText(CartoonActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartoonActivity.this, CartoonActivity2.class);
                    startActivity(intent);
                } else {
                    // take the choice out of the list
                    cartoonList.remove(0);
                    // notify the user that they're icorrect and switch to the next activity
                    Toast.makeText(CartoonActivity.this, "Incorrect.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CartoonActivity.this, CartoonActivity2.class);
                    startActivity(intent);
                }
                // take the choice out of the list
                cartoonList.remove(0);
            }
        });

        // when the user clicks the next button
        next_btn = findViewById(R.id.cartoon_next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartoonActivity.this, CartoonActivity2.class);
                startActivity(intent);
                // take the choice out of the list
                cartoonList.remove(0);
            }
        });

        // then the user clicks the back button
        back_btn = findViewById(R.id.cartoon_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartoonActivity.this, MainPage.class);
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
                redirectActivity(CartoonActivity.this, MainPage.class);
            }
        });

        // when the user click animal, they will be directed to the Animal Activity
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(CartoonActivity.this, AnimalActivity.class);
            }
        });

        // when the user clicks cartoon, they will be directed to the Cartoon Activity
        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(CartoonActivity.this, CartoonActivity.class);
            }
        });

        // when the customer click log off button
        MainActivity mainActivity = new MainActivity();
        String username = mainActivity.getThatUsername();
        log_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartoonActivity.this);
                builder.setTitle("Overall score").setMessage(String.format("%s, you have overall %d points.", username, databaseHelper.getOverallScore(username))).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectActivity(CartoonActivity.this, MainActivity.class);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // when the customer click to see all attempts
        attempts_lo = findViewById(R.id.attempts_lo);
        attempts_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempts_lo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        redirectActivity(CartoonActivity.this, AllAttemptsActivity.class);
                    }
                });
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

    public List<String> getCartoonList(){
        return cartoonList;
    }

    public int getNumCorrect(){
        return numCorrect;
    }
}