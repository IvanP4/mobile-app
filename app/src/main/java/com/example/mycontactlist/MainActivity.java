package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListButton();
        initMapButton();
        initSettingsButton();
        initToggleButton();
        initChangeDateButton();
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
        ;
    }

    public void initMapButton() {
        ImageButton ibList = findViewById(R.id.imageButtonMap);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
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

        if(enabled){
            editName.requestFocus();
        }


    }

    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        TextView birthDay = findViewById(R.id.textBirthday);

        birthDay.setText(DateFormat.format("MM/dd/yyyy", selectedTime));
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
}
















