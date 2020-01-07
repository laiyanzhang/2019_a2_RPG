package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class StoryActivity extends AppCompatActivity {
    private  PrintTextView mPrintTextView;
    private ImageView imageView;
    private  AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_story);
        mPrintTextView = findViewById(R.id.pt_my);
        imageView=findViewById(R.id.tv);
        animation=(AnimationDrawable) imageView.getBackground();
        animation.start();
        startPrint(mPrintTextView);
        Timer timer=new Timer();
        TimerTask task=new TimerTask()
        {
            @Override
            public void run(){
                Intent intent1=new Intent (StoryActivity.this,qiyuanzhidi1.class);
                startActivity(intent1);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        timer.schedule(task,40*1000);//此处的Delay可以是3*1000，代表三秒


    }

    public void startPrint(View view) {
        mPrintTextView.setPrintText("      拥有无限大陆最强力量的方舟核心再现，" +
                "为得到方舟核心的力量，魔种、稷下、长安城、西方、长城守卫军、尧天组织以及更多神秘势力掀起方舟核心的抢夺。"+
                "边境区域，魔种为争夺生存空间与人类发动大规模战争，包括千窟之城、玉城之内的地方遭受到了史无前例的破坏。" +
                "而在大陆中心的长安城更不安宁，尧天组织与西方势力率先找到方舟核心。" +
                "他们带着方舟核心前往王者峡谷启动远古的战斗机器，长城守卫军等势力集合王者峡谷阻止他们启动远古的战斗机器。" +
                "在争夺过程中，方舟核心再次失踪，但是争夺方舟核心的战争仍在继续……", 150);
        mPrintTextView.startPrint();

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
