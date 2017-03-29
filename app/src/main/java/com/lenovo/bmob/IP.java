package com.lenovo.bmob;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IP extends AppCompatActivity {
    private EditText mIp1;
    private EditText mIp2;
    private EditText mIp3;
    private EditText mIp4;
    private Button mButton;
    private String myIP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        getLocalIP();
        mIp1 = (EditText) findViewById(R.id.ip1);
        mIp2 = (EditText) findViewById(R.id.ip2);
        mIp3 = (EditText) findViewById(R.id.ip3);
        mIp4 = (EditText) findViewById(R.id.ip4);
        mButton = (Button) findViewById(R.id.button);

    }

    public void confirm(View view) {
        String ip1 = mIp1.getText().toString().trim();
        String ip2 = mIp2.getText().toString().trim();
        String ip3 = mIp3.getText().toString().trim();
        String ip4 = mIp4.getText().toString().trim();
        myIP = ip1 + ip2 + ip3 + ip4;
        String ip = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|[1-9])\\."
                + "(25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{2}|\\d)\\."
                + "(25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{2}|\\d)\\."
                + "(25[0-5]|2[0-4]\\d|1\\d{1,2}|\\d{2}|\\d)";//限定输入格式

        Pattern p = Pattern.compile(ip);
        Matcher m = p.matcher(myIP);
        boolean b=m.matches();
        if(ip1.equals("")||ip2.equals("")||ip3.equals("")||ip4.equals("")){
            Toast.makeText(this, "IP地址不可为空!", Toast.LENGTH_LONG).show();
        }
        else{
            if(b==false){
                Toast.makeText(this, "IP格式输入错误", Toast.LENGTH_LONG).show();
            }
    }
    }


    void getLocalIP() {
        WifiManager WifiManager = (android.net.wifi.WifiManager) getSystemService(WIFI_SERVICE);
        if (!WifiManager.isWifiEnabled()) {
            WifiManager.setWifiEnabled(true);
        }
        WifiInfo WifiInfo = WifiManager.getConnectionInfo();
        int ipAdderss = WifiInfo.getIpAddress();
        Toast.makeText(this, ipAdderss + "", Toast.LENGTH_SHORT).show();
        String ip = inToIP(ipAdderss);
        Toast.makeText(this, ip + "", Toast.LENGTH_SHORT).show();
    }

    private String inToIP(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }


}
