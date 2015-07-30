package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import android.support.v7.app.ActionBar;
import android.database.sqlite.*;
import android.database.*;
import android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity implements OnClickListener
{


	
	protected Toolbar mToolbar;
	private FloatingActionButton mfab;
	private RecyclerView rv;
	RecyclerViewAdapter recy;
	private MySqliteDataBase MYsqb;
	private SQLiteDatabase db;
	private Cursor cursor;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mToolbar=(Toolbar)findViewById(R.id.toolbar);
		mfab=(FloatingActionButton)findViewById(R.id.fab);
		mfab.setOnClickListener(this);
		rv=(RecyclerView)findViewById(R.id.recyclerview);
		setSupportActionBar(mToolbar);
		
  }
  
  	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.fab:
				Intent i=new Intent();
				i.setAction(Intent.ACTION_MAIN);
				i.setClass(MainActivity.this,AddContent.class);
				startActivity(i);
				break;
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		MYsqb=new MySqliteDataBase(this);
		db=MYsqb.getReadableDatabase();
		LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
		rv.setLayoutManager(mLayoutManager);
		rv.setHasFixedSize(true);
		rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
		cursor=db.query(MySqliteDataBase.TABLE_NAME,null,null,null,null,null,null);
		recy = new RecyclerViewAdapter(this,cursor);
		rv.setAdapter(recy);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		menu.clear();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id=item.getItemId();
		if(id==R.id.setting){
			Intent i=new Intent(MainActivity.this,SettingActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
