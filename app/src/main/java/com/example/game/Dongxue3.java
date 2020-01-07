package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Dongxue3 extends AppCompatActivity {
    private  PrintTextView mPrintTextView;
    private ImageView imageView;
    private Intent intent,intent1;
    private int time;
    private int flag;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_dongxue3);
        myHelper=new CreateActivity.MyHelper(this);
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
                imageView.setBackgroundResource(R.drawable.mkbl);
                mPrintTextView.setPrintText("快点取走核心，那群该死的长城守卫军可不知道什么时候会出现。" , 150);
                mPrintTextView.startPrint();
                time=5000;
                flag=5;
                intent1=new Intent (Dongxue3.this,Dongxue.class);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.mkbl);
                mPrintTextView.setPrintText("（往我的方向看来）看来有人发现了，出来吧！" , 150);
                mPrintTextView.startPrint();
                time=3500;
                flag=6;
                intent1=new Intent (Dongxue3.this,FightActivity2.class);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.mkbl);
                mPrintTextView.setPrintText("凭你可阻止不了我们取走核心。" , 150);
                mPrintTextView.startPrint();
                time=2400;
                flag=6;
                intent1=new Intent (Dongxue3.this,Dongxue2.class);
                break;
            case 7:
                imageView.setBackgroundResource(R.drawable.msy);
                mPrintTextView.setPrintText("晚了！（明世隐、马可波罗等人取走方舟核心并凭空消失）" , 150);
                mPrintTextView.startPrint();
                time=4200;
                flag=7;
                intent1=new Intent (Dongxue3.this,Dongxue2.class);
                break;
            case 8:
                imageView.setBackgroundResource(R.drawable.lyf);
                mPrintTextView.setPrintText("你没事吧？" , 150);
                mPrintTextView.startPrint();
                time=1000;
                flag=8;
                intent1=new Intent (Dongxue3.this,Dongxue.class);
                break;
            case 9:
                imageView.setBackgroundResource(R.drawable.bq);
                mPrintTextView.setPrintText("（取出药瓶给你一饮而尽）" , 150);
                mPrintTextView.startPrint();
                ContentValues values1=new ContentValues();
                db=myHelper.getReadableDatabase();
                Cursor cursor1=db.query("shuxing",null,null,null,null,null,null);
                cursor1.moveToFirst();
                db=myHelper.getWritableDatabase();
                values1.put("xueliang",(cursor1.getInt(2)));
                db.update("shuxing",values1,"_id=?",new String[]{"1"});
                cursor1.close();
                db.close();
                time=2000;
                flag=9;
                intent1=new Intent (Dongxue3.this,Dongxue.class);
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
