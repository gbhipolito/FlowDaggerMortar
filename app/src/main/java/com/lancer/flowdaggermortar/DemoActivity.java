package com.lancer.flowdaggermortar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import com.lancer.flowdaggermortar.presenters.DemoButtonPresenter;
import com.lancer.flowdaggermortar.screens.DemoButtonScreen;
import com.lancer.flowdaggermortar.squaresupport.GsonParceler;
import com.lancer.flowdaggermortar.squaresupport.HandlesBack;

import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.path.PathContainerView;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

import static com.lancer.flowdaggermortar.DemoApplication.TAG;
import static com.lancer.flowdaggermortar.DemoApplication.START;

public class DemoActivity extends Activity implements Flow.Dispatcher {
    private MortarScope activityScope;
    private PathContainerView container;
    private HandlesBack containerHandlesBack;
    private FlowDelegate flowDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, START + "DemoActivity onCreate");

        // parceler and nonConfig to be used by flow, for handling config changes
        GsonParceler parceler = new GsonParceler(new Gson());
        @SuppressWarnings("deprecation")
        FlowDelegate.NonConfigurationInstance nonConfig = (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();

        // check if mortar (Activity)scoped object graph exists, if not, make one
        // an Activity's parent scope is an Application scope
        MortarScope parentScope = MortarScope.getScope(getApplicationContext());
        String scopeName = getClass().getName();
        activityScope = parentScope.findChild(scopeName);
        if(activityScope == null) {
            activityScope = parentScope.buildChild()
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DemoButtonPresenter.class.getName(), new DemoButtonPresenter())
                    .build(scopeName);
        }
//        ObjectGraphService.inject(activityScope, this);
        BundleServiceRunner.getBundleServiceRunner(activityScope).onCreate(savedInstanceState);

        Log.e(TAG, START + "DemoActivity setContentView");
        setContentView(R.layout.activity_demo);
        container = (PathContainerView)findViewById(R.id.container);
        containerHandlesBack = (HandlesBack)container;
        Log.e(TAG, START + "DemoActivity create flow delegate");
        // create flow delegate w/ initial screen and hook to stuff related to activity's lifecycle and config changes
        flowDelegate = FlowDelegate.onCreate(nonConfig, getIntent(), savedInstanceState, parceler, History.single(new DemoButtonScreen()), this);
    } // end onCreate

    @Override
    public void dispatch(Flow.Traversal traversal, Flow.TraversalCallback callback) {
        Log.e(TAG, START + "DemoActivity dispatch");
//        Path newScreen = traversal.destination.top();
        container.dispatch(traversal, callback);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(TAG, START + "DemoActivity onNewIntent");
        super.onNewIntent(intent);
        flowDelegate.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        Log.e(TAG, START + "DemoActivity onResume");
        super.onResume();
        flowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, START + "DemoActivity onPause");
        flowDelegate.onPause();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, START + "DemoActivity onSaveInstanceState");
        super.onSaveInstanceState(outState);
        flowDelegate.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(activityScope).onSaveInstanceState(outState);
    }

    @SuppressWarnings("deprecation") // https://code.google.com/p/android/issues/detail?id=151346
    @Override
    public Object onRetainNonConfigurationInstance() {
        Log.e(TAG, START + "DemoActivity onRetainNonConfigurationInstance");
        return flowDelegate.onRetainNonConfigurationInstance();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, START + "DemoActivity onDestroy");
        // destroy (Activity)scoped object graph
        if(isFinishing() && activityScope != null) {
            Log.e(TAG, START + "DemoActivity destroy activity scope");
            activityScope.destroy();
            activityScope = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, START + "DemoActivity onBackPressed");
        if (containerHandlesBack.onBackPressed()) {
            Log.e(TAG, START + "DemoActivity container handled back");
            return;
        }
        if (flowDelegate.onBackPressed()) {
            Log.e(TAG, START + "DemoActivity flow handled back");
            return;
        }

        Log.e(TAG, START + "DemoActivity super handled back");
        super.onBackPressed();
    }

    @Override
    public Object getSystemService(String name) {
        Log.e(TAG, START + "DemoActivity getSystemService: " + name);
        // overriden for easy access of flow and mortar services, including
        // services added in mortar's scope (e.g. BundleServiceRunner)
        // something like a dependency injection, using Context to hide the dependencies/services
        // this is how flow and mortar get the services they need
        if(flowDelegate != null) {
            Object flowService = flowDelegate.getSystemService(name);
            if(flowService != null) {
                Log.e(TAG, START + "DemoActivity return flow service");
                return flowService; // if flow service requested
            }
        }

        if(activityScope != null && activityScope.hasService(name)) {
            Log.e(TAG, START + "DemoActivity return activityscope mortar service");
            return activityScope.getService(name); // if mortar service requested
        }

        Log.e(TAG, START + "DemoActivity return super's service");
        return super.getSystemService(name); // activity service request  last resort
    } // end getSystemService
} // end DemoActivity