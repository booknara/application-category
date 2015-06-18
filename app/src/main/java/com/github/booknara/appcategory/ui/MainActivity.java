package com.github.booknara.appcategory.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.github.booknara.appcategory.R;
import com.github.booknara.appcategory.adapter.AppListItem;
import com.github.booknara.appcategory.adapter.BaseAppArrayAdapter;
import com.github.booknara.appcategory.adapter.Item;
import com.github.booknara.appcategory.util.PackageUtil;
import com.github.booknara.appcategory.vo.PackageVO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ArrayList<PackageVO> packages;

    protected BaseAppArrayAdapter mAppBaseArrayAdapter;

    private ListView mAppListView;
    private ProgressBar mProgressBar;
    private AppCompatTextView mEmptyView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    protected boolean listShown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureActionBar();

        mAppListView = (ListView) findViewById(android.R.id.list);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
        mEmptyView = (AppCompatTextView) findViewById(android.R.id.empty);
        mSwipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        mAppListView.setEmptyView(mEmptyView);



        mAppListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (mAppListView == null || mAppListView.getChildCount() == 0) ?
                                0 : mAppListView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.primary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                packages.clear();
                mAppListView.setVisibility(View.GONE);
                forceRefresh(true);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    public void forceRefresh(boolean pullToRefresh) {
        new ReloadPackageTask(pullToRefresh).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    public BaseActivity setListShown(final boolean shown) {
        return setListShown(shown, true);
    }

    public BaseActivity setListShown(final boolean shown, final boolean animate) {
        if (shown == listShown) {
            if (shown)
                // List has already been shown so hide/show the empty view with
                // no fade effect
                if (packages.isEmpty())
                    hide(mAppListView).show(mEmptyView);
                else
                    hide(mEmptyView).show(mAppListView);
            return this;
        }

        listShown = shown;

        if (shown)
            if (!packages.isEmpty())
                hide(mProgressBar).hide(mEmptyView).fadeIn(mAppListView, animate)
                        .show(mAppListView);
            else
                hide(mProgressBar).hide(mAppListView).fadeIn(mEmptyView, animate)
                        .show(mEmptyView);
        else
            hide(mAppListView).hide(mEmptyView).fadeIn(mProgressBar, animate)
                    .show(mProgressBar);

        return this;
    }

    private List<Item> setListItem(List<PackageVO> packages) {
        List<Item> items = new ArrayList<>();
        for (PackageVO vo:packages) {
            if (vo.systemApp) {
                continue;
            }

            items.add(new AppListItem(this, vo));
        }

        return items;
    }

    private class ReloadPackageTask extends AsyncTask<String, Void, String> {
        private boolean pullToRefresh;
        public ReloadPackageTask(boolean pullToRefresh) {
            this.pullToRefresh = pullToRefresh;
        }

        @Override
        protected String doInBackground(String... args) {
            if (packages.size() != 0 )
                packages.clear();

            // 1. Getting device applications
            packages = ((ArrayList<PackageVO>) PackageUtil.getLaunchableApps(ctx(), true));

            // 2. Getting applications' category

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            mAppBaseArrayAdapter.replaceAll(setListItem(packages));
            if (pullToRefresh) {
                mAppListView.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                setListShown(true, false);
            }
        }
    }
}
