package com.lancer.flowdaggermortar;

import dagger.Module;

// not used yet
@Module(
        injects = DemoApplication.class
        )
public class DemoApplicationModule {
// used in creating ObjectGraphService
// doesn't inject anything to DemoApplication yet, just made ready, in case needed
// also it is used as the root module/root object graph


} // end DemoApplicationModule