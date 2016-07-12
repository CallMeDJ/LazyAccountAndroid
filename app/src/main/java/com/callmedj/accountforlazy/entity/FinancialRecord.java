package com.callmedj.accountforlazy.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
@Table(name = "FINANCIAL_RECORD")
public class FinancialRecord extends Model{
	public static String COLUMN_RECORD_DATE = "RECORD_DATE";
	public static String COLUMN_ITEM = "ITEM";
	public static String COLUMN_ACCOUNT = "ACCOUNT";
@Column( name = "RECORD_DATE")
private String recordDate;
@Column( name = "ITEM" )
private String item;
@Column( name = "ACCOUNT")
private Double account;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
public String getRecordDate() {
	return recordDate;
}
public void setRecordDate(Date recordDate) {
	this.recordDate = sdf.format(recordDate);
}
public String getItem() {
	return item;
}
public void setItem(String item) {
	this.item = item;
}
public Double getAccount() {
	return account;
}
public void setAccount(Double account) {
	this.account = account;
}
public static FinancialRecord getFinacialRecord(){
	return new Select().from(FinancialRecord.class).executeSingle();
}
	
}
