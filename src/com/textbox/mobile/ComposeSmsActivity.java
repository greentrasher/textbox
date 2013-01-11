package com.textbox.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ComposeSmsActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_sms);
		
	}
	
	public void onClick (View v){
		switch (v.getId()) {
	    	case R.id.buttonSend:
	    		 //startActivity(new Intent(this, ComposeSmsActivity.class));
	    		// todo's send service
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

}
