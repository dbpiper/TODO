package com.dbpiper.todo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private TodoItemDao todoItemDao;
    private Query<TodoItem> todoItemQuery;
    private List<TodoItem> todoList;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView todoListView = (ListView)view
                .findViewById(R.id.todoListView);
        this.todoList = new ArrayList<>();

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: This should be launching EditItem not AddItemActivity
//                Intent myIntent = new Intent(getActivity(), AddItemActivity.class);
//                getActivity().startActivity(myIntent);
            }
        });

        setupDao();

        TodoItemAdapter todoAdapter = new TodoItemAdapter(
                this.getContext(), R.layout.todo_list_item,
                this.todoList
        );

        if (todoListView != null) {
            todoListView.setAdapter(todoAdapter);
        }

        return view;
    }

    private void setupDao() {
        // get the note DAO
        DaoSession daoSession = ((App) this.getActivity().getApplication()).getDaoSession();
        todoItemDao = daoSession.getTodoItemDao();

        // query all items, sorted a-z by their title
        todoItemQuery = todoItemDao.queryBuilder().orderAsc(TodoItemDao.Properties.Title).build();
        updateTodoList();
    }

    private void updateTodoList() {
        List<TodoItem> todoItems = todoItemQuery.list();
        for(TodoItem todoItem : todoItems) {
            this.todoList.add(todoItem);
        }
//        notesAdapter.setNotes(notes);
        // TODO: set list of items to this list
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.action_archive) {
            Toast.makeText(getActivity(), "Archive", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
