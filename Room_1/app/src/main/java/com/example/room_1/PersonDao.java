package com.example.room_1;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM Persons ORDER by Id Desc")
    DataSource.Factory<Integer, PersonEntity> getAll();

    @Insert
    void insertAll(PersonEntity... personEntities);

    @Delete
    void delete(PersonEntity personEntityEntity);
}
