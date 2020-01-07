package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Dongxue2 extends AppCompatActivity {
    private  PrintTextView mPrintTextView;
    private ImageView imageView;
    private Intent intent,intent1;
    private int time;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_dongxue2);
        intent=getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(null!=bundle && bundle.get("flag")!=null){
                flag = Integer.parseInt((String) bundle.get("flag"));
            }
        }
        mPrintTextView = findViewById(R.id.pt_1);
        imageView=findViewById(R.id.iv_1);
        Timer timer=new Timer();
        startPrint(mPrintTextView);
        TimerTask task=new TimerTask()
        {
            @Override
            public void run(){
                intent1.putExtra("flag",Integer.toString(flag));
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        timer.schedule(task,time);
    }
    public void startPrint(View view) {
        switch(flag){
            case 4:
                imageView.setBackgroundResource(R.drawable.msy);
                mPrintTextView.setPrintText("果然没有猜错，方舟核心又回到了这里。真让人怀念啊，这远古的气息！" , 150);
                mPrintTextView.startPrint();
                time=5000;
                flag=4;
                intent1=new Intent (Dongxue2.this,Dongxue3.class);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.msy);
                mPrintTextView.setPrintText("堕世之神的力量，即将觉醒！" , 150);
                mPrintTextView.startPrint();
                time=2400;
                flag=5;
                intent1=new Intent (Dongxue2.this,Dongxue3.class);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.drj);
                mPrintTextView.setPrintText("但我们可以，交出核心。" , 150);
                mPrintTextView.startPrint();
                time=1800;
                flag=7;
                intent1=new Intent (Dongxue2.this,Dongxue3.class);
                break;
            case 7:
                imageView.setBackgroundResource(R.drawable.drj);
                mPrintTextView.setPrintText("这股气息，堕世神的力量。" , 150);
                mPrintTextView.startPrint();
                time=2000;
                flag=8;
                intent1=new Intent (Dongxue2.this,Dongxue3.class);
                break;
            case 8:
                imageView.setBackgroundResource(R.drawable.drj);
                mPrintTextView.setPrintText("先让扁鹊为你治疗吧。" , 150);
                mPrintTextView.startPrint();
                time=1800;
                flag=9;
                intent1=new Intent (Dongxue2.this,Dongxue3.class);
                break;
            case 9:
                imageView.setBackgroundResource(R.drawable.drj);
                mPrintTextView.setPrintText("这里的魔种还没消灭干净，但是尧天拿到方舟核心的事情必须赶紧汇报回长安城。这样吧，你与扁鹊先回长安城将这里的情况告诉木兰将军。" , 150);
                mPrintTextView.startPrint();
                time=9500;
                flag=10;
                intent1=new Intent (Dongxue2.this,Dongxue.class);
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
