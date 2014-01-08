package com.example.cmp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DummyActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Change to our configure view
		setContentView(R.layout.dummy_activity);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);

		Toast.makeText(
		        getApplicationContext(),
		        "This activity is called because it is the configure activity of the widget.",
		        Toast.LENGTH_LONG).show();

	}

	public void onClick(View v) {
		finish();
	}

}
