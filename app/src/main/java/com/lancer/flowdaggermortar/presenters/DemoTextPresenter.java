package com.lancer.flowdaggermortar.presenters;

import android.os.Bundle;
import android.util.Log;

import com.lancer.flowdaggermortar.views.DemoButtonView;
import com.lancer.flowdaggermortar.views.DemoTextView;

import javax.inject.Singleton;

import mortar.MortarScope;
import mortar.ViewPresenter;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

@Singleton
public class DemoTextPresenter extends ViewPresenter<DemoTextView> {

    @Override
    protected void onEnterScope(MortarScope scope) {
        Log.e(TAG, START + "DemoTextPresenter onEnterScope");
    }

    @Override
    protected void onSave(Bundle outState) {
        Log.e(TAG, START + "DemoTextPresenter onSave");
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        Log.e(TAG, START + "DemoTextPresenter onLoad");
    }

    @Override
    protected void onExitScope() {
        Log.e(TAG, START + "DemoTextPresenter onExitScope");
    }

} // end DemoTextPresenter