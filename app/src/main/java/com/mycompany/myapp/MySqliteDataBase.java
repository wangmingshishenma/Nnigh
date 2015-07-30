package com.mycompany.myapp;
import android.database.sqlite.*;
import android.content.*;

public class MySqliteDataBase extends SQLiteOpenHelper
{

	public static final String TABLE_NAME="NoteDB";
	public static final String TITLE="Title";
	public static final String CONTENT="Content";
	public static final String ID="_id";
	
	public MySqliteDataBase(Context context){
		super(context,"NoteDB.db",null,1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL("CREATE TABLE "+ TABLE_NAME + "(" + ID
		+ " INTEGER PRIMARY KEY AUTOINCREMENT," + TITLE
		+ " TEXT NOT NULL , " + CONTENT + " TEXT NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
	}
	
}
