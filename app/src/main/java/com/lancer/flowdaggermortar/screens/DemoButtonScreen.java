package com.lancer.flowdaggermortar.screens;

import android.util.Log;

import com.lancer.flowdaggermortar.R;
import com.lancer.flowdaggermortar.presenters.DemoButtonPresenter;
import com.lancer.flowdaggermortar.squaresupport.Layout;
import com.lancer.flowdaggermortar.squaresupport.Screen;
import com.lancer.flowdaggermortar.views.DemoApplicationModule;
import com.lancer.flowdaggermortar.views.DemoButtonView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flow.path.Path;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

@Layout(R.layout.demo_btn_view)
public class DemoButtonScreen extends Screen {
// a screen just extends Screen

    public DemoButtonScreen() {
        super();
        Log.e(TAG, START + "DemoButtonScreen constructor: " + this);
    }

    @Override
    public String getPresenterName() {
        return DemoButtonPresenter.class.getName();
    }

    //
//    @Module(
//            injects = DemoButtonView.class,
//            addsTo = DemoApplicationModule.class
//            )
//    public static class DemoButtonModule {
//        @Provides
//        @Singleton
//        public DemoButtonPresenter providePresenter() {
//            Log.e(TAG, START + "DemoButtonModule providePresenter");
//            // provide presenter in this screen so that when this screen is removed, so shall the presenter
//            return new DemoButtonPresenter();
//        }
//
//    } // end DemoButtonModule

} // end DemoButtonScreen