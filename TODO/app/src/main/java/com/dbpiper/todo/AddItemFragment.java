package com.dbpiper.todo;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddItemFragment extends Fragment{
    private static final String DIALOG_DATE = "DialogDate";

    private TodoItemDao mTodoItemDao;
    private Button mDateButton;
    private TextView mDueDate;


    private Date getDate() {

        final SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return df.parse(mDateButton.getText().toString());
        } catch (Exception e) {
            return new Date();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        DaoSession daoSession = ((App) getActivity().getApplication()).getDaoSession();
        mTodoItemDao = daoSession.getTodoItemDao();

        Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        final String formattedDate = df.format(c.getTime());
        mDueDate = (TextView) view.findViewById(R.id.textViewDueDate);
        mDueDate.setText(formattedDate);


        mDateButton = (Button) view.findViewById(R.id.buttonDate);
        mDateButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               FragmentManager manager = getActivity().getFragmentManager();
               DatePickerFragment dialog = DatePickerFragment.newInstance(getDate());
               dialog.show(manager, DIALOG_DATE);
           }
        });


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
