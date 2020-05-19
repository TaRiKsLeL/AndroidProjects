package com.example.room_1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class PersonItemCallback extends DiffUtil.ItemCallback<PersonEntity> {
    @Override
    public boolean areItemsTheSame(@NonNull PersonEntity oldPerson, @NonNull PersonEntity newPerson) {
        return oldPerson.getId() == newPerson.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull PersonEntity oldPerson, @NonNull PersonEntity newPerson) {
        return oldPerson.getName().equals(newPerson.getName()) &&
                oldPerson.getFamilyName().equals(newPerson.getFamilyName());
    }
}
