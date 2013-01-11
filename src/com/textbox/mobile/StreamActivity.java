package com.textbox.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class StreamActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stream);
		Intent browserIntent = 
                new Intent(Intent.ACTION_VIEW, Uri.parse("http://localhost:8888/txtbox/16607_413640852047309_1762540886_n.jpg"));
		startActivity(browserIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}

	
}
