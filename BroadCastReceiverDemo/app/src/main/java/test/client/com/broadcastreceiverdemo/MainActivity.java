package test.client.com.broadcastreceiverdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private MyReceiver myReceiver;
    private IntentFilter intentFilter;

    public static final String BROADCAST_TAG = "COM.BROADCAST.NETWORK_DONG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_TAG);

        registerReceiver(myReceiver, intentFilter);  //动态注册
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(myReceiver, intentFilter);//本地注册


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //静态注册
                Intent intent = new Intent();
                intent.setAction("test.client.com.broadcastreceiverdemo.AB");
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.putExtra("key", "静态发送");
                intent.setComponent(new ComponentName("test.client.com.broadcastreceiverdemo", "test.client.com.broadcastreceiverdemo.MyReceiver"));
                getApplicationContext().sendBroadcast(intent);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态注册
                Intent intent = new Intent();
                intent.setAction(BROADCAST_TAG);
                intent.putExtra("key", "动态发送");
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //本地注册
                Intent intent = new Intent();
                intent.setAction(BROADCAST_TAG);
                intent.putExtra("key", "本地发送");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }
        });


        findViewById(R.id.eventBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(myReceiver);
    }

}
