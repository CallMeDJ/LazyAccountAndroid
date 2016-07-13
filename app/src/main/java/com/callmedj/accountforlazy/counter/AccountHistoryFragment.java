package com.callmedj.accountforlazy.counter;

import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.callmedj.accountforlazy.account.PositionBarUtil;
import com.callmedj.accountforlazy.entity.FinancialRecord;
import com.callmedj.accountforlazy.utils.StringUtils;

import callmedj.com.accountforlazy.R;

public class AccountHistoryFragment extends Fragment {
	private EditText text;
	private View rootView;
	private Button btn;
	private LinearLayout listView;
	private Context context;
	private SQLiteDatabase database;
	private MediaPlayer player;

	@Override
	public void onAttach(Context context){
		super.onAttach(context);
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_account_history, container, false);
		return rootView;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart(){
		super.onStart();

		text = (EditText) rootView.findViewById(R.id.countInput);
		btn = (Button) rootView.findViewById(R.id.btn);
		listView = (LinearLayout) rootView.findViewById(R.id.moneyList);

		//Intent intent = new Intent(this,MyAlarmService.class);
		//startService(intent);
		refreshList();
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast toast = Toast.makeText(context,"�㰡",Toast.LENGTH_LONG);
				//toast.show();
				String string = text.getText().toString();
				text.setText("");
				Map<String, Double> data = StringUtils.strToMoney(string);
				try {
					ActiveAndroid.beginTransaction();
					for (String key : data.keySet()) {
						FinancialRecord r = new FinancialRecord();
						r.setItem(key);
						r.setAccount(data.get(key));
						r.setRecordDate(new Date());
						r.save();
					}
					ActiveAndroid.setTransactionSuccessful();
				} catch (SQLiteException e) {
					e.printStackTrace();
				} finally {
					ActiveAndroid.endTransaction();
				}
				listView.removeAllViews();
				refreshList();
				try {
					new Thread(new Runnable() {
						public void run() {
							player = MediaPlayer.create(context, R.raw.money);
							player.setAudioStreamType(AudioManager.STREAM_MUSIC);
							player.start();
						}
					}).start();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		refreshList();

	}

	private void refreshList(){
		List<FinancialRecord> fincialList = new Select().from(FinancialRecord.class).orderBy(FinancialRecord.COLUMN_RECORD_DATE + " DESC").execute();
		LinearLayout accountLayout =  PositionBarUtil.getPositionBarByList(context, fincialList);
		listView.addView(accountLayout);
	}

}
