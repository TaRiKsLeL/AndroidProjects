package com.example.room_1;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonViewModel extends AndroidViewModel {
    private LiveData<PagedList<PersonEntity>> personList;
    private PersonDao personDao;
    ExecutorService IO_EXECUTOR;

    public LiveData<PagedList<PersonEntity>> getPersonList() {
        return personList;
    }

    public PersonViewModel(@NonNull Application application) {
        super(application);
        IO_EXECUTOR = Executors.newSingleThreadExecutor();
        personDao = PersonDatabase.getPersonDatabase(application).personDao();
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).
                setEnablePlaceholders(false)
                .setPageSize(15).build();

        personList = new LivePagedListBuilder<>(
                personDao.getAll(), pagedListConfig).build();
    }

    public void deletePerson(PersonEntity personEntity) {
        IO_EXECUTOR.execute(() -> {
                    personDao.delete(personEntity);
                }
        );
    }

    public void insertPerson(PersonEntity... personEntities) {
        IO_EXECUTOR.execute(() -> {
                    personDao.insertAll(personEntities);
                }
        );
    }
}
