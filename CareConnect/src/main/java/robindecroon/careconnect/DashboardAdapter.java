package robindecroon.careconnect;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.askerov.dynamicgid.BaseDynamicGridAdapter;

import java.util.List;

/**
 * Created by robindecroon on 03/01/14.
 */
public class DashboardAdapter extends BaseDynamicGridAdapter {

    private int viewHeight;
    private int viewWidth;

    protected DashboardAdapter(Context context, List<?> items, int columnCount, int viewWidth, int viewHeight) {
        super(context, items, columnCount);
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String dashboardType = (String) getItem(position);
        DashboardItemView dashboardView = new DashboardItemView(getContext(),dashboardType);
        dashboardView.setLayoutParams(new LinearLayout.LayoutParams(viewWidth, viewHeight));
        return dashboardView;
    }
}
