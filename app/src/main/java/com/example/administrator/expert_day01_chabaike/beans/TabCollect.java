package com.example.administrator.expert_day01_chabaike.beans;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class TabCollect {
    private String name;
    private String sqlName;

    public TabCollect(String name, String sqlName) {
        this.name = name;
        this.sqlName = sqlName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }
}
