package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private List<Person> personList;
    private DeleteCallback deleteCallback;

    public PersonAdapter(List<Person> personList, DeleteCallback deleteCallback) {
        this.personList = personList;
        this.deleteCallback = deleteCallback;
    }

    public void updatePersonList(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        TextView nameTextView = holder.itemView.findViewById(R.id.nameTextView);
        TextView ageTextView = holder.itemView.findViewById(R.id.ageTextView);
        Button button = holder.itemView.findViewById(R.id.button);

        final Person person = personList.get(position);

        nameTextView.setText(person.getName());
        ageTextView.setText(person.getAge() + "");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCallback.onDelete(person);
            }
        });

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}

class PersonViewHolder extends RecyclerView.ViewHolder {
    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

interface DeleteCallback {
    void onDelete(Person person);
}
