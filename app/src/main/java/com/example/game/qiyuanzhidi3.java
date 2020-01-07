package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("ALL")
public class qiyuanzhidi3 extends AppCompatActivity {
    private  PrintTextView mPrintTextView;
    private ImageView imageView;
    private int flag;
    private int time;
    private Intent intent,intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_qiyuanzhidi3);
        intent=getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(null!=bundle && bundle.get("flag")!=null){
                flag = Integer.parseInt((String) bundle.get("flag"));
            }
        }
        mPrintTextView = findViewById(R.id.pt_1);
        imageView=findViewById(R.id.iv_1);
        startPrint(mPrintTextView);
        Timer timer = new Timer();
        startPrint(mPrintTextView);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                intent1.putExtra("flag", Integer.toString(flag));
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        timer.schedule(task, time);
    }

    public void startPrint(View view) {
        switch(flag) {
            case 1:
                imageView.setBackgroundResource(R.drawable.mz);
                mPrintTextView.setPrintText("方舟核心是属于我们的，你们走不掉。", 150);
                mPrintTextView.startPrint();
                time = 3700;
                flag=2;
                intent1 = new Intent(qiyuanzhidi3.this, qiyuanzhidi2.class);
                break;
        }
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

        }
    }
}
