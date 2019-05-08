package com.example.sep4xyz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectBlock extends AppCompatActivity {

    TextView blockF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_block);
        blockF= findViewById(R.id.selectBlockF);

    }


    public void selectF(View view) {
         Intent selectBlock= new Intent(SelectBlock.this,SelectFloor.class);
         startActivity(selectBlock);

    }
}
