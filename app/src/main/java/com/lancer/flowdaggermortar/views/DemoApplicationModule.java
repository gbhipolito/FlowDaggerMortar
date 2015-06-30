package com.lancer.flowdaggermortar.views;

import com.lancer.flowdaggermortar.DemoApplication;
import com.lancer.flowdaggermortar.presenters.DemoButtonPresenter;
import com.lancer.flowdaggermortar.screens.DemoButtonScreen;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = DemoApplication.class
//        includes = DemoButtonScreen.DemoButtonModule.class
        )
public class DemoApplicationModule {
// used in creating ObjectGraphService
// doesn't inject anything to DemoApplication yet, just made ready, in case needed
// also it is used as the root module/root object graph


} // end DemoApplicationModule