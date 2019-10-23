package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Patrick Feltes", 21));
        personList.add(new Person("John Smith", 20));
        personList.add(new Person("Jane Doe", 20));
        personList.add(new Person("Patrick Feltes", 21));
        personList.add(new Person("John Smith", 20));
        personList.add(new Person("Jane Doe", 20));
        personList.add(new Person("Patrick Feltes", 21));
        personList.add(new Person("John Smith", 20));
        personList.add(new Person("Jane Doe", 20));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        PersonAdapter adapter = new PersonAdapter(personList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
