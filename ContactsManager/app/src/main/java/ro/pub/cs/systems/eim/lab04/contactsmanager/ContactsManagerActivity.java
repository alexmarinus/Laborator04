package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;

import java.util.ArrayList;

import static android.R.attr.name;

public class ContactsManagerActivity extends AppCompatActivity implements View.OnClickListener{

    private Button showAddDetButton, saveButton, cancelButton;
    EditText nameText, phoneText, emailText, postText;

    @Override
    public void onClick (View v) {
        /*Save*/
        Button button = (Button) v;

        if (button.getId() == R.id.button) {
            LinearLayout hiddenLayout =  (LinearLayout) findViewById(R.id.hiddenlayout);

            if (button.getText().toString() == "Show additional details") {
                hiddenLayout.setVisibility(View.VISIBLE);
                button.setText ("Hide additional details");
            }
            else if (button.getText().toString() == "Hide additional details") {
                hiddenLayout.setVisibility(View.GONE);
                button.setText ("Show additional details");
            }
        }

        if (button.getId() == R.id.button2) {

            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            String name = null, phone = null, email = null, address = null,
                    jobTitle = null, company = null, website = null, im = null;
            name = nameText.getText().toString();
            phone = phoneText.getText().toString();
            email = emailText.getText().toString();
            address = postText.getText().toString();

            if (name != null) {
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
            }
            if (phone != null) {
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
            }
            if (email != null) {
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
            }
            if (address != null) {
                intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
            }
            if (jobTitle != null) {
                intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
            }
            if (company != null) {
                intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
            }
            ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
            if (website != null) {
                ContentValues websiteRow = new ContentValues();
                websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                contactData.add(websiteRow);
            }
            if (im != null) {
                ContentValues imRow = new ContentValues();
                imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                contactData.add(imRow);
            }
            intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
            startActivity(intent);
        }
        /*Show/hide additional details*/
        else if (button.getId() == R.id.button) {

        }
        /*Cancel*/
        else if (button.getId() == R.id.button3)
           finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        /*Buttons*/
        showAddDetButton = (Button) findViewById(R.id.button);
        saveButton = (Button) findViewById(R.id.button2);
        cancelButton = (Button) findViewById(R.id.button3);
        /*Edit texts*/
        nameText = (EditText) findViewById(R.id.editText);
        phoneText = (EditText) findViewById(R.id.editText2);
        emailText = (EditText) findViewById(R.id.editText3);
        postText = (EditText) findViewById(R.id.editText4);

        showAddDetButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

}
