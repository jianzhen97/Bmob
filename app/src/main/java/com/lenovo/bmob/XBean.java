package com.lenovo.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by w on 2017/3/28.
 */

public class XBean extends BmobObject{
    String key;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
