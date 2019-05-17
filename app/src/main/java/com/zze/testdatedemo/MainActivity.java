package com.zze.testdatedemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private boolean isFirst=true;
    private EditText et_test;
    private SharedPreferences sp;
    private final static String IS_FIRST_RUN="is_first_run";
    private final static String FIRST_DATE="first_date";
    private final static String LOG_LOOK="log_look";
    private Long oldTime;
    private Long currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSp();
        initDate();
        getSp();
        setSp();
    }

    private void initView() {
        et_test=findViewById(R.id.et_test);
    }

    private void initSp() {
        sp=getSharedPreferences("sp_name",MODE_PRIVATE);
    }

    private void initDate() {
        currentTime=System.currentTimeMillis();
        Log.i(LOG_LOOK,"currentTime==>"+currentTime);
    }

    private void getSp() {
        isFirst=sp.getBoolean(IS_FIRST_RUN,true);
        oldTime=sp.getLong(FIRST_DATE,0);
        Log.i(LOG_LOOK,"isFirst==>"+isFirst);
        Log.i(LOG_LOOK,"oldTime==>"+oldTime);
    }

    private void setSp() {
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(IS_FIRST_RUN,false);
        editor.putLong(FIRST_DATE,currentTime);
        editor.commit();
    }

    public void test(View view){
        if (isFirst){
            et_test.setText("欢迎初次使用");
        }else {
            if(Math.abs(((currentTime - oldTime)/(24*3600*1000))) <3){
                et_test.setText("欢迎经常使用");
            }else {
                et_test.setText("好久不见，欢迎再次使用");
            }
        }
    }
}
