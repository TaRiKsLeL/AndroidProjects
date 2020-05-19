package com.example.room_1;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.NonNull;

@Database(entities = {PersonEntity.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
    private static PersonDatabase personDatabase;
    public static PersonDatabase getPersonDatabase(final Context context) {
        if (personDatabase == null) {
            synchronized (PersonDatabase.class) {
                if (personDatabase == null) {
                    personDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            PersonDatabase.class, "PersonDB.db").
                            addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                }
                            }).build();
                }
            }
        }
        return personDatabase;
    }
}
