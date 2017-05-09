package com.dbpiper.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by suerg on 5/7/2017.
 */

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    Context context;
    int layoutResourceId;
    List<TodoItem> data;

    public TodoItemAdapter(Context context, int layoutResourceId, List<TodoItem> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TodoItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TodoItemHolder();
            holder.title = (TextView)row.findViewById(R.id.textViewTitle);
            holder.dueDate = (TextView)row.findViewById(R.id.textViewDueDate);

            row.setTag(holder);
        } else {
            holder = (TodoItemHolder)row.getTag();
        }

        TodoItem todoItem = data.get(position);
        holder.title.setText(todoItem.getTitle());

        if (todoItem.getDueDate() != null) {
            final SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            final String formattedDate = df.format(todoItem.getDueDate());
            holder.dueDate.setText(formattedDate);
        }

        return row;
    }

    static class TodoItemHolder
    {
        TextView title;
        TextView dueDate;
    }
}
