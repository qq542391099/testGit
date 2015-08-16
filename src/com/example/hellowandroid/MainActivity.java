package com.example.hellowandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private SeekBar seekBar1;
	private TextView textView3;
	private WakeLock wakeLock;
	private PowerManager pm;
	private static int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		textView3 = (TextView) findViewById(R.id.textView3);

		button1.setOnClickListener(new button1Listener());
		button2.setOnClickListener(new button2Listener());
		button3.setOnClickListener(new button3Listener());
		button4.setOnClickListener(new button4Listener());
		button5.setOnClickListener(new button5Listener());

		SeekBarlistener listener = new SeekBarlistener();
		seekBar1.setOnSeekBarChangeListener(listener);
		handler.postDelayed(runnable, 5000);
	}

	class SeekBarlistener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			String strOpt = progress + "%";
			textView3.setText(strOpt);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		// 开始获得唤醒锁
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
		
	//	pm.goToSleep(SystemClock.uptimeMillis());
	}

	@Override
	public void finish() {
		super.finish();
		// 释放锁
		Toast.makeText(this, "onfinish", Toast.LENGTH_SHORT).show();
		releaseWakeLock();
		
	}

	private void acquireWakeLock() {
		if (wakeLock == null) {
			Toast.makeText(this, "已经进入acquireWake", Toast.LENGTH_SHORT).show();
			pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "");
			wakeLock.acquire();
		}
	}

	private void releaseWakeLock() {
		Toast.makeText(this, "已经进入releaseWake", Toast.LENGTH_SHORT).show();
		if (wakeLock != null && wakeLock.isHeld()) {
			wakeLock.release();
			wakeLock = null;
		}

	}
	 Handler handler=new Handler();
	 Runnable runnable=new Runnable(){
	@Override
		public void run() {
		acquireWakeLock();
	}
	};
	class button1Listener implements OnClickListener {
		@Override
		public void onClick(View v) {
			releaseWakeLock();
		}
	}

	class button2Listener implements OnClickListener {
		@Override
		public void onClick(View v) {
			
		}
	}

	class button3Listener implements OnClickListener {
		@Override
		public void onClick(View v) {

		}
	}

	class button4Listener implements OnClickListener {
		@Override
		public void onClick(View v) {

		}
	}

	class button5Listener implements OnClickListener {
		@Override
		public void onClick(View v) {

		}
	}

}
