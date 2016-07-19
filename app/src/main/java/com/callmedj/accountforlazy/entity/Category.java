package com.callmedj.accountforlazy.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by callmedj on 16/7/19.
 */
@Table(name = "CATEGORY")
public class Category extends Model{

    @Column(name = "ID_KEY")
    private String idKey;

    @Column(name = "CATEGORY_ID")
    private String categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    public List<FinancialRecord> financialRecordList;
    public List<FinancialRecord> getFinancialRecordList(){
        financialRecordList = getMany(FinancialRecord.class,"Category");
        return financialRecordList;
    }

    public String getId_key() {
        return idKey;
    }

    public void setId_key(String idKey) {
        this.idKey = idKey;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
