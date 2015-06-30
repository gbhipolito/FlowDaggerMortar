package com.lancer.flowdaggermortar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.lancer.flowdaggermortar.DemoApplication;
import com.lancer.flowdaggermortar.presenters.DemoButtonPresenter;
import com.lancer.flowdaggermortar.screens.DemoButtonScreen;

import javax.inject.Inject;

import dagger.ObjectGraph;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

public class DemoButtonView extends LinearLayout {
    // used LinearLayout because PathContainerView's getCurrentChild requires us to return a ViewGroup
    // so cannot use a Button directly, hopefully this will be updated to accept regular Views

    DemoButtonPresenter presenter;

    public DemoButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, START + "DemoButtonView constructor context attrs");
        presenter = (DemoButtonPresenter)context.getSystemService(DemoButtonPresenter.class.getName());
        Log.e(TAG, START + "DemoButtonView presenter instance: " + presenter);
    }

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