package com.dbpiper.todo;

/**
 * Created by suerg on 4/10/2017.
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.sql.Time;
import java.util.Date;

@Entity
public class TodoItem {
    @Id
    private Long id;

    @NotNull
    private String title;

    private String description;

    private Date dueDate;

    private boolean archived;

    @Generated(hash = 33245341)
    public TodoItem(Long id, @NotNull String title, String description, Date dueDate,
            boolean archived) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.archived = archived;
    }


    @Generated(hash = 1307818545)
    public TodoItem() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}

