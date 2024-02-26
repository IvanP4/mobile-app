package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
     ArrayList<Contact> contacts;
     RecyclerView contactList;
    ContactAdapter contactAdapter;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int contactId = contacts.get(position).getContactID();
            Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
            intent.putExtra("contactID", contactId);
            startActivity(intent);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initAddContactButton();
        initDeleteSwitch();

    }

        @Override
        public void onResume(){
        super.onResume();

            String sortBy = getSharedPreferences("MyContactListPreferences",

                    Context.MODE_PRIVATE).getString("sortfield", "contactname");

            String sortOrder = getSharedPreferences("MyContactListPreferences",

                    Context.MODE_PRIVATE).getString("sortorder", "ASC");


            ContactDataSource ds = new ContactDataSource(this);
            try{
                ds.open();
                contacts = ds.getContacts(sortBy, sortOrder);
                ds.close();
                if (contacts.size() > 0) {
                    contactList = findViewById(R.id.rvContacts);
                    RecyclerView contactList = findViewById(R.id.rvContacts);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                    contactList.setLayoutManager(layoutManager);
                    ContactAdapter contactAdapter = new ContactAdapter(contacts, this);
                    contactList.setAdapter(contactAdapter);
                    contactAdapter.setOnItemClickListener(onItemClickListener);
                } else {
                    Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            } catch(
                    Exception e)

            {
                Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();


            }

        }

    private void initDeleteSwitch() {
        Switch s = findViewById(R.id.switchDelete);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Boolean status = isChecked;
                contactAdapter.setDelete(status);;
                contactAdapter.notifyDataSetChanged();
            }
        });
    }




    private void initAddContactButton() {
        Button newContact = findViewById(R.id.buttonAddContact);
        newContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}