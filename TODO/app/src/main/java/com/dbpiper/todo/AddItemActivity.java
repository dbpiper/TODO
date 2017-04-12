package com.dbpiper.todo;

import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class AddItemActivity extends AppCompatActivity {
    private TodoItemDao todoItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbarAddItem = (Toolbar) findViewById(R.id.toolbarAddItem);
        setSupportActionBar(toolbarAddItem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DaoSession daoSession = ((App) this.getApplication()).getDaoSession();
        todoItemDao = daoSession.getTodoItemDao();
    }

    public void handleBackPress() {
        Log.d("onbackpressec", "back pressed");
        
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("hello");
        todoItem.setDescription("i have a description");
        todoItemDao.insert(todoItem);
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
