package com.example.mycontactlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> contactData;
    private View.OnClickListener mOnItemClickListener;
    private boolean isDeleting; // Initialize to false
    private Context parentContext;

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewContact;
        public TextView textPhone;
        public Button deleteButton;
        public Switch deleteSwitch; 

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textViewName);
            textPhone = itemView.findViewById(R.id.textPhoneNumber);
            deleteButton = itemView.findViewById(R.id.buttonDeleteContact);
            deleteSwitch = itemView.findViewById(R.id.switchDelete);

            deleteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    isDeleting = isChecked; // Update isDeleting when switch state changes
                }
            });

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public ContactAdapter(ArrayList<Contact> arrayList, Context context) {
        contactData = arrayList;
        parentContext = context;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ContactViewHolder cvh = holder;
        cvh.textViewContact.setText(contactData.get(position).getContactName());
        cvh.textPhone.setText(contactData.get(position).getPhoneNumber());
        if (isDeleting) {
            cvh.deleteButton.setVisibility(View.VISIBLE);
            cvh.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });
        } else {
            cvh.deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void setDelete(boolean b) {
        isDeleting = b;
    }

    private void deleteItem(int position) {
        Contact contact = contactData.get(position);
        ContactDataSource ds = new ContactDataSource(parentContext);
        try {
            ds.open();
            boolean didDelete = ds.deleteContact(contact.getContactID());
            ds.close();
            if (didDelete) {
                contactData.remove(position);
                notifyDataSetChanged();
            } else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return contactData.size();
    }
}
