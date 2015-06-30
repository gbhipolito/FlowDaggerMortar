package com.lancer.flowdaggermortar.squaresupport;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.lancer.flowdaggermortar.squaresupport.Screen;

import flow.path.Path;
import flow.path.PathContextFactory;
import mortar.MortarScope;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

public class DemoScreenContextFactory implements PathContextFactory {

    private ScreenScopeContext screenScopeContext;

    @Override
    public Context setUpContext(Path path, Context parentContext) {
        Log.e(TAG, START + "DemoScreenContextFactory setUpContext: " + path.getClass().getName() + " " + parentContext.getClass().getName());
        screenScopeContext = new ScreenScopeContext(parentContext, path);
        return screenScopeContext;
    } // end setUpContext

    @Override
    public void tearDownContext(Context context) {
        Log.e(TAG, START + "DemoScreenContextFactory tearDownContext: " + context.getClass().getName());
        if(screenScopeContext != null) {
            screenScopeContext.destroyScope();
            screenScopeContext = null;
        }
    } // end tearDownContext

    private class ScreenScopeContext extends ContextWrapper {
        private MortarScope parentScope;
        private MortarScope screenScope;
        private LayoutInflater inflater;

        public ScreenScopeContext(Context parentContext, Path path) {
            super(parentContext);
            Log.e(TAG, START + "ScreenScopeContext constructor");

            parentScope = MortarScope.getScope(parentContext);
            String scopeName = getClass().getName();
            screenScope = parentScope.findChild(scopeName);
            if(screenScope == null) {
                Log.e(TAG, START + "ScreenScopeContext build screen scope");
                String presenterName = ((Screen)path).getPresenterName();
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