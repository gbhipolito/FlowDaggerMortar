package com.lancer.flowdaggermortar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lancer.flowdaggermortar.R;
import com.lancer.flowdaggermortar.presenters.DemoButtonPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

public class DemoButtonView extends LinearLayout {
    // used LinearLayout because PathContainerView's getCurrentChild requires us to return a ViewGroup
    // so cannot use a Button directly, hopefully this will be updated to accept regular Views

    private DemoButtonPresenter presenter;

    // use butterknife to bind the button from xml to java instead of findViewById
    @Bind(R.id.button)
    Button demoButton;

    public DemoButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, START + "DemoButtonView constructor context attrs");
        presenter = (DemoButtonPresenter)context.getSystemService(DemoButtonPresenter.class.getName());
        Log.e(TAG, START + "DemoButtonView presenter instance: " + presenter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        demoButton = (Button)findViewById(R.id.button);
        ButterKnife.bind(this); // where binding happens
        demoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDemoButtonClicked(v);
            }
        });
    } // end onFinishInflate

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, START + "DemoButtonView onAttachedToWindow");
        Log.e(TAG, START + "DemoButtonView presenter take view");
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.e(TAG, START + "DemoButtonView presenter drop view");
        presenter.dropView(this);
        super.onDetachedFromWindow();
        Log.e(TAG, START + "DemoButtonView onDetachedFromWindow");
    }
} // end DemoButtonView