package robindecroon.careconnect;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.List;

import robindecroon.careconnect.ui.dashboard.DashboardItemView;

/**
 * Created by robindecroon on 03/01/14.
 */
public class DashboardAdapter extends BaseAdapter {

    private int viewHeight;
    private int viewWidth;
    private List<?> items;
    private Context context;

    public DashboardAdapter(Context context, List<?> items, int viewWidth, int viewHeight) {
        this.items = items;
        this.context = context;
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String dashboardType = (String) getItem(position);
        DashboardItemView dashboardView = new DashboardItemView(context, dashboardType);
        dashboardView.setLayoutParams(new AbsListView.LayoutParams(viewWidth, viewHeight));

        return dashboardView;
    }
}
