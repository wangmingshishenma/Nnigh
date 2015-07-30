package com.mycompany.myapp;
import android.app.*;
import android.view.*;
import android.content.*;
import android.util.*;
import android.os.*;
import android.widget.*;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.internal.app.*;
import android.database.sqlite.*;
import android.database.*;
import android.view.View.*;
import android.support.design.widget.*;

public class DisplayContent extends ActionBarActivity
{

	private TextView tv,dispaly;
	private Toolbar tb;
	private SQLiteDatabase db;
	private MySqliteDataBase mysqlitedatabase;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_content);
		tv=(TextView)findViewById(R.id.display_contentTextView);
		dispaly=(TextView)findViewById(R.id.dispaly_content);
		tv.setText(getIntent().getStringExtra(MySqliteDataBase.CONTENT));
		dispaly.setText(getIntent().getStringExtra(MySqliteDataBase.TITLE));
	    tb=(Toolbar)findViewById(R.id.dispalytoolbar);
		setSupportActionBar(tb);
		ActionBar ab=getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		mysqlitedatabase=new MySqliteDataBase(this);
		db=mysqlitedatabase.getWritableDatabase();
		textonclick();
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		menu.clear();
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id=item.getItemId();
		if(item.getItemId()==android.R.id.home){
			finish();
			return true;
			}
		if(id==R.id.datale){
			datale();
			}
		if(id==R.id.send){
			share();
		}
		return super.onOptionsItemSelected(item);
	}

	public void datale(){
		AlertDialog.Builder ad=new AlertDialog.Builder(this);
		ad.setMessage("删除将无法恢复，是否确定！");
		ad.setNegativeButton("取消",null);
		ad.setPositiveButton("确定", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					db.delete(MySqliteDataBase.TABLE_NAME, "_id="+getIntent().getStringExtra(MySqliteDataBase.ID),null);
					Toast.makeText(DisplayContent.this,R.string.datale,16).show();
					finish();
				}						
		}).show();
		}
		
	public void share(){
		Intent i=new Intent();
		i.setAction(Intent.ACTION_SEND);
		i.putExtra(Intent.EXTRA_TEXT,getIntent().getStringExtra(MySqliteDataBase.TITLE));
		i.setType("text/plain");
		startActivity(i);
		}
		
	public void textonclick(){
		tv.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					copy(getIntent().getStringExtra(MySqliteDataBase.CONTENT),DisplayContent.this);
					Snackbar.make(p1,"已复制到剪切板！",Snackbar.LENGTH_LONG).show();
					return true;
				}		
		});
		dispaly.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					copy(getIntent().getStringExtra(MySqliteDataBase.TITLE),DisplayContent.this);
					Snackbar.make(p1,"已复制到剪切板",Snackbar.LENGTH_LONG).show();
					return true;
				}
				
			
		});
	}
	
	public static void copy(String content, Context context)  
	{  
		ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);  
		cmb.setText(content.trim());  
	}  
}
