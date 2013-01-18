package com.textbox.mobile;


import java.util.ArrayList;
import java.util.List;

import com.textbox.services.AsynchronousSender;
import com.textbox.services.Sender;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeSmsActivity extends Activity{
	private static final String TAG = ComposeSmsActivity.class.getSimpleName();
	Sender sender;
	private EditText fromText;
	private EditText toText;
	private EditText msgText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_sms);
		sender = new Sender();

		// this will listen for text input then do something about it
		addKeyListener();
	}

	public void onClick (View v){
		try {
		switch (v.getId()) {
		case R.id.buttonSend:
			fromText = (EditText)findViewById(R.id.fromText);
			toText = (EditText)findViewById(R.id.toText);
			msgText = (EditText)findViewById(R.id.msgText);
			
			Log.d(TAG, "buttonSend" + fromText.getText().toString() +":"+ toText.getText().toString() +":"+ msgText.getText().toString());
			
			AsyncTask<String, Integer, String> as = new AsynchronousSender();
			
			as.execute( fromText.getText().toString(), toText.getText().toString(), msgText.getText().toString());
			
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
		});
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
		System.out.println("--search -- "+ key + ":" + keyCode);
		for ( String contact : contacts ){
			
			if( contact.contains(key)){
				sb.append(contact).append(",");
			}
		}
		
		return sb.toString();
	}
}