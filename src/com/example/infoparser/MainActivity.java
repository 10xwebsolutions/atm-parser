package com.example.infoparser;

import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText poIType;
	TextView Name,Vicinity,Lat,Lng;
	ProgressBar pgBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		poIType=(EditText)findViewById(R.id.editText1);
		Name=(TextView)findViewById(R.id.textView1); 
		Vicinity=(TextView)findViewById(R.id.textView2);
		Lat=(TextView)findViewById(R.id.textView3);
		Lng=(TextView)findViewById(R.id.textView4);
		pgBar=(ProgressBar)findViewById(R.id.progressBar1);
	}

	public void Parse(View a) {
		String city = URLEncoder.encode(poIType.getText().toString());
		if(!city.equals("")){
		new countryNameFetcher()
				.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=26.300,73.290&radius=10000&types="+city+"&name=&sensor=false&key=AIzaSyBPXwJ6XQDhCfQGX1QGJBsoy4z6a1rc0lw");
		}
		else
		{
			Toast.makeText(getApplicationContext(),"Please enter valid City Name",Toast.LENGTH_LONG).show();
		}
		}

	public class countryNameFetcher extends	AsyncTask<String, Void, Nitu> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pgBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Nitu doInBackground(String... abc) {
			// TODO Auto-generated method stub
			try {

				URL url = new URL(abc[0]);

				SAXParserFactory spf = SAXParserFactory.newInstance();
				XMLReader xr = spf.newSAXParser().getXMLReader();
				xr.setContentHandler(new XMLHandler());
				xr.parse(new InputSource(url.openStream()));
				Nitu xgs = XMLHandler.getGetterSetter();
				return xgs;
			} catch (Exception e) {
				// TODO: handle exception
				Log.i("MyException",e+"");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Nitu result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pgBar.setVisibility(View.INVISIBLE);
			try {

				if (result == null) {
					Toast.makeText(getApplicationContext(), "Connection Error",
							Toast.LENGTH_LONG).show();
					return;
				}
				Name.setText(result.getName());
				Vicinity.setText(result.getVicinity());
				Lat.setText(result.getLat());
				Lng.setText(result.getLng());
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


