package com.lancer.flowdaggermortar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.lancer.flowdaggermortar.presenters.DemoTextPresenter;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

public class DemoTextView extends LinearLayout {

    DemoTextPresenter presenter;

    public DemoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, START + "DemoTextView constructor context attrs");
        presenter = (DemoTextPresenter)context.getSystemService(DemoTextPresenter.class.getName());
        Log.e(TAG, START + "DemoTextView presenter instance: " + presenter);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, START + "DemoTextView onAttachedToWindow");
        Log.e(TAG, START + "DemoTextView presenter take view");
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.e(TAG, START + "DemoTextView presenter drop view");
        presenter.dropView(this);
        super.onDetachedFromWindow();
        Log.e(TAG, START + "DemoTextView onDetachedFromWindow");
    }
} // end DemoTextView