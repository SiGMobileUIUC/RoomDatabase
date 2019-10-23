package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DeleteCallback {

    private List<Person> personList;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        personList = new ArrayList<>();
        adapter = new PersonAdapter(personList, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        loadPersonListFromDatabase();

        final EditText nameEditText = findViewById(R.id.nameEditText);
        final EditText ageEditText = findViewById(R.id.ageEditText);
        final Button addPersonButton = findViewById(R.id.addPersonButton);

        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                Person person = new Person(name, age);

                personList.add(person);
                adapter.updatePersonList(personList);
                addPersonToDatabase(person);
            }
        });
    }

    public void onDelete(Person person) {
        personList.remove(person);
        adapter.updatePersonList(personList);
        deletePersonFromDatabase(person);
    }

    public void loadPersonListFromDatabase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personList = PersonDatabase.getInstance(MainActivity.this).personDao().getPersonList();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updatePersonList(personList);
                    }
                });
            }
        }).start();
    }

    public void addPersonToDatabase(final Person person) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PersonDatabase.getInstance(MainActivity.this).personDao().insertPerson(person);
            }
        }).start();
    }

    public void deletePersonFromDatabase(final Person person) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PersonDatabase.getInstance(MainActivity.this).personDao().deletePerson(person);
            }
        }).start();
    }
}
