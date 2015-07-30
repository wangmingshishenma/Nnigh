package com.mycompany.myapp;
import android.widget.*;
import android.view.*;
import android.support.v7.widget.*;
import android.database.*;
import android.content.*;
import android.view.View.*;
import android.support.design.widget.*;
import android.database.sqlite.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

	private Context context;
	private static Cursor cursor;

	public RecyclerViewAdapter(Context context,Cursor cursor) {

		this.context=context;
		this.cursor=cursor;
	}
	
	@Override

	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_card,viewGroup,false);

		ViewHolder vh = new ViewHolder(view);

		return vh;
	}

	@Override

	public void onBindViewHolder(ViewHolder viewHolder, int position) {

		cursor.moveToPosition(position);
		String ct=cursor.getString(cursor.getColumnIndex(MySqliteDataBase.CONTENT));
		viewHolder.mTextView.setText(ct);

	}
	@Override

	public int getItemCount() {

		return cursor.getCount();

	}

	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		
		public TextView mTextView;

		public ViewHolder(View view){

			super(view);

			mTextView = (TextView) view.findViewById(R.id.item_cardTextView);
			view.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						cursor.moveToPosition(getPosition());
						Intent i=new Intent();
						i.setAction(Intent.ACTION_MAIN);
						i.putExtra(MySqliteDataBase.TITLE,cursor.getString(cursor.getColumnIndex(MySqliteDataBase.TITLE)));
						i.putExtra(MySqliteDataBase.CONTENT,cursor.getString(cursor.getColumnIndex(MySqliteDataBase.CONTENT)));
						i.putExtra(MySqliteDataBase.ID,cursor.getString(cursor.getColumnIndex(MySqliteDataBase.ID)));
						i.setClass(p1.getContext(),DisplayContent.class);
						p1.getContext().startActivity(i);
					}			
		});
		}
	}

}
