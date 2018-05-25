package com.wang.customtitlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopTitleBar extends RelativeLayout{

    final private static String TAG="TopTitleBar";

    private Button leftButton;
    private Button rightButton;
    private TextView titleTextView;

    private OnLeftAndRightClickListener listener;//监听点击事件

    //按钮点击接口回调
    public interface OnLeftAndRightClickListener {
        void onLeftButtonClick();
        void onRightButtonClick();
    }


    public TopTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

       // LayoutInflater.from(context).inflate(R.layout.layout_toptitlebar,this,false);
        LayoutInflater.from(context).inflate(R.layout.layout_toptitlebar,this );
        leftButton=(Button)findViewById(R.id.leftButton);
        rightButton=(Button)findViewById(R.id.rightButton);
        titleTextView=(TextView)findViewById(R.id.titleText);
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.d(TAG, "onClick: leftbutton");
                if(listener!=null){
                    listener.onLeftButtonClick();
                }
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: rightbutton");

                if(listener!=null){
                    listener.onRightButtonClick();
                }
            }
        });

        //获得自定义属性并赋值,tip ：name_attrname,如TopTitleBar_leftBackground
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.TopTitleBar);
        int leftBtnBackground = typeArray.getResourceId(R.styleable.TopTitleBar_leftBackground, 0);
        int rightBtnBackground = typeArray.getResourceId(R.styleable.TopTitleBar_rightBackground, 0);
        String titleText = typeArray.getString(R.styleable.TopTitleBar_titleText);
        float titleTextSize = typeArray.getDimension(R.styleable.TopTitleBar_titleTextSize, 10);
        int titleTextColor = typeArray.getColor(R.styleable.TopTitleBar_titleTextColor, 0x38ad5a);

        float buttonTextSize=typeArray.getDimension(R.styleable.TopTitleBar_buttonTextSize,6);
        String leftButtonText=typeArray.getString(R.styleable.TopTitleBar_leftButtonText);
        String rightButtonText=typeArray.getString(R.styleable.TopTitleBar_rightButtonText);


        int buttonTextColor = typeArray.getColor(R.styleable.TopTitleBar_buttonTextColor, 0x38ad5a);


        //释放资源
        typeArray.recycle();

        leftButton.setBackgroundResource(leftBtnBackground);
        rightButton.setBackgroundResource(rightBtnBackground);
        leftButton.setText(leftButtonText);
        rightButton.setText(rightButtonText);
        leftButton.setTextSize(buttonTextSize);
        rightButton.setTextSize(buttonTextSize);
        leftButton.setTextColor(buttonTextColor);
        rightButton.setTextColor(buttonTextColor);

        titleTextView.setText(titleText);
        titleTextView.setTextSize(titleTextSize);
        titleTextView.setTextColor(titleTextColor);


    }

    //设置监听器
    public void setOnLeftAndRightClickListener(OnLeftAndRightClickListener listener) {
        this.listener = listener;
    }

    //设置标题
    private void setTitleText(String titleText){
        titleTextView.setText(titleText);
    }


}
