package com.lancer.flowdaggermortar.screens;

import android.util.Log;

import com.lancer.flowdaggermortar.R;
import com.lancer.flowdaggermortar.presenters.DemoButtonPresenter;
import com.lancer.flowdaggermortar.presenters.DemoTextPresenter;
import com.lancer.flowdaggermortar.squaresupport.Layout;
import com.lancer.flowdaggermortar.squaresupport.Screen;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

@Layout(R.layout.demo_txt_view)
public class DemoTextScreen extends Screen {
// a screen just extends Screen and has annotation of what view to use

    public DemoTextScreen() {
        super();
        Log.e(TAG, START + "DemoTextScreen constructor: " + this);
    }

    @Override
    public String getPresenterName() {
        return DemoTextPresenter.class.getName();
    }


} // end DemoTextScreen