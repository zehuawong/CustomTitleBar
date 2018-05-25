package com.wang.customtitlebar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {
    final private static String TAG="MainActivity";

    private TopTitleBar topEditBar;
    private TopTitleBar topChooseAllBar;
    private TopTitleBar topCancelAllBar;

    MyHandler mhandler;

    private static class MyHandler extends Handler {
        WeakReference<MainActivity> weakReference;
        public MyHandler(MainActivity mainActivity){
            weakReference=new WeakReference<>(mainActivity);
        }
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                MainActivity mainActivity=weakReference.get();
                if(mainActivity==null)
                    return;
                Toast.makeText(mainActivity,"消息：点击了back按钮",Toast.LENGTH_SHORT).show();
            }else if(msg.what==2){
                MainActivity mainActivity=weakReference.get();
                if(mainActivity==null)
                    return;
                Toast.makeText(mainActivity,"消息：点击了编辑按钮",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mhandler=new MyHandler(this);

        initView();
    }

    private void  initView(){
        topEditBar=findViewById(R.id.topEditBar);
        topEditBar.setOnLeftAndRightClickListener(new TopTitleBar.OnLeftAndRightClickListener() {
            @Override
            public void onLeftButtonClick() {   //处理左边按钮点击事件-返回
                Message message=Message.obtain();
                message.what=1;
                mhandler.sendMessage(message);
                Log.d(TAG, "onLeftButtonClick: ");
            }

            @Override
            public void onRightButtonClick() { //处理右边按钮点击事件-编辑
                Message message=Message.obtain();
                message.what=2;
                mhandler.sendMessage(message);
                topEditBar.setVisibility(View.GONE);
                topChooseAllBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "onRightButtonClick: ");

            }
        });

        topChooseAllBar=findViewById(R.id.topChooseAllBar);
        topChooseAllBar.setOnLeftAndRightClickListener(new TopTitleBar.OnLeftAndRightClickListener() {
            @Override
            public void onLeftButtonClick() {   //处理左边按钮点击事件-取消
                topChooseAllBar.setVisibility(View.GONE);
                topEditBar.setVisibility(View.VISIBLE);

                Log.d(TAG, "onLeftButtonClick: ");
            }

            @Override
            public void onRightButtonClick() { //处理右边按钮点击事件-全选
                topChooseAllBar.setVisibility(View.GONE);
                topCancelAllBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "onRightButtonClick: ");

            }
        });

        topCancelAllBar=findViewById(R.id.topCancelAllBar);
        topCancelAllBar.setOnLeftAndRightClickListener(new TopTitleBar.OnLeftAndRightClickListener() {
            @Override
            public void onLeftButtonClick() {   //处理左边按钮点击事件-取消
                topCancelAllBar.setVisibility(View.GONE);
                topChooseAllBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "onLeftButtonClick: ");
            }

            @Override
            public void onRightButtonClick() { //处理右边按钮点击事件-取消全选
                topCancelAllBar.setVisibility(View.GONE);
                topChooseAllBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "onRightButtonClick: ");

            }
        });

    }


}
