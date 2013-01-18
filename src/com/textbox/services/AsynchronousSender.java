package com.textbox.services;

import android.os.AsyncTask;

public class AsynchronousSender extends AsyncTask<String, Integer, String> {
		
		protected String doInBackground(String... params) {
			Sender sender = new Sender();
			//for( String param : params ){
				sender.setSrc(params[0]);
				sender.setDst(params[1]);
				sender.setMsg(params[2]);
				
				sender.sendSms();
			//}
			
			return sender.getResponse();
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		}
		
		

}
