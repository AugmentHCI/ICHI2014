package robindecroon.careconnect.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 03/01/14.
 */
public class DashboardItemView extends LinearLayout {

    private Context mContext;

    private String type;

    private TextView title;
    private TextView content;
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
        title = (TextView) view.findViewById(R.id.dashboard_item_title);
        title.setText(type);

        content = (TextView) view.findViewById(R.id.dashboard_item_content);
        String[] contentArray = getResources().getStringArray(getResources().getIdentifier(type, "array", mContext.getPackageName()));
        String displayString = "";
        for (String str : contentArray) {
            displayString += "- " + str + "\n";
        }
        content.setText(displayString);
    }


}
