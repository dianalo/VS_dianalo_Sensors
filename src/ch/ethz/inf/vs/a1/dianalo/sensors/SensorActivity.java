package ch.ethz.inf.vs.a1.dianalo.sensors;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SensorActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		Intent intent = getIntent();
		String message[] = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
		
		
		ListView sensorInfosVIEW = (ListView) findViewById(R.id.sensorinfo);
		ArrayAdapter<String> a = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, message);
		sensorInfosVIEW.setAdapter(a);
		
		
		SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor s = sm.getDefaultSensor(Integer.parseInt(message[1]));
		ListView sensordataVIEW = (ListView) findViewById(R.id.sensor_activity_list);
		// continously update list
		fill(sensordataVIEW, s);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void fill(final ListView l, final Sensor s){
		final Context c = (Context) this;
		//invoke thread, read sensordata in every second...		
		new Thread(new Runnable(){
			public void run(){
				List<Float> data = new ArrayList<Float>();
				data.add(s.getPower());
				data.add(s.getResolution());
				ArrayAdapter<Float> adapter = new ArrayAdapter<Float>(c,
			              android.R.layout.simple_list_item_1, android.R.id.text1, data);
				l.setAdapter(adapter);
					//update data
//				while(true){
//					data.add(s.getPower());
//					data.add(s.getResolution());		
//					adapter.notifyDataSetChanged();
//				}
			}
		}).start();
	}

}
