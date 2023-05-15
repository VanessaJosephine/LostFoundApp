package com.example.myapplication.sqlitehelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Advert {
    private int id;
    String name, type, phone, desc, date, location;

    public Advert(String name, String type, String phone, String desc, String date, String location) {
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.desc = desc;
        this.date = date;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
