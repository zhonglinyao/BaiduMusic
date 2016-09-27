package com.example.lanou3g.baidumusic;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by dllo on 16/9/26.
 */
public class CustomBehavior extends CoordinatorLayout.Behavior<AppBarLayout>{
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, AppBarLayout child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }
}
