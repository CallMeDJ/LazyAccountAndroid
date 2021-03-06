package com.callmedj.accountforlazy.account;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callmedj.accountforlazy.entity.FinancialRecord;

import java.util.List;

import callmedj.com.accountforlazy.R;

/**
 * Created by YANGZHAO549 on 2015-08-19.
 */
public class PositionBarUtil {
    public static LinearLayout getPositionBarByList(Context context , List<FinancialRecord> positionList){
        LinearLayout barContainer = new LinearLayout(context);
        barContainer.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TextView position_left_content ;
        TextView position_point;
        TextView position_right_content;
        TextView position_bar;

        barContainer.setOrientation(LinearLayout.VERTICAL);

        int size = positionList.size();
        boolean reach = true;


        for(int i = 0 ; i  <  size; i++){
            LinearLayout bar = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.postion_list_item_content,null);
            FinancialRecord financialRecord = positionList.get(i);
            position_left_content = (TextView)bar.findViewById(R.id.position_left_content);
            position_point = (TextView)bar.findViewById(R.id.position_point);
            position_right_content = (TextView)bar.findViewById(R.id.position_right_content);
            position_bar = (TextView)bar.findViewById(R.id.position_bar);

            //字体左右布局
                position_left_content.setText(financialRecord.getItem()+'-'+financialRecord.getAccount());
                position_right_content.setText(financialRecord.getRecordDate());


            //当前职位布局
            //if(reach){
        //   position_point.setSelected(true);
              //  position_bar.setSelected(true);
           // }




            if(i == size-1){
                position_bar.setVisibility(View.INVISIBLE);
            }
            bar.setGravity(Gravity.CENTER_HORIZONTAL);
            barContainer.addView(bar);
        }

        return barContainer;
    }


}
