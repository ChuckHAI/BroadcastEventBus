package test.client.com.broadcastreceiverdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by chuck on 2018/3/16.
 */

public class EventBusActivity extends Activity {

    private SeekBar mProbar;
    private Button mBtn;
    private int time = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);


        mProbar = findViewById(R.id.probar);
        mBtn = findViewById(R.id.bbbb);

        findViewById(R.id.bbbb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (time < 100) {
                            time += 5;
                            EventBus.getDefault().post(new MessageEvent(time));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });

        EventBus.getDefault().register(this);

    }


    //eventBus回调方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MessageEvent event) {
        mProbar.setProgress(event.getAge());
        mBtn.setText(event.getAge() + "");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
