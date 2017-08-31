package com.hnxy.farmshop.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.hnxy.farmshop.bean.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsUtil {

    private Context context;

    private ContactsUtil(Context context){
        this.context = context;
    }
    public static ContactsUtil getInstance(Context context){
        return new ContactsUtil(context);
    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.RawContacts.CONTENT_URI,
                new String[]{ContactsContract.RawContacts._ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY}//
                , null, null, null);
        if(null == cursor) return null;
        cursor.moveToFirst();
        if(cursor.isFirst()){
            while (cursor.moveToNext()){
                Contact contact = new Contact();
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts._ID));// "_id"
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY));// "display_name"
                contact.setId(id);
                contact.setName(name);
                //联系人电话
                Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI//
                        , new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER }// "data1"
                        , "raw_contact_id=?", new String[] { id }, null);
                if (null == phoneCursor) return null;
                phoneCursor.moveToFirst();
                if (phoneCursor.isFirst()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contact.setPhone(number);
                }
                phoneCursor.close();
                // 联系人邮箱
                Cursor emailCursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI//
                        , new String[] { ContactsContract.CommonDataKinds.Email.ADDRESS}// "data1"
                        , "raw_contact_id=?", new String[] { id }, null);
                if (null == emailCursor) return null;
                emailCursor.moveToFirst();
                if (emailCursor.moveToNext()) {
                    String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    contact.setEmail(email);
                }
                emailCursor.close();
                contacts.add(contact);
            }
        }
        cursor.close();
        Log.i("contacts", "getContacts: "+ Arrays.toString(contacts.toArray()));
        return contacts;
    }
}
