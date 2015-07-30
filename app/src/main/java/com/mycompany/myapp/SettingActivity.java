package com.mycompany.myapp;
import android.app.*;
import android.os.*;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.view.*;

public class SettingActivity extends ActionBarActivity
{

	private Toolbar Tb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		Tb=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(Tb);
		ActionBar actionbar=getSupportActionBar();
		actionbar.setTitle(R.string.setting);
		actionbar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id=item.getItemId();
		if(id==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}	
	
}
