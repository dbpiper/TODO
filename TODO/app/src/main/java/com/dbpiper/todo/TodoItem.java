package com.dbpiper.todo;

/**
 * Created by suerg on 4/10/2017.
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

@Entity
public class TodoItem {
    @Id
    private Long id;

    @NotNull
    private String title;

    private String description;

    private Date due;

    // TODO: figure out best way to store datetime

    @Generated(hash = 108964900)
    public TodoItem(Long id, @NotNull String title, String description, Date due) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.due = due;
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

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }
}
