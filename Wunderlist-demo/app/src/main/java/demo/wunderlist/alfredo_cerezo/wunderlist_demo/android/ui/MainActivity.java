package demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import demo.wunderlist.alfredo_cerezo.wunderlist_demo.R;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.ApplicationWunderlist;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.ui.mvp.MainPresenter;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.ui.mvp.MainPresenterImpl;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.ui.mvp.MainView;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.ui.mvp.SimpleItemTouchHelperCallback;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.android.ui.mvp.TaskListAdapter;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.core.entities.Observer;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.core.entities.Task;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.exceptions.WunderlistException;
import demo.wunderlist.alfredo_cerezo.wunderlist_demo.interactors.TaskInteractors;

public class MainActivity extends AppCompatActivity implements MainView<Task> {

    private static String TAG = "MainActivity";
    private TaskListAdapter mAdapter;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenterImpl(this, getApplicationContext());
        initToolBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }


    private void initList(List<Task> listTask) {
        RecyclerView list = (RecyclerView) findViewById(R.id.recyclerView);
        ArrayList<Task> data = new ArrayList<>(listTask);
        mAdapter = new TaskListAdapter(data, mPresenter);

        list.setAdapter(mAdapter);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        list.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(list);
    }


    private void initToolBar() {
        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBar = (CollapsingToolbarLayout) findViewById(R.id.ctlLayout);
        toolBar.setTitle(getString(R.string.app_name));
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

    @Override
    public void addTask(Task task) {
        mAdapter.addTask(task);
    }

    @Override
    public void removeTask(Task task) {

    }

    @Override
    public void onCheckTask(int position) {
        mAdapter.markTaskAsCompleted(position);
        mPresenter.updateTask(mAdapter.getTask(position));
    }

    @Override
    public void onUnCheckTask(int position) {
        //TODO maybe in a future
    }

    @Override
    public void setItems(List<Task> items) {
        initList(items);
    }
}