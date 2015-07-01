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

package com.lancer.flowdaggermortar.squaresupport;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.Map;

import flow.Flow;
import flow.path.Path;
import flow.path.PathContainer;
import flow.path.PathContext;
import flow.path.PathContextFactory;

import static com.lancer.flowdaggermortar.DemoApplication.START;
import static com.lancer.flowdaggermortar.DemoApplication.TAG;
import static flow.Flow.Direction.REPLACE;

/**
 * Provides basic right-to-left transitions. Saves and restores view state.
 * Uses {@link PathContext} to allow customized sub-containers.
 */
public class SimpleScreenContainer extends PathContainer {
  private static final Map<Class, Integer> PATH_LAYOUT_CACHE = new LinkedHashMap<>();
  private final PathContextFactory contextFactory;

  public SimpleScreenContainer(int tagKey, PathContextFactory contextFactory) {
    super(tagKey);
    Log.e(TAG, START + "SimpleScreenContainer constructor");
    this.contextFactory = contextFactory;
  }

  @Override
  protected void performTraversal(final ViewGroup containerView,
      final TraversalState traversalState, final Flow.Direction direction,
      final Flow.TraversalCallback callback) {
    Log.e(TAG, START + "SimpleScreenContainer performTraversal");

    final PathContext context;
    final PathContext oldPath = getOldPath(containerView);

    Path to = traversalState.toPath();

    View newView;
    context = PathContext.create(oldPath, to, contextFactory);
    int layout = getLayout(to);
    newView = LayoutInflater.from(context)
        .cloneInContext(context) // not sure why cloned
        .inflate(layout, containerView, false);

    View fromView = null;
    if (traversalState.fromPath() != null) {
      fromView = containerView.getChildAt(0);
      traversalState.saveViewState(fromView);
    }
    traversalState.restoreViewState(newView);

    if (fromView == null || direction == REPLACE) {
      containerView.removeAllViews();
      containerView.addView(newView);
      oldPath.destroyNotIn(context, contextFactory);
      callback.onTraversalCompleted();
    } else {
      containerView.removeView(fromView);
      containerView.addView(newView);
      oldPath.destroyNotIn(context, contextFactory);
      callback.onTraversalCompleted();
    }
  } // end performTraversal

  protected PathContext getOldPath(final ViewGroup containerView) {
//    return PathContext.root(containerView.getContext());
//    return PathContext.get(containerView.getChildAt(0).getContext());
    if (containerView.getChildCount() > 0) {
      return PathContext.get(containerView.getChildAt(0).getContext());
    } else {
      return PathContext.root(containerView.getContext());
    }
  }

  protected int getLayout(Path path) {
    Log.e(TAG, START + "SimpleScreenContainer getLayout");
    Class pathType = path.getClass();
    Integer layoutResId = PATH_LAYOUT_CACHE.get(pathType);
    if (layoutResId == null) {
      Layout layout = (Layout) pathType.getAnnotation(Layout.class);
      if (layout == null) {
        throw new IllegalArgumentException(
            String.format("@%s annotation not found on class %s", Layout.class.getSimpleName(),
                    pathType.getName()));
      }
      layoutResId = layout.value();
      PATH_LAYOUT_CACHE.put(pathType, layoutResId);
    }
    return layoutResId;
  }

//  private void runAnimation(final ViewGroup container, final View from, final View to,
//      Flow.Direction direction, final Flow.TraversalCallback callback) {
//    Animator animator = createSegue(from, to, direction);
//    animator.addListener(new AnimatorListenerAdapter() {
//      @Override
//      public void onAnimationEnd(Animator animation) {
//        container.removeView(from);
//        callback.onTraversalCompleted();
//      }
//    });
//    animator.start();
//  }

//  protected Animator createSegue(View from, View to, Flow.Direction direction) {
//    boolean backward = direction == Flow.Direction.BACKWARD;
//    int fromTranslation = backward ? from.getWidth() : -from.getWidth();
//    int toTranslation = backward ? -to.getWidth() : to.getWidth();
//
//    AnimatorSet set = new AnimatorSet();
//
//    set.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_X, fromTranslation));
//    set.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_X, toTranslation, 0));
//
//    return set;
//  }
}