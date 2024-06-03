package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button create_btn, log_in_btn;
    EditText username;
    EditText password;
    DatabaseHelper databaseHelper;
    public static String thatUsername;
    static List<Integer> overallScoreList;

    public MainActivity() {
    }

    public List<Integer> getOverallScoreList(){
        return overallScoreList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(MainActivity.this);
        overallScoreList = new ArrayList<>();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        create_btn = findViewById(R.id.create_btn);
        log_in_btn = findViewById(R.id.login_btn);
        username = findViewById(R.id.username_et);
        password = findViewById(R.id.password_ed);
        thatUsername = "username";

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        List<UserModel> userList = databaseHelper.getEveryone();

        log_in_btn.setOnClickListener(new View.OnClickListener() {
            final List<UserModel> userList = databaseHelper.getEveryone();
            boolean check = false;
            @Override
            public void onClick(View v) {
                try{
                    for(int i = 0; i < userList.size(); i ++){
                        if(userList.get(i).getUsername().equals(username.getText().toString()) && userList.get(i).getPassword().equals(password.getText().toString())) {

                            // Assign the correct username to thatUsername
                            thatUsername = userList.get(i).getUsername();

                            Toast.makeText(MainActivity.this, String.format("%s login successfully", thatUsername), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MainPage.class);
                            startActivity(intent);

                            check = true;
                            break;
                        }
                        if(userList.get(i).getUsername().equals(username.getText().toString())) {
                            if (!userList.get(i).getPassword().equals(password.getText().toString())) {
                                Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }

                            check = true;
                            break;
                        }
                    }
                    if(!check){
                        Toast.makeText(MainActivity.this, "Account doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Some errors occured", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getThatUsername(){
        return this.thatUsername;
    }
}