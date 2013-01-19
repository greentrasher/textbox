/*package com.textbox.mobile.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.textbox.mobile.ComposeSmsActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

public class WrapperCursor {
	
	private static final String TAG = ComposeSmsActivity.class.getSimpleName();
	private Set<String> populateContacts( String byWhat ) {
		if( byWhat == null ){
			byWhat = "Numbers";
		}
		Set<String> contactNames = new HashSet<String>();
		Set<String> contactNumbers = new HashSet<String>();
		
	      ContentResolver cr = getContentResolver();
	        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
	                null, null, null, null);
	        int i=0;
	        if (cur.getCount() > 0) {
	            while (cur.moveToNext()) {
	                  String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
	                  String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	                  if (Integer.parseInt(cur.getString(
	                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
	                     Cursor pCur = cr.query(
	                               ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
	                               null,
	                               ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
	                               new String[]{id}, null);
	                     
	                     
	                     while (pCur.moveToNext()) {
	                         String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	                         contactNames.add(name);
	                 		 contactNumbers.add(phoneNo);
	                 		 
	                         //Toast.makeText(this, "Name: " + name + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
	                     }
	                    pCur.close();
	                }
	            }
	        }
	        
	        if( byWhat.equalsIgnoreCase("Numbers")){
				Log.d(TAG, "CONTACT! numbers : " +contactNumbers + ":"+ contactNumbers.size());
				
				return contactNumbers;
			}
			else {
				Log.d(TAG, "CONTACT! numbers : " +contactNames + ":"+ contactNames.size());
				return contactNames;
			}
	    }
	
	public void PopulatePeopleList()
	{

		mPeopleList.clear();

		Cursor people = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		while (people.moveToNext())
		{
			String contactName = people.getString(people.getColumnIndex(
					ContactsContract.Contacts.DISPLAY_NAME));

			String contactId = people.getString(people.getColumnIndex(
					ContactsContract.Contacts._ID));
			String hasPhone = people.getString(people.getColumnIndex(
					ContactsContract.Contacts.HAS_PHONE_NUMBER));

			if ((Integer.parseInt(hasPhone) > 0))
			{

				// You know have the number so now query it like this
				Cursor phones = getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,
						null, null);
				while (phones.moveToNext()) {

					//store numbers and display a dialog letting the user select which.
					String phoneNumber = phones.getString(
							phones.getColumnIndex(
									ContactsContract.CommonDataKinds.Phone.NUMBER));

					String numberType = phones.getString(phones.getColumnIndex(
							ContactsContract.CommonDataKinds.Phone.TYPE));

					Map<String, String> NamePhoneType = new HashMap<String, String>();

					NamePhoneType.put("Name", contactName);
					NamePhoneType.put("Phone", phoneNumber);

					if(numberType.equals("0"))
						NamePhoneType.put("Type", "Work");
					else
						if(numberType.equals("1"))
							NamePhoneType.put("Type", "Home");
						else if(numberType.equals("2"))
							NamePhoneType.put("Type",  "Mobile");
						else
							NamePhoneType.put("Type", "Other");

					//Then add this map to the list.
					mPeopleList.add(NamePhoneType);
				}
				phones.close();
			}
		}
		people.close();

		startManagingCursor(people);
	}
}
*/