package com.lancer.flowdaggermortar.presenters;

import android.os.Bundle;
import android.util.Log;

import com.lancer.flowdaggermortar.views.DemoButtonView;
import mortar.MortarScope;
import mortar.ViewPresenter;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

public class DemoButtonPresenter extends ViewPresenter<DemoButtonView> {

    @Override
    protected void onEnterScope(MortarScope scope) {
        Log.e(TAG, START + "DemoButtonPresenter onEnterScope");
    }

    @Override
    protected void onSave(Bundle outState) {
        Log.e(TAG, START + "DemoButtonPresenter onSave");
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        Log.e(TAG, START + "DemoButtonPresenter onLoad");
    }

    @Override
    protected void onExitScope() {
        Log.e(TAG, START + "DemoButtonPresenter onExitScope");
    }
} // end DemoButtonPresenter