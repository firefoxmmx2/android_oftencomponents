package com.example.oftencomponents;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hooxin on 14-6-9.
 */
public class SysActionActivity extends Activity {
    final int PICK_CONTACT = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sysaction);
        Button bn = (Button) findViewById(R.id.sysActionViewContactBtn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("vnd.android.cursor.item/phone");
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case PICK_CONTACT:
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = intent.getData();
                    Cursor cursor = managedQuery(contactData, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = "此联系人暂未输入电话号码";

                        Cursor phones = getContentResolver().query(ContactsContract
                                        .CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                                null,
                                null
                        );

                        if (phones.moveToFirst()) {
                            phoneNumber = phones.getString(phones.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER
                            ));
                        }
                        phones.close();
                        EditText show = (EditText) findViewById(R.id.sysActionShowEdit);
                        show.setText(name);
                        EditText phone = (EditText) findViewById(R.id.sysActionPhoneEdit);
                        phone.setText(phoneNumber);
                    }
                    cursor.close();
                }
                break;
        }
    }
}