package com.textbox.mobile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.textbox.services.AsynchronousSender;
import com.textbox.services.Sender;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ComposeSmsActivity extends Activity{
	private static final String TAG = ComposeSmsActivity.class.getSimpleName();
	private EditText fromText;
	private EditText toText;
	private EditText msgText;

	private ArrayList<Map<String, String>> mPeopleList;

    private SimpleAdapter mAdapter;
    private AutoCompleteTextView mTxtPhoneNo;

	private static final String[] COUNTRIES = new String[] {
		"Belgium", "France", "Italy", "Germany", "Spain"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_sms);
		
		//String [] numbers = null;
		
		Log.d(TAG, "CONTACT! : ");
		
		 mPeopleList = new ArrayList<Map<String, String>>();
	     PopulatePeopleList();
	    
	     mTxtPhoneNo = (AutoCompleteTextView) findViewById(R.id.toTextHintView);

	     mAdapter = new SimpleAdapter(this, mPeopleList, R.layout.activity_contact_list ,new String[] { "Name", "Phone" , "Type" }, new int[] { R.id.ccontName, R.id.ccontNo, R.id.ccontType });
	      
	     // mAdapter = new SimpleAdapter(this, mPeopleList, R.layout.activity_contact_list ,new String[] { "Name" }, new int[] { R.id.ccontName });
	     
	     mTxtPhoneNo.setAdapter(mAdapter);	
	     addKeyListener();
	}
	
	
	public void onClick ( View v){
		try {
			          
	          
		
		switch (v.getId()) {
		case R.id.buttonSend:
			fromText = (EditText)findViewById(R.id.fromText);
			toText = (AutoCompleteTextView)findViewById(R.id.toTextHintView);
			msgText = (EditText)findViewById(R.id.msgText);
		
			Log.d(TAG, "buttonSend" + fromText.getText().toString() +":"+ toText.getText().toString() +":"+ msgText.getText().toString());
			
			AsyncTask<String, Integer, String> as = new AsynchronousSender();
			
			as.execute( fromText.getText().toString(), toText.getText().toString() , msgText.getText().toString());
			
			Log.d(TAG, "sms send! : " +as.get());
			
			boolean success = true;
			// TODO's
			if ( success ){
				//save to outbox flag as sent
			}else {
				//save to drafts flag as not-sent
			}
			
			
			Toast.makeText(ComposeSmsActivity.this,
					as.get(), Toast.LENGTH_LONG).show();
			break;
		case R.id.buttonCancel:

			break;
		default:
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later!",
					Toast.LENGTH_LONG).show();
			break;
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void addKeyListener() {
		// to do's key lookup
		mTxtPhoneNo = (AutoCompleteTextView)findViewById(R.id.toTextHintView);
		
		mTxtPhoneNo.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int index, long arg) {
				Toast.makeText(ComposeSmsActivity.this,
						"here ", Toast.LENGTH_LONG).show();
				Map<String, String> map = (Map<String, String>) av.getItemAtPosition(index);
				Iterator<String> myVeryOwnIterator = map.keySet().iterator();
				while(myVeryOwnIterator.hasNext()) {
					String key=(String)myVeryOwnIterator.next();
					String value=(String)map.get(key);
					mTxtPhoneNo.setText(value);
					Toast.makeText(ComposeSmsActivity.this,
							value, Toast.LENGTH_LONG).show();
				}
			}
		});
		/*
		fromText =  (EditText) findViewById(R.id.fromText);
		
		fromText.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO's get list of number sorted by keyCode
				// 
				if ( event.getAction() == KeyEvent.ACTION_DOWN ) {
					// isNumber - populate number msisdn list
					// isAlpha  - populate contact name list
					String code = KeyEvent.keyCodeToString(keyCode);
					String list = search(code);
					
					//fromText.setText(list);
					
					// display a floating message
					Toast.makeText(ComposeSmsActivity.this,
							list, Toast.LENGTH_LONG).show();
					return true;
				}
				return false;
			}
		});*/
	}
	
	private String search(String keyCode){
		// todo's actual db query
		
		List<String> contacts = new ArrayList<String>();
		contacts.add("639081271840");
		contacts.add("639091111110");
		contacts.add("639163333330");
		contacts.add("639452233330");
		contacts.add("639225667777");
		StringBuilder sb = new StringBuilder();
		String arr[] = keyCode.split("_");
		String key = null;
		if(arr.length > 1){
			key = arr[1];
		}
		System.out.println("--search -- "+ key +
				
				":" + keyCode);
		for ( String contact : contacts ){
			
			if( contact.contains(key)){
				sb.append(contact).append(",");
			}
		}
		
		return sb.toString();
	}
	
	
	public void PopulatePeopleList(){
		//if(mPeopleList.size() > 0){
		//	Log.d(TAG, "gere! : " +mPeopleList.size());
		//	return;
		//}
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