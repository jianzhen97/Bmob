package com.lenovo.bmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Achat extends AppCompatActivity {
    EditText editTextxkey;
    EditText editTextykey;
    EditText editTextxvaule;
    EditText editTextyvalue;
    Button buttonx;
    Button buttony;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat);
        Bmob.initialize(this, "9b60d9e9bd274fe74b85fd25ff448179");
        editTextxkey = (EditText) findViewById(R.id.xkey);
        editTextykey = (EditText) findViewById(R.id.ykey);
        editTextxvaule = (EditText) findViewById(R.id.xvalue);
        editTextyvalue = (EditText) findViewById(R.id.yvalue);
        buttonx = (Button) findViewById(R.id.xsubmit);
        buttony = (Button) findViewById(R.id.ysubmit);


    }

    public void xsubmit(View view) {
        XBean xBean = new XBean();
        xBean.setKey(editTextxkey.getText().toString());
        xBean.setValue(editTextxvaule.getText().toString());
        xBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Log.e("ss111", s);
            }
        });
    }

    public void ysubmit(View view) {
        Ybean Ybean = new Ybean();
        Ybean.setKey(editTextykey.getText().toString());
        Ybean.setValue(editTextyvalue.getText().toString());
        Ybean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Log.e("ss222", s);
            }
        });
    }
}
