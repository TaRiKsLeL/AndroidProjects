package com.example.room_1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "Persons")
public class PersonEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long Id;
    @NonNull
    private String Name;
    @NonNull
    private String familyName;

    @NonNull
    public Long getId() {
        return Id;
    }

    public void setId(@NonNull Long id) {
        Id = id;
    }

    @NonNull
    public String getName() {
        return Name;
    }

    public void setName(@NonNull String name) {
        Name = name;
    }

    @NonNull
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(@NonNull String familyName) {
        this.familyName = familyName;
    }

}
