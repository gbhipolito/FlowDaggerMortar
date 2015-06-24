/*
 * Copyright 2014 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lancer.flowdaggermortar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lancer.flowdaggermortar.R;
import com.lancer.flowdaggermortar.squaresupport.BackSupport;
import com.lancer.flowdaggermortar.squaresupport.HandlesBack;
import com.lancer.flowdaggermortar.squaresupport.SimpleScreenContainer;
import flow.Flow;
import flow.path.Path;
import flow.path.PathContainer;
import flow.path.PathContainerView;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;

/** A FrameLayout that can show screens for a {@link flow.Flow}. */
public class DemoScreensContainerView extends FrameLayout
    implements HandlesBack, PathContainerView {
  private final PathContainer container;
  private boolean disabled;

  @SuppressWarnings("UnusedDeclaration") // Used by layout inflation, of course!
  public DemoScreensContainerView(Context context, AttributeSet attrs) {
    this(context, attrs, new SimpleScreenContainer(R.id.screen_switcher_tag, Path.contextFactory()));
    Log.e(TAG, START + "DemoScreensContainerView constructor context attrs"); // should be called before the this constructor to avoid confusion, but error
  }

  /**
   * Allows subclasses to use custom {@link flow.path.PathContainer} implementations. Allows the use
   * of more sophisticated transition schemes, and customized context wrappers.
   */
  protected DemoScreensContainerView(Context context, AttributeSet attrs, PathContainer container) {
    super(context, attrs);
    Log.e(TAG, START + "DemoScreensContainerView constructor context attrs container");
    this.container = container;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    Log.e(TAG, START + "DemoScreensContainerView dispatchTouchEvent");
    return !disabled && super.dispatchTouchEvent(ev);
  }

  /**
   * Overriden from {@link PathContainerView}
   */
  @Override
  public ViewGroup getCurrentChild() {
    Log.e(TAG, START + "DemoScreensContainerView getCurrentChild");
    return (ViewGroup) getContainerView().getChildAt(0);
  }

  /**
   * Overriden from {@link PathContainerView}
   */
  @Override
  public ViewGroup getContainerView() {
    Log.e(TAG, START + "DemoScreensContainerView getContainerView");
    return this;
  }

  /**
   * Overriden from {@link PathContainerView}
   */
  @Override
  public void dispatch(Flow.Traversal traversal, final Flow.TraversalCallback callback) {
    Log.e(TAG, START + "DemoScreensContainerView dispatch");
    disabled = true;
    container.executeTraversal(this, traversal, new Flow.TraversalCallback() {
      @Override public void onTraversalCompleted() {
        Log.e(TAG, START + "DemoScreensContainerView container onTraversalCompleted");
        callback.onTraversalCompleted();
        disabled = false;
      }
    });
  }

  /**
   * Overriden from {@link HandlesBack}
   */
  @Override
  public boolean onBackPressed() {
    Log.e(TAG, START + "DemoScreensContainerView onBackPressed");
    return BackSupport.onBackPressed(getCurrentChild());
  }
} // end FrameScreenContainerView