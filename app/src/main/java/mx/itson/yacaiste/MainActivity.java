package mx.itson.yacaiste;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.itson.yacaiste.adapters.ReportAdapter;
import mx.itson.yacaiste.entidades.ReportEntity;


public class MainActivity extends ActionBarActivity implements AbsListView.OnScrollListener {
    int headerHeight;
    int minHeaderHeight;
    int toolbarTitleLeftMargin;
    int minHeaderTranslation;
    int windowHeight;
    int marginFab;
    Toolbar toolbar;
    ImageView imageView;
    private ListView listView;
    // Header views
    private View headerView;
    private FloatingActionButton headerFab;
    private ReportAdapter mAdapter;
    private List<ReportEntity> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        minHeaderHeight = getResources().getDimensionPixelSize(R.dimen.action_bar_height);
        toolbarTitleLeftMargin = getResources().getDimensionPixelSize(R.dimen.toolbar_left_margin);
        marginFab = getResources().getDimensionPixelSize(R.dimen.margin_fab);
        Display display = getWindowManager().getDefaultDisplay();
        windowHeight = display.getHeight();
        items = new ArrayList<ReportEntity>();
        for (int i = 0; i < 100; i++) {
            ReportEntity report = new ReportEntity();
            report.setDireccion("Direccion numero " + i);
            report.setFecha(new Date(2015, 07, (int) Math.random() * 5));
            report.setNumReportes(i + 1);
            report.setRank(Math.random() * 5);
            report.setRank(i);
            items.add(report);
        }
        mAdapter = new ReportAdapter(this, items);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mAdapter);
        // Init the headerHeight and minHeaderTranslation values

        headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        minHeaderTranslation = -headerHeight + getResources().getDimensionPixelOffset(R.dimen.action_bar_height);

        // Inflate your header view
        headerView = getLayoutInflater().inflate(R.layout.header_view, listView, false);

        imageView = (ImageView) headerView.findViewById(R.id.imageView);
        headerFab = (FloatingActionButton) findViewById(R.id.header_fab);

        // Add the headerView to your listView
        listView.addHeaderView(headerView, null, false);

        // Set the onScrollListener
        listView.setOnScrollListener(this);

        // ...

    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Do nothing
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Integer scrollY = getScrollY(view);

        // This will collapse the header when scrolling, until its height reaches
        // the toolbar height
        headerView.setTranslationY(0);
        // Scroll ratio (0 <= ratio <= 1).
        // The ratio value is 0 when the header is completely expanded,
        // 1 when it is completely collapsed
        float offset = 1 - Math.max((float) (-minHeaderTranslation - scrollY) / -minHeaderTranslation, 0f);

        headerFab.setTranslationY(Math.min(headerHeight + windowHeight * offset - headerFab.getHeight() / 2, windowHeight - headerFab.getHeight() - marginFab * 2));

        //headerFab.setTranslationY(offset == 0 ? headerHeight - headerFab.getHeight() / 2 : windowHeight - headerFab.getHeight() - marginFab * 2);
        // Now that we have this ratio, we only have to apply translations, scales,
        // alpha, etc. to the header views

        // For instance, this will move the toolbar title & subtitle on the X axis
        // from its original position when the ListView will be completely scrolled
        // down, to the Toolbar title position when it will be scrolled up.
        // Or we can make the FAB disappear when the ListView is scrolled
        //headerFab.setAlpha(1 - offset);

        if (offset == 1) {
            toolbar.setBackgroundResource(R.color.primary);
        } else {
            toolbar.setBackgroundColor(Color.TRANSPARENT);
        }
        imageView.setAlpha(1 - offset);
    }


    // Method that allows us to get the scroll Y position of the ListView
    public int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);

        if (c == null)
            return 0;

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1)
            headerHeight = this.headerHeight;

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
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
}
