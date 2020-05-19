package com.example.room_1;


import androidx.paging.PagedListAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PersonsAdapter extends PagedListAdapter<PersonEntity, PersonsAdapter.PersonsViewHolder> {

    public PersonsAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }


    @Override
    public PersonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PersonsViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.person_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(PersonsViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public static class PersonsViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView name;
        private TextView familyName;
        private PersonEntity personEntity;
        public PersonsViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            familyName = itemView.findViewById(R.id.famiy_name);
        }
        void bindTo(PersonEntity personEntity) {
            this.personEntity = personEntity;
            id.setText(String.valueOf(personEntity.getId()));
            name.setText(personEntity.getName());
            familyName.setText(personEntity.getFamilyName());
        }
    }
}
