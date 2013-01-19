package com.textbox.mobile;

//import com.textbox.mobile.activity.ContactListActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ViewActivity extends Activity{
	
	Button buttonSend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
	}
	
	public void onClick (View v){
		switch (v.getId()) {
	    	case R.id.buttonViewSmsMessage:
	    		// startActivity(new Intent(this, ContactListActivity.class));
	    	break;
	    	case R.id.buttonViewMmsMessage:
	    		startActivity(new Intent(this, ListMmsActivity.class));
	    	break;
	    	default:
	    		Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
	    	break;
		}
	}
}