package com.callmedj.accountforlazy.account;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import callmedj.com.accountforlazy.R;


public class MainItemAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private List<Map<String,String>> itemDetial;
	public MainItemAdapter(Context context){
		inflater = LayoutInflater.from(context);
	}
	public  static class viewHolder{
		public TextView itemMain;
		public TextView itemMoney;
	}


	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemDetial.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		viewHolder holder = new viewHolder();
		contentView = inflater.inflate(R.layout.array_item, null);
		holder.itemMain = (TextView) contentView.findViewById(R.id.item_main);
		holder.itemMoney = (TextView) contentView.findViewById(R.id.item_money);
		holder.itemMain.setText(this.itemDetial.get(position).get("ITEM"));
		holder.itemMoney.setText(this.itemDetial.get(position).get("MONEY"));
		contentView.setTag(holder);
		return contentView;
	}


	public List<Map<String, String>> getItemDetial() {
		return itemDetial;
	}


	public void setItemDetial(List<Map<String, String>> itemDetial) {
		this.itemDetial = itemDetial;
	}

}
