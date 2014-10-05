package ch.ethz.inf.vs.a1.dianalo.sensors;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity{
	
	public final static String EXTRA_MESSAGE = "ch.ethz.inf.vs.a1.dianalo.sensors";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		final List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
		List<String> sensorNames = new ArrayList<String>();
		for(Sensor s : sensors){
			sensorNames.add(s.getName());
		}
		
		ListView sensorListVIEW = (ListView) findViewById(R.id.mainactivity_list_view);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, sensorNames);
		sensorListVIEW.setAdapter(adapter);
		
		sensorListVIEW.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
            	int itemPosition = arg2;
            	startSensor(sensors.get(itemPosition));				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	@SuppressLint("NewApi")
	public void startSensor(Sensor s){
		Intent intent = new Intent(this,SensorActivity.class);
		String[] sInfos = new String[3];
		sInfos[0] = s.getName();
		sInfos[1] = Integer.toString(s.getType());
		sInfos[2] = s.getVendor();
		intent.putExtra(EXTRA_MESSAGE, sInfos);
		startActivity(intent);
	}
	
	public void startActuatorsActivity(View v){
		Intent i = new Intent(this,ActuatorsActivity.class);
		startActivity(i);
		
	}
}
