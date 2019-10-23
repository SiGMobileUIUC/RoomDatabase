package com.example.recyclerview;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Person.class, version = 1)
public abstract class PersonDatabase extends RoomDatabase {
    private static volatile PersonDatabase INSTANCE;

    public abstract PersonDao personDao();

    public static PersonDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PersonDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PersonDatabase.class, "person-db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
