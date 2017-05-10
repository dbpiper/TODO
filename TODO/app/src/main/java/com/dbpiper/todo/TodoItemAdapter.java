package com.dbpiper.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.greenrobot.greendao.query.Query;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by suerg on 5/7/2017.
 */

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    Context context;
    int layoutResourceId;
    List<TodoItem> data;
    DaoSession daoSession;
    TodoItemDao todoItemDao;

    public TodoItemAdapter(Context context, int layoutResourceId, List<TodoItem> data,
                           DaoSession daoSession) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
        this.daoSession = daoSession;
        this.todoItemDao = daoSession.getTodoItemDao();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TodoItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TodoItemHolder();
            holder.title = (TextView)row.findViewById(R.id.textViewTitle);
            holder.dueDate = (TextView)row.findViewById(R.id.textViewDueDate);
            holder.checkBoxArchived = (CheckBox)row.findViewById(R.id.checkBoxArchived);

            row.setTag(holder);
        } else {
            holder = (TodoItemHolder)row.getTag();
        }

        final TodoItem todoItem = data.get(position);
        holder.title.setText(todoItem.getTitle());

        if (todoItem.getDueDate() != null) {
            final SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            final String formattedDate = df.format(todoItem.getDueDate());
            holder.dueDate.setText(formattedDate);
        }

        holder.checkBoxArchived.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Query<TodoItem> todoItemQuery = todoItemDao.queryBuilder()
                        .where(TodoItemDao.Properties.Id.eq(todoItem.getId()))
                        .orderAsc(TodoItemDao.Properties.Title).build();
                List<TodoItem> todoItems = todoItemQuery.list();
                for(TodoItem todoItem : todoItems) {
                    todoItem.setArchived(isChecked);
                    todoItemDao.update(todoItem);
                }

            }
        });

        return row;
    }

    static class TodoItemHolder
    {
        TextView title;
        TextView dueDate;
        CheckBox checkBoxArchived;
    }
}
