package com.lancer.flowdaggermortar.squaresupport;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;


import flow.path.Path;
import flow.path.PathContextFactory;
import mortar.MortarScope;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

public class DemoScreenContextFactory implements PathContextFactory {

    @Override
    public Context setUpContext(Path path, Context parentContext) {
        Log.e(TAG, START + "DemoScreenContextFactory setUpContext: " + path.getClass().getName() + " " + parentContext.getClass().getName());
        // create a mortar scope & context, scoped only to a single screen
        return new ScreenScopeContext(parentContext, path);
    } // end setUpContext

    @Override
    public void tearDownContext(Context context) {
        // this method is only called when going to another screen, not in onDestroy / config change
        // w/c means we can use this to destroy objects that we want destroyed when we change screen but survive on config change
        // the context provided here is the same context that we returned in setUpContext (or clone?)
        Log.e(TAG, START + "DemoScreenContextFactory tearDownContext: " + context.getClass().getName());
        ScreenScopeContext screenScopeContext = (ScreenScopeContext)context;
        screenScopeContext.destroyScope();
        screenScopeContext = null;
    } // end tearDownContext

    private class ScreenScopeContext extends ContextWrapper {
        private MortarScope parentScope;
        private MortarScope screenScope;
        private LayoutInflater inflater;

        public ScreenScopeContext(Context parentContext, Path path) {
            super(parentContext);
            Log.e(TAG, START + "ScreenScopeContext constructor");

            parentScope = MortarScope.getScope(parentContext);
            String presenterName = ((Screen)path).getPresenterName();
            String scopeName = getClass().getName() + presenterName;
            screenScope = parentScope.findChild(scopeName);
            if(screenScope == null) {
                Log.e(TAG, START + "ScreenScopeContext build screen scope");
                try {
                    screenScope = parentScope.buildChild()
                            .withService(presenterName, Class.forName(presenterName).newInstance())
                            .build(scopeName);
                } catch (Exception e) {
                    Toast.makeText(parentContext, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        } // end ScreenScopeContext constructor

        public void destroyScope() {
            // destroy this scope (included objects that it provides, specially the presenter) because we will change screen
            if(screenScope != null) {
                Log.e(TAG, START + "ScreenScopeContext destroy screen scope");
                screenScope.destroy();
                screenScope = null;
            }
        }

        @Override
        public Object getSystemService(String name) {
            Log.e(TAG, START + "ScreenScopeContext screen scope service requested: " + name);
            if (name.equals(LAYOUT_INFLATER_SERVICE)) {
                if (inflater == null) {
                    inflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
                }
                Log.e(TAG, START + "ScreenScopeContext return screen scope inflater: " + inflater);
                return inflater;
            }

//            if (SERVICE.equals(name)) {
//                return parentScope;
//            }

            if(screenScope != null && screenScope.hasService(name)) {
                Log.e(TAG, START + "ScreenScopeContext return screen scope mortar service: " + name);
                return screenScope.getService(name); // if mortar service requested
            }

            Log.e(TAG, START + "ScreenScopeContext return super service");
            return super.getSystemService(name);
        } // end getSystemService
    } // end ScreenScopeContext
} // end DemoScreenContextFactory