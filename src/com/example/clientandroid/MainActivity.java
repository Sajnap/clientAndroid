package com.example.clientandroid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
public void getAdviceClick(View v) {
	EditText edit_ip=(EditText)findViewById(R.id.editText1);
	EditText edit_port=(EditText)findViewById(R.id.editText2);
	new MyAsyncTask().execute(edit_ip.getText().toString(),edit_port.getText().toString());
}
	public class MyAsyncTask extends AsyncTask<String, Void, String>
	{
		@Override
		protected void onPreExecute() {
			Toast.makeText(getApplicationContext(), "Starting Async to get advice", Toast.LENGTH_SHORT).show();
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params) {
			String advice ="";
			try {
				Socket socket = new Socket(params[0], Integer.parseInt(params[1]));
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				advice = reader.readLine();

				reader.close();
				socket.close();
			} catch (Exception e) {
			}

			return advice;
		}
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), "Finished Async to get advice\n"+result, Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
		}


	}
}
