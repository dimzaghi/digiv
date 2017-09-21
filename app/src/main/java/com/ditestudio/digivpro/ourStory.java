package com.ditestudio.digivpro;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ourStory extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.pic01,R.drawable.pic02,R.drawable.pic03,R.drawable.pic04,R.drawable.pic05,R.drawable.pic06,R.drawable.pic07,R.drawable.pic08};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_story);

        ImageButton backButton1 = (ImageButton) findViewById(R.id.backButton);

        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ourStory.this,MainActivity.class);
                startActivity(newIntent);
            }
        });

        init();
    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(ourStory.this,XMENArray));
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

}

//public class ourStory extends AppCompatActivity {
//
//    int mFlipping = 0 ; // Initially flipping is off
//    Button mButton ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_our_story);
//
//        ImageButton backButton1 = (ImageButton) findViewById(R.id.backButton);
//
//        backButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent newIntent = new Intent(ourStory.this,MainActivity.class);
//                startActivity(newIntent);
//            }
//        });
//
//        /** Click event handler for button */
//        OnClickListener listener = new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
//
//                if(mFlipping==0){
//                    /** Start Flipping */
//                    flipper.startFlipping();
//                    mFlipping=1;
//                    mButton.setText(R.string.str_btn_stop);
//                }
//                else{
//                    /** Stop Flipping */
//                    flipper.stopFlipping();
//                    mFlipping=0;
//                    mButton.setText(R.string.str_btn_start);
//                }
//            }
//        };
//        /** Getting a reference to the button available in the resource */
//        mButton = (Button) findViewById(R.id.btn);
//
//        /** Setting click event listner for the button */
//        mButton.setOnClickListener(listener);
//
//    }
//}