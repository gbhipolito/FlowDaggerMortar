package com.lancer.flowdaggermortar;

import android.app.Application;
import android.util.Log;

import mortar.MortarScope;

public class DemoApplication extends Application {
    public static final String TAG = "FlowDaggerMortar";
    public static final String START = "^^^^^>>>";
    public static final String APPOBJGRAPH = "ApplicationObjectGraph";

    private MortarScope applicationScope;

    @Override
    public Object getSystemService(String name) {
        // see comment on Activity's getSystemService
        Log.e(TAG, START + "DemoApplication getSystemService: " + name);

//        // create dagger object graph
//        ObjectGraph objectGraph = ObjectGraph.create(new DemoApplicationModule());
//        objectGraph.inject(this);

        // check if mortar (Application)scoped object graph exists, if not, make one
        if(applicationScope == null) {
            Log.e(TAG, START + "DemoApplication build root scope");
            applicationScope = MortarScope.buildRootScope()
//                    .withService(ObjectGraphService.SERVICE_NAME, ObjectGraph.create(new DemoApplicationModule()))
//                    .withService(APPOBJGRAPH, objectGraph)
                    .build(getClass().getName());
        }

        if(applicationScope.hasService(name)) {
            Log.e(TAG, START + "DemoApplication return applicationscope mortar service");
            return applicationScope.getService(name); // if mortar service requested
        }

        Log.e(TAG, START + "DemoApplication return super's service");
        return super.getSystemService(name); // application service request last resort
    }
} // end DemoApplication