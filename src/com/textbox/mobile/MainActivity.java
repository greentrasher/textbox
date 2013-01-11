package com.textbox.mobile;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	
	
	 /** Called when the activity is first created. */
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	 
	        TabHost tabHost = getTabHost();
	 
	        TabSpec stream = tabHost.newTabSpec("Stream");
	        stream.setIndicator("", getResources().getDrawable(R.drawable.ccsm));
	        Intent mainIntent = new Intent(this, StreamActivity.class);
	        stream.setContent(mainIntent);
	        
	        // Tab for Photos
	        TabSpec photospec = tabHost.newTabSpec("Compose");
	        // setting Title and Icon for the Tab
	        //photospec.setIndicator("Compose", getResources().getDrawable(R.drawable.icon_photos_tab));
	        photospec.setIndicator("",  getResources().getDrawable(R.drawable.rssicon));
	        Intent photosIntent = new Intent(this, Compose.class);
	        photospec.setContent(photosIntent);
	 
	        // Tab for Songs
	        TabSpec songspec = tabHost.newTabSpec("View");
	        songspec.setIndicator("", getResources().getDrawable(R.drawable.searchicon));
	        Intent songsIntent = new Intent(this, ViewActivity.class);
	        songspec.setContent(songsIntent);

	        // Adding all TabSpec to TabHost
	        tabHost.addTab(stream);
	        tabHost.addTab(photospec); // Adding photos tab
	        tabHost.addTab(songspec); // Adding songs tab
	       // tabHost.addTab(videospec); // Adding videos tab
	        tabHost.setCurrentTab(0);
	    }
	
}
