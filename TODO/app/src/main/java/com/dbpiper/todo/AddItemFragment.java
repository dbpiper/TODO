package com.dbpiper.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AddItemFragment extends Fragment{
    private TodoItemDao mTodoItemDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        DaoSession daoSession = ((App) getActivity().getApplication()).getDaoSession();
        mTodoItemDao = daoSession.getTodoItemDao();

        return view;
    }

    // handles the back pressed functionality,
    // this is called by the parent activity
    public void onBackPressed() {
        String title = ((EditText)getActivity().findViewById(R.id.editTextTitle))
                .getText().toString();
        String description = ((EditText) getActivity().findViewById(R.id.editTextDescription))
                .getText().toString();

        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(title);
        todoItem.setDescription(description);
        mTodoItemDao.insert(todoItem);
    }

}
