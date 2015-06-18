package com.github.booknara.appcategory.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.github.booknara.appcategory.util.ViewUtils;

/**
 * @author : Daehee Han(@daniel_booknara)
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected BaseActivity show(final View view) {
        ViewUtils.setGone(view, false);
        return this;
    }

    protected BaseActivity hide(final View view) {
        ViewUtils.setGone(view, true);
        return this;
    }

    protected BaseActivity fadeIn(final View view, final boolean animate) {
        if (view != null)
            if (animate)
                view.startAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.fade_in));
            else
                view.clearAnimation();
        return this;
    }

    public abstract void configureActionBar();

    public Context ctx() {
        return this;
    }
}
