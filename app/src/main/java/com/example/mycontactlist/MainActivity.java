package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListButton();
        initMapButton();
        initSettingsButton();
        initToggleButton();
        initChangeDateButton();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            initContact(extras.getInt("contactid"));
        }
        else{
            currentContact = new Contact();
        }
        initTextChangedEvents();
        initSaveButton();
        hideKeyboard();



    }


    private void initListButton() {
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }

        });

    }

    public void initMapButton() {
        ImageButton ibList = findViewById(R.id.imageButtonMap);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
                if (currentContact.getContactID() == -1) {
                    Toast.makeText(getBaseContext(), "Contact must be saved before it can be mapped", Toast.LENGTH_LONG).show();
                }
                else{
                    intent.putExtra("contactid", currentContact.getContactID());
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


    private void initSettingsButton() {
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactSettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initToggleButton() {
        final ToggleButton editToggle = (ToggleButton) findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForEditing(editToggle.isChecked());

            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editName);
        EditText editaddress = findViewById(R.id.editaddress);
        EditText editcity = findViewById(R.id.editcity);
        EditText editstate = findViewById(R.id.editState);
        EditText editzipcode = findViewById(R.id.editzipcode);
        EditText edithome = findViewById(R.id.edithome);
        EditText editcell = findViewById(R.id.editcell);
        EditText editemail = findViewById(R.id.editemail);
        Button buttonChange = findViewById(R.id.buttonBirthday);
        Button buttonSave = findViewById(R.id.buttonsave);

        editName.setEnabled(enabled);
        editaddress.setEnabled(enabled);
        editcity.setEnabled(enabled);
        editstate.setEnabled(enabled);
        editzipcode.setEnabled(enabled);
        edithome.setEnabled(enabled);
        editcell.setEnabled(enabled);
        editemail.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }


    }

    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        TextView birthDay = findViewById(R.id.textBirthday);

        birthDay.setText(DateFormat.format("MM/dd/yyyy", selectedTime));
        currentContact.setBirthday(selectedTime);
    }


    private void initChangeDateButton() {
        Button changeDate = findViewById(R.id.buttonBirthday);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm, "DatePick");
            }
        });
    }

    private void initTextChangedEvents() {
        final EditText etContactName = findViewById(R.id.editName);

        etContactName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setContactName(etContactName.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int atg3) {
                //auto generate method sub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        final EditText etStreetAddress = findViewById(R.id.editaddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setStreetAddress(etStreetAddress.getText().toString());
            }

            public void beforeTextChanged(CharSequence ar0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

        });
        final EditText etCity = findViewById(R.id.editcity);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setCity(etCity.getText().toString());
            }

            public void beforeTextChanged(CharSequence ar0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


        });
        final EditText etState = findViewById(R.id.editState);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setState(etState.getText().toString());
            }

            public void beforeTextChanged(CharSequence ar0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etZipcode = findViewById(R.id.editzipcode);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setZipCode(etZipcode.getText().toString());
            }

            public void beforeTextChanged(CharSequence ar0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etHome = findViewById(R.id.edithome);
        etStreetAddress.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setPhoneNumber(etHome.getText().toString());
            }

            public void beforeTextChanged(CharSequence ar0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

        });
        final EditText etCell = findViewById(R.id.editcell);
        etStreetAddress.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setCellNumber(etCell.getText().toString());
            }

            public void beforeTextChanged(CharSequence ar0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        final EditText etEmail = findViewById(R.id.editemail);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentContact.setEmail(etEmail.getText().toString());
            }

            public void beforeTextChanged(CharSequence ar0, int arg1, int arg2, int arg3) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }


    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonsave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                ContactDataSource ds = new ContactDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentContact.getContactID() == -1) {
                        wasSuccessful = ds.insertContact(currentContact);
                    } else {
                        wasSuccessful = ds.updateContact(currentContact);
                    }
                    ds.close();
                    if (wasSuccessful) {
                        int newId = ds.getLastContactID();
                        currentContact.setContactID(newId);
                    }

                } catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                    editToggle.toggle();
                    setForEditing(false);

                }
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editaddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
    }





private void initContact(int id) {
    ContactDataSource ds = new ContactDataSource(MainActivity.this);
    try {
        ds.open();
        currentContact = ds.getSpecificContact(id);
        ds.close();
    } catch (Exception e) {
        Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
    }
    EditText editName = findViewById(R.id.editName);
    EditText editAddress = findViewById(R.id.editaddress);
    EditText editCity = findViewById(R.id.editcity);
    EditText editState = findViewById(R.id.editState);
    EditText editZipCode = findViewById(R.id.editzipcode);
    EditText editPhone = findViewById(R.id.edithome);
    EditText editCell = findViewById(R.id.editcell);
    EditText editEmail = findViewById(R.id.editemail);
    TextView birthDay = findViewById(R.id.textBirthday);

    editName.setText(currentContact.getContactName());
    editAddress.setText(currentContact.getStreetAddress());
    editCity.setText(currentContact.getCity());
    editState.setText(currentContact.getState());
    editZipCode.setText(currentContact.getZipcode());

    editPhone.setText(currentContact.getPhoneNumber());
    editCell.setText(currentContact.getCellNumber());
    editEmail.setText(currentContact.getEMail());
    birthDay.setText(DateFormat.format("MM/dd/yyyy,",
            currentContact.getBirthday().getTimeInMillis()).toString());
}
}


















