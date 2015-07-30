package com.mycompany.myapp;
import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.support.v7.app.*;
import android.support.design.widget.*;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.view.View.*;
import android.content.*;
import android.database.sqlite.*;

public class AddContent extends ActionBarActivity implements OnClickListener
{

	
	private CollapsingToolbarLayout collapsingToolbar;
	private FloatingActionButton mfab;
	private EditText med,mcontent;
	private MySqliteDataBase mySDB;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontent);
		Toolbar mtool=(Toolbar)findViewById(R.id.addtoolbar);
		mfab=(FloatingActionButton)findViewById(R.id.addfab);
		mfab.setOnClickListener(this);
		med=(EditText)findViewById(R.id.title);
		mcontent=(EditText)findViewById(R.id.content);
		setSupportActionBar(mtool);
		ActionBar ab=getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		collapsingToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Demo");
		mySDB=new MySqliteDataBase(this);
		db=mySDB.getWritableDatabase();
		
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
	
	private void AddContent(){
		String i=med.getText().toString();
		if(i.length()>10||i.equals("Fuck")){
			Toast.makeText(AddContent.this,"Title超数无法写入！",Toast.LENGTH_LONG).show();
		}else{
		ContentValues cv=new ContentValues();
		cv.put(MySqliteDataBase.TITLE,mcontent.getText().toString());
		cv.put(MySqliteDataBase.CONTENT,med.getText().toString());
		db.insert(MySqliteDataBase.TABLE_NAME,null,cv);
		Toast.makeText(this,R.string.sava,Toast.LENGTH_LONG).show();
		finish();
		}
	}
	
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.addfab:
				String e=med.getText().toString();
				if(e.isEmpty()){
					Snackbar.make(p1,R.string.isempty, Snackbar.LENGTH_LONG).show();
				}
				else{
					AddContent();			
				}
				break;
		}
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		db.close();
	}

	
}
