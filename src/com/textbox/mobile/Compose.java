package com.textbox.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Compose extends Activity{
	
	Button buttonSend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		
	}
	
	public void onClick (View v){
		switch (v.getId()) {
	    	case R.id.buttonSmsMessage:
	    		 startActivity(new Intent(this, ComposeSmsActivity.class));
	    	break;
	    	case R.id.buttonMmsMessage:
	    		
	    	break;
	    	default:
	    		Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
	    	break;
		}
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compose, menu);
		return true;
	}*/
}
