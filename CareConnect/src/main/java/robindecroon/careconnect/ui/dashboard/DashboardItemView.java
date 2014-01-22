package robindecroon.careconnect.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 03/01/14.
 */
public class DashboardItemView extends LinearLayout {

    private Context mContext;

    private String type;

    private View view;

    public DashboardItemView(Context context, String type) {
        super(context);
        this.mContext = context;
        this.type = type;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.view_dashboard_item, this, true);
        }
        initialize();
    }

    private void initialize() {
        TextView title = (TextView) view.findViewById(R.id.dashboard_item_title);
        title.setText(type);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);


        LinearLayout layout  = (LinearLayout) view.findViewById(R.id.dashboard_item_layout);


        String[] contentArray = getResources().getStringArray(getResources().getIdentifier(type, "array", mContext.getPackageName()));

        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(2011, 2013);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        for (String itemString : contentArray) {

            LinearLayout item  = new LinearLayout(mContext);
            item.setOrientation(HORIZONTAL);
            item.setPadding(10,2,10,2);

            String dateString = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (prefs.getBoolean("dashboard_date",true)) {
                dateString += sdf.format(gc.getTime());
            }
            TextView date = new TextView(mContext);
            date.setText(dateString);
            date.setTextColor(getResources().getColor(android.R.color.darker_gray));


            TextView content = new TextView(mContext);
            String contentString =  " - " + itemString + "\n";
            content.setText(contentString);

            gc.add(gc.DAY_OF_YEAR, -5);

            item.addView(date);
            item.addView(content);

            layout.addView(item);
        }
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }


}
