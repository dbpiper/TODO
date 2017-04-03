package com.dbpiper.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbarAddItem = (Toolbar) findViewById(R.id.toolbarAddItem);
        setSupportActionBar(toolbarAddItem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
