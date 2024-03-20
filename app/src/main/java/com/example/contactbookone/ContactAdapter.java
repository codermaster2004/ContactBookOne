package com.example.contactbookone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Activity activity;
    ArrayList<UserData> userDatalist;

    public ContactAdapter(Activity activity, ArrayList<UserData> userDatalist) {
        this.activity = activity;
        this.userDatalist = userDatalist;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.contact_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        UserData userData = userDatalist.get(position);



        int userid = userData.getUserid();
        String name = userData.getName();
        String contact = userData.getContact();

        holder.tvInitial.setText(name);
        holder.tvName.setText(name);
        holder.tvContact.setText(contact);
    }

    @Override
    public int getItemCount() {
        return userDatalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvContact;
        TextView tvInitial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvContact = itemView.findViewById(R.id.tvContact);
            tvInitial = itemView.findViewById(R.id.tvInitial);
        }
    }
}
