package com.example.room_1;

import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    PersonViewModel personViewModel;
    PersonsAdapter personsAdapter;
    PersonDatabase personDatabase;
    EditText nameText;
    EditText familyNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personDatabase = PersonDatabase.getPersonDatabase(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        nameText = findViewById(R.id.name_text);
        familyNameText = findViewById(R.id.famiy_name_text);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        personsAdapter = new PersonsAdapter(new PersonItemCallback());
        personViewModel =
                ViewModelProviders.of(this).get(PersonViewModel.class);

        personViewModel.getPersonList().observe(this, pagedList -> {
            if (pagedList != null)  personsAdapter.submitList(pagedList);
        });
        recyclerView.setAdapter(personsAdapter);


    }

    public void Add(View view) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(nameText.getText().toString());
        personEntity.setFamilyName(familyNameText.getText().toString());
        personViewModel.insertPerson(personEntity);
    }
}
