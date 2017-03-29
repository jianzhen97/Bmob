package com.lenovo.bmob;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MyHandler extends AppCompatActivity {

    private TextView textView;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("sdssfd");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_handler);
        textView = (TextView) findViewById(R.id.textView);


    }

    public void change(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("main", isMainThread() + "");
                handler.sendEmptyMessage(0);

            }
        }).start();
    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
