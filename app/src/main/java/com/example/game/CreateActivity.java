package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    private TextView textView1,textView2,textView3,textView4,textView5;
    private EditText editText;
    private int scord1,scord2,scord3,scord4,scord5;
    private String num1,num2,num3,num4;
    private Button button1;
    private Button button9;
    private int id;
    private MyHelper myHelper;
    private int[] icons1={R.drawable.jialuo1, R.drawable.kai1,R.drawable.libai1, R.drawable.lixin1};
    private int[] icons2={R.drawable.yingzheng1, R.drawable.yao1, R.drawable.yase1, R.drawable.sunshangxiang1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBottomUIMenu();
        setContentView(R.layout.activity_create);
        ListView mListView = findViewById(R.id.lv);
        myHelper=new MyHelper(this);
        imageView=findViewById(R.id.iv_1);
        textView1=findViewById(R.id.tv_1);
        textView2=findViewById(R.id.tv_2);
        textView3=findViewById(R.id.tv_3);
        textView4=findViewById(R.id.tv_4);
        textView5=findViewById(R.id.tv_5);
        editText=findViewById(R.id.et);
        button1=findViewById(R.id.bt_1);
        Button button2 = findViewById(R.id.bt_2);
        Button button3 = findViewById(R.id.bt_3);
        Button button4 = findViewById(R.id.bt_4);
        Button button5 = findViewById(R.id.bt_5);
        Button button6 = findViewById(R.id.bt_6);
        Button button7 = findViewById(R.id.bt_7);
        Button button8 = findViewById(R.id.bt_8);
        button9=findViewById(R.id.bt_9);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        scord1=Integer.parseInt(textView1.getText().toString());
        scord2=Integer.parseInt(textView2.getText().toString());
        scord3=Integer.parseInt(textView3.getText().toString());
        scord4=Integer.parseInt(textView4.getText().toString());
        scord5=Integer.parseInt(textView5.getText().toString());
        MyBaseAdapter mAdapter=new MyBaseAdapter();
        mListView.setAdapter(mAdapter);
    }
    public void onClick(View v) {
        String str;
        switch (v.getId()) {
            case R.id.bt_1:
                if (scord1 > 0) {
                    scord1 = scord1 - 1;
                    scord5 = scord5 + 1;
                    str = String.valueOf(scord1);
                    textView1.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_2:
                if (scord5 > 0) {
                    scord1 = scord1 + 1;
                    scord5 = scord5 - 1;
                    str = String.valueOf(scord1);
                    textView1.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_3:
                if (scord2 > 0) {
                    scord2 = scord2 - 1;
                    scord5 = scord5 + 1;
                    str = String.valueOf(scord2);
                    textView2.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_4:
                if (scord5 > 0) {
                    scord2 = scord2 + 1;
                    scord5 = scord5 - 1;
                    str = String.valueOf(scord2);
                    textView2.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_5:
                if (scord3 > 0) {
                    scord3 = scord3 - 1;
                    scord5 = scord5 + 1;
                    str = String.valueOf(scord3);
                    textView3.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_6:
                if (scord5 > 0) {
                    scord3 = scord3 + 1;
                    scord5 = scord5 - 1;
                    str = String.valueOf(scord3);
                    textView3.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_7:
                if (scord4 > 0) {
                    scord4 = scord4 - 1;
                    scord5 = scord5 + 1;
                    str = String.valueOf(scord4);
                    textView4.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_8:
                if (scord5 > 0) {
                    scord4 = scord4 + 1;
                    scord5 = scord5 - 1;
                    str = String.valueOf(scord4);
                    textView4.setText(str);
                    str = String.valueOf(scord5);
                    textView5.setText(str);
                }
                break;
            case R.id.bt_9:
                if (scord5 != 0)
                    Toast.makeText(CreateActivity.this, "点数未分配完", Toast.LENGTH_SHORT).show();
                else if (editText.getText().toString().equals(""))
                    Toast.makeText(CreateActivity.this, "无昵称", Toast.LENGTH_SHORT).show();
                else {
                    SQLiteDatabase db = myHelper.getWritableDatabase();
                    db.delete("shuxing",null,null);
                    db.delete("yingxiong",null,null);
                    db.delete("jineng",null,null);
                    db.delete("juqing",null,null);
                    db.execSQL("update sqlite_sequence set seq=0 where name='shuxing'");
                    db.execSQL("update sqlite_sequence set seq=0 where name='yingxiong'");
                    db.execSQL("update sqlite_sequence set seq=0 where name='jineng'");
                    db.execSQL("update sqlite_sequence set seq=0 where name='juqing'");
                    ContentValues values1 = new ContentValues();
                    ContentValues values2 = new ContentValues();
                    ContentValues values3 = new ContentValues();
                    ContentValues values4 = new ContentValues();
                    ContentValues values5 = new ContentValues();
                    ContentValues values6 = new ContentValues();
                    ContentValues values7 = new ContentValues();
                    switch(id) {
                        case R.drawable.jialuo1:
                            values1.put("number", R.drawable.jialuo2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.jl);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.jl1);
                            values4.put("name",R.drawable.jl2);
                            values5.put("name",R.drawable.jl3);
                            break;
                        case R.drawable.kai1:
                            values1.put("number", R.drawable.kai2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.k);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.k1);
                            values4.put("name",R.drawable.k2);
                            values5.put("name",R.drawable.k3);
                            break;
                        case R.drawable.libai1:
                            values1.put("number", R.drawable.libai2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.lb);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.lb1);
                            values4.put("name",R.drawable.lb2);
                            values5.put("name",R.drawable.lb3);
                            break;
                        case R.drawable.lixin1:
                            values1.put("number", R.drawable.lixin2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.lx);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.lx1);
                            values4.put("name",R.drawable.lx2);
                            values5.put("name",R.drawable.lx3);
                            break;
                        case R.drawable.yingzheng1:
                            values1.put("number", R.drawable.yingzheng2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.yz);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.yz1);
                            values4.put("name",R.drawable.yz2);
                            values5.put("name",R.drawable.yz3);
                            break;
                        case R.drawable.yao1:
                            values1.put("number", R.drawable.yao2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.y);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.y1);
                            values4.put("name",R.drawable.y2);
                            values5.put("name",R.drawable.y3);
                            break;
                        case R.drawable.yase1:
                            values1.put("number", R.drawable.yase2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.ys);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.ys1);
                            values4.put("name",R.drawable.ys2);
                            values5.put("name",R.drawable.ys3);
                            break;
                        case R.drawable.sunshangxiang1:
                            values1.put("number", R.drawable.sunshangxiang2);
                            db.insert("yingxiong", null, values1);
                            values6.put("number", R.drawable.ssx);
                            db.insert("yingxiong", null, values6);
                            values3.put("name",R.drawable.ssx1);
                            values4.put("name",R.drawable.ssx2);
                            values5.put("name",R.drawable.ssx3);
                            break;
                    }
                    values3.put("shuxing", "造成两倍伤害\n冷却3回合");
                    values3.put("dengji", 1);
                    values3.put("jingyan", 0);
                    values4.put("shuxing", "造成两倍伤害并50%吸血\n冷却4回合");
                    values4.put("dengji", 1);
                    values4.put("jingyan", 0);
                    values5.put("shuxing", "造成三倍伤害\n冷却5回合");
                    values5.put("dengji", 1);
                    values5.put("jingyan", 0);
                    values2.put("name", editText.getText().toString());
                    num1=textView1.getText().toString();
                    num2=textView2.getText().toString();
                    num3=textView3.getText().toString();
                    num4=textView4.getText().toString();
                    values2.put("shengming", Integer.valueOf(num1)*100+1000 );
                    values2.put("gongji", Integer.valueOf(num2) *50+500);
                    values2.put("fangyu", Integer.valueOf(num1) *30+300);
                    values2.put("yisu", Integer.valueOf(num3)*10+100);
                    values2.put("gongsu", Integer.valueOf(num3) *10+100);
                    values2.put("baoji", Integer.valueOf(num4) );
                    values2.put("jinqian", 1000);
                    values2.put("dengji",1);
                    values2.put("xueliang",Integer.valueOf(num1)*100+1000);
                    values7.put("jindu",1);
                    db.insert("shuxing", null, values2);
                    db.insert("jineng", null, values3);
                    db.insert("jineng", null, values4);
                    db.insert("jineng", null, values5);
                    db.insert("juqing", null, values7);
                    db.close();
                    button9.setTextColor(Color.parseColor("#00BCD4"));
                    Intent intent1 = new Intent(CreateActivity.this, StoryActivity.class);
                    startActivity(intent1);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                break;
        }
    }
    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return icons1.length;
        }

        @Override
        public Object getItem(int position) {
            return icons1[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(
                        getApplicationContext()).inflate(R.layout.list_item, parent,
                        false);
                holder = new ViewHolder();
                holder.imageView1 = (ImageView) convertView.findViewById(R.id.item_image1);
                holder.imageView2 = (ImageView) convertView.findViewById(R.id.item_image2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imageView1.setBackgroundResource(icons1[position]);
            holder.imageView2.setBackgroundResource(icons2[position]);
            holder.imageView1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    imageView.setBackgroundResource(icons1[position]);
                    id=icons1[position];
                }
            });
            holder.imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    imageView.setBackgroundResource(icons2[position]);
                    id=icons2[position];
                }
            });
            return convertView;
        }
        class ViewHolder {
            ImageView imageView1;
            ImageView imageView2;
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
    static class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context){
            super(context,"character1.db",null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE yingxiong(_id INTEGER PRIMARY KEY AUTOINCREMENT,number INTEGER)");
            db.execSQL("CREATE TABLE shuxing(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),shengming INTEGER,gongji INTEGER,fangyu INTEGER,yisu INTEGER,gongsu INTEGER,baoji INTEGER,jinqian INTEGER,dengji INTEGER,xueliang INTEGER)");
            db.execSQL("CREATE TABLE jineng(_id INTEGER PRIMARY KEY AUTOINCREMENT,name INTEGER,dengji INTEGER,shuxing VARCHAR(20),jingyan INTEGER)");
            db.execSQL("CREATE TABLE juqing(_id INTEGER PRIMARY KEY AUTOINCREMENT,jindu INTEGER)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
    }



}
