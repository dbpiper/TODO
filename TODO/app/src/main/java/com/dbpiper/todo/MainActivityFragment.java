package com.dbpiper.todo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView todoListView = (ListView)view
                .findViewById(R.id.todoListView);
        ArrayList<String> todoList = new ArrayList<>();

        todoList.add("Laundry");
        todoList.add("Dishes");
        todoList.add("Programming");


        ArrayAdapter<String> todoAdapter = new ArrayAdapter<>(
                this.getContext(), R.layout.todo_list_item,
                todoList
        );

        if (todoListView != null) {
            todoListView.setAdapter(todoAdapter);
        }

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: This should be launching EditItem not AddItemActivity
//                Intent myIntent = new Intent(getActivity(), AddItemActivity.class);
//                getActivity().startActivity(myIntent);
            }
        });

        return view;
    }
}
