package ch.ethz.inf.vs.a1.dianalo.sensors;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ActuatorsActivity extends ActionBarActivity implements OnSeekBarChangeListener{
	
	private Vibrator vib;
	private int duration = 50;
	private MediaPlayer mp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actuators);
		vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		SeekBar sb = (SeekBar) findViewById(R.id.seek_duration);
		sb.setOnSeekBarChangeListener((OnSeekBarChangeListener) this);
		initPlayer();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actuators, menu);
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
	
	public void vibrate(View v){
		vib.vibrate(duration*10);
	}
	
	@Override
	public void onProgressChanged(SeekBar s, int progress, boolean fromUser){
		duration = progress;
	}
	
	@Override
	public void onStartTrackingTouch(SeekBar s){
		
	}
	
	@Override
	public void onStopTrackingTouch(SeekBar s){
		
	}
	
	public void initPlayer(){
		mp = MediaPlayer.create(this, R.raw.sound);
	}
	
	public void onClickSound(View v){
		if(!mp.isPlaying()){
			mp.start();
			((Button) v).setText(R.string.btn_running);
			while(mp.isPlaying()){};
			((Button) v).setText(R.string.btn_stopped);
		}
		else{
			mp.stop();
			mp.prepareAsync();
			((Button) v).setText(R.string.btn_stopped);
		}
	}
	
}
