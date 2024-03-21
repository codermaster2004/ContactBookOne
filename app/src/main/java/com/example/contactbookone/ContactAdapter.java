package com.example.contactbookone;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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

        holder.moreOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(activity, holder.moreOption);

                activity.getMenuInflater().inflate(R.menu.more_option_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.updateContact) {

                        } else if (item.getItemId() == R.id.deleteContact) {
                            Datahelper datahelper = new Datahelper(activity);
                            datahelper.deleteMyContact(userid);

                            Toast.makeText(activity, "Contact Deleted!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(activity, MainActivity.class);
//                            activity.startActivity(intent);
//                            activity.finish();

                            userDatalist.remove(position);
                            if (userDatalist.size() > 0) {
                                notifyDataSetChanged();
                            } else {
                                Intent intent = new Intent(activity, MainActivity.class);
                                activity.startActivity(intent);
                                activity.finish();
                            }

                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userDatalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvContact;
        TextView tvInitial;
        ImageView moreOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvContact = itemView.findViewById(R.id.tvContact);
            tvInitial = itemView.findViewById(R.id.tvInitial);
            moreOption = itemView.findViewById(R.id.moreOption);
        }
    }
}
