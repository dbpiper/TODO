package com.dbpiper.todo;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

public class AddItemActivity extends AppCompatActivity {
    private AddItemFragment mAddItemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbarAddItem = (Toolbar) findViewById(R.id.toolbarAddItem);
        setSupportActionBar(toolbarAddItem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAddItemFragment = (AddItemFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentAddItem);
    }

    public void handleBackPress() {
        mAddItemFragment.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        handleBackPress();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // back pressed toolbar
                handleBackPress();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
