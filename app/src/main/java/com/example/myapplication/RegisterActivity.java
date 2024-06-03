package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    Button back_btn, register_btn;
    EditText email_et, username_et, password_et;
    public DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        back_btn = findViewById(R.id.reg_back_btn);
        email_et = findViewById(R.id.reg_email_et);
        username_et = findViewById(R.id.reg_username_et);
        password_et = findViewById(R.id.reg_password_ed);
        register_btn = findViewById(R.id.reg_register_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // when user clicks register button
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check = true;
                UserModel userModel;
                UserListView userListView;

                databaseHelper = new DatabaseHelper(RegisterActivity.this);
                try{
                    // check if the email or the username exists
                    List<UserModel> userList = databaseHelper.getEveryone();
                    for(int i = 0; i < userList.size(); i ++){
                        if(userList.get(i).getEmail().equals(email_et.getText().toString())){
                            Toast.makeText(RegisterActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();

                            check = false;

                            break;
                        }
                        else if(userList.get(i).getUsername().equals(username_et.getText().toString())){
                            Toast.makeText(RegisterActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();

                            check = false;

                            break;
                        }
                    }
                    if(check){
                        userModel = new UserModel(-1, email_et.getText().toString(), username_et.getText().toString(), password_et.getText().toString());

                        // add the customer to the database
                        boolean success = databaseHelper.addOne(userModel);
                        if(success){
                            Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();

                            // Take the user back to the login page
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registered unsuccessfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch(Exception e) {
                    Toast.makeText(RegisterActivity.this, "Error registering user", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "error", "error", "error");
                }
            }
        });
    }
}