package test.client.com.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by chuck on 2018/3/16.
 */

public class MyReceiver extends BroadcastReceiver {
    String value;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }

        if (networkInfo != null && networkInfo.isAvailable()) {
            Toast.makeText(context, "yes" + value, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "NO" + value, Toast.LENGTH_SHORT).show();
        }
    }
}
