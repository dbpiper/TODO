package com.dbpiper.todo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddItemFragment extends Fragment{
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private TodoItemDao mTodoItemDao;
    private Button mDateButton;
    private TextView mDueDate;
    private Button mTimeButton;
    private TextView mDueTime;

    private View.OnClickListener mTableRowClickHandler;
    private boolean[] mSelectedRows;


    private Date getDate() {

        final SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return df.parse(mDueDate.getText().toString());
        } catch (Exception e) {
            return new Date();
        }
    }

    private Date getTime() {
        final SimpleDateFormat df = new SimpleDateFormat("h:m");
        try {
            return df.parse(mDueTime.getText().toString());
        } catch (Exception e) {
            return new Date();
        }
    }

    private void setDate(Date date) {
        final SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        final String formattedDate = df.format(date);
        mDueDate.setText(formattedDate);
    }

    private void setTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int am_pm = calendar.get(Calendar.AM_PM);

        final SimpleDateFormat df = new SimpleDateFormat("h:mm");
        String formattedDate = df.format(date);

        if (am_pm == Calendar.AM) {
            formattedDate += " AM";
        } else {
            formattedDate += " PM";
        }

        mDueTime.setText(formattedDate);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        DaoSession daoSession = ((App) getActivity().getApplication()).getDaoSession();
        mTodoItemDao = daoSession.getTodoItemDao();

        Calendar c = Calendar.getInstance();
        mDueDate = (TextView) view.findViewById(R.id.textViewDueDate);
        mDueTime = (TextView) view.findViewById(R.id.textViewDueTime);
        setDate(c.getTime());
        setTime(c.getTime());

        mSelectedRows = new boolean[2];
        mSelectedRows[0] = false;
        mSelectedRows[1] = false;


        mDateButton = (Button) view.findViewById(R.id.buttonDate);
        mDateButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               FragmentManager manager = getFragmentManager();
               DatePickerFragment dialog = DatePickerFragment.newInstance(getDate());
               dialog.setTargetFragment(AddItemFragment.this, REQUEST_DATE);
               dialog.show(manager, DIALOG_DATE);
           }
        });

        mTimeButton = (Button) view.findViewById(R.id.buttonTime);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(getTime());
                dialog.setTargetFragment(AddItemFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });

        TableRow tableRowDueDate = (TableRow) view.findViewById(R.id.tableRowDueDate);
        TableRow tableRowDueTime = (TableRow) view.findViewById(R.id.tableRowDueTime);

        tableRowDueDate.setTag(0);
        tableRowDueTime.setTag(1);

        mTableRowClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int row = (int) v.getTag();

                if (!mSelectedRows[row]) {
                    v.setBackgroundColor(Color.GRAY);
                } else {
                    v.setBackgroundColor(Color.WHITE);
                }
                mSelectedRows[row] = !mSelectedRows[row];
            }
        };

        tableRowDueDate.setOnClickListener(mTableRowClickHandler);
        tableRowDueTime.setOnClickListener(mTableRowClickHandler);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            setDate(date);
        }

        if (requestCode == REQUEST_TIME) {

            Date time = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            setTime(time);
        }
    }

    // handles the back pressed functionality,
    // this is called by the parent activity
    public void onBackPressed() {
        String title = ((EditText)getActivity().findViewById(R.id.editTextTitle))
                .getText().toString();
        String description = ((EditText) getActivity().findViewById(R.id.editTextDescription))
                .getText().toString();

        if (title.length() != 0) {
            TodoItem todoItem = new TodoItem();
            todoItem.setTitle(title);
            todoItem.setDescription(description);
            todoItem.setDueDate(getDate());
            mTodoItemDao.insert(todoItem);
        }
    }

    public void clickHandleRow(View v) {
        Toast.makeText(getActivity(), "This is my Toast message!",
                Toast.LENGTH_LONG).show();
    }

}
