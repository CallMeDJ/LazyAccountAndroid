package com.callmedj.accountforlazy.utils;

import com.callmedj.accountforlazy.entity.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class StringUtils {
	public static Map<String,Double> strToMoney(String str){
		Map<String,Double>  result = new HashMap<String,Double>();
		String name = "";
		String money = "";
		for(int i = 0 ; i < str.length() ; i++){
			String nowChar = str.charAt(i)+"";
			if(nowChar.equals(" ")) continue;
			else if(nowChar.equals("")) {
				money+= "";
				name+=" ";
				continue;
			}
			try{
				Double.parseDouble(nowChar);
				money+=nowChar;
				name+=" ";
			}
			catch(NumberFormatException e){
				money+=" ";
				name+=nowChar;
			}
		}
		StringTokenizer nameToken = new StringTokenizer(name," ");
		StringTokenizer moneyToken = new StringTokenizer(money," ");
		while(nameToken.hasMoreTokens()&&moneyToken.hasMoreTokens()){
			result.put(nameToken.nextToken(), Double.parseDouble(moneyToken.nextToken()));
		}
		return result;
	}


	public static Category learnCategory(){


		return null;
	}



}
