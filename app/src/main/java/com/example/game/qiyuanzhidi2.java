package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class qiyuanzhidi2 extends AppCompatActivity {
    private  PrintTextView mPrintTextView;
    private ImageView imageView;
    private int flag;
    private int time;
    private Intent intent,intent1;
    private SQLiteDatabase db;
    private CreateActivity.MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_qiyuanzhidi2);
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
                imageView.setBackgroundResource(R.drawable.lyf);
                mPrintTextView.setPrintText("狄大人得到的消息应该不会错，如今各方势力都在寻找方舟核心，我们必须赶在他们之前找到它。糟了！这里还有魔种。", 150);
                mPrintTextView.startPrint();
                time = 9000;
                flag=1;
                intent1 = new Intent(qiyuanzhidi2.this, qiyuanzhidi3.class);
                break;
            case 2:
                db=myHelper.getWritableDatabase();
                ContentValues values1=new ContentValues();
                values1.put("jindu",2);
                db.update("juqing",values1,"_id=?",new String[]{"1"});
                db.close();
                imageView.setBackgroundResource(R.drawable.lyf);
                mPrintTextView.setPrintText("魔种太多了。我们分开走看看谁能逃出去，你走东边，我走西边，无论谁突围出去先找到狄大人汇报消息。", 150);
                mPrintTextView.startPrint();
                time = 8200;
                flag=2;
                intent1 = new Intent(qiyuanzhidi2.this, qiyuanzhidi1.class);
                break;
            case 3:
                db=myHelper.getWritableDatabase();
                ContentValues values2=new ContentValues();
                values2.put("jindu",3);
                db.update("juqing",values2,"_id=?",new String[]{"1"});
                db.close();
                imageView.setBackgroundResource(R.drawable.drj);
                mPrintTextView.setPrintText("看来魔种也在寻找方舟核心。这样吧，西边的洞穴有方舟核心的踪迹，你带着信号弹先去探寻一遍，要是发现有任何的踪迹就发射信号弹告诉我们。我先带着大部队去消灭魔种。", 150);
                mPrintTextView.startPrint();
                time = 13000;
                flag=3;
                intent1 = new Intent(qiyuanzhidi2.this, GameActivity.class);
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
