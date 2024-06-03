package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserListView extends AppCompatActivity {
    ListView user_lv;
    Button back_btn, clear_btn;
    ArrayAdapter userArrayAdapter;
    DatabaseHelper databaseHelper;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_list_view);

        databaseHelper = new DatabaseHelper(UserListView.this);
        user_lv = findViewById(R.id.user_lv);
        back_btn = findViewById(R.id.userLv_back_btn);
        clear_btn = findViewById(R.id.userLv_clear_btn);

        showCustomersOnListView(databaseHelper);

        // when the back button is clicked
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListView.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // when the clear button is clicked
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
//                check = true;
                databaseHelper.clearTable();
                recreate();
            }
        });
    }

    public UserListView() {
    }

    public void showCustomersOnListView(DatabaseHelper databaseHelper2) {
        userArrayAdapter = new ArrayAdapter<>(UserListView.this, android.R.layout.simple_list_item_1, databaseHelper2.getEveryone());
        user_lv.setAdapter(userArrayAdapter);
    }

    public boolean checkClearBtn(){
        if(check){
            return true;
        }
        return false;
    }
}