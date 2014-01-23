package robindecroon.careconnect.ui.dashboard;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 03/01/14.
 */
public class DashboardItemView extends LinearLayout {

    private Context mContext;
    private String mType;
    private View mView;
    private GregorianCalendar mGregorianCalendar;
    private String[] mItems;
    private SimpleDateFormat mSimpleDateFormat;
    private LinearLayout mContentLayout;

    public DashboardItemView(Context context, String type) {
        super(context);
        this.mContext = context;
        this.mType = type;
        this.mGregorianCalendar = new GregorianCalendar();
        mItems = getResources().getStringArray(getResources().getIdentifier(mType, "array", mContext.getPackageName()));
        int year = randBetween(2011, 2013);
        mGregorianCalendar.set(mGregorianCalendar.YEAR, year);
        int dayOfYear = randBetween(1, mGregorianCalendar.getActualMaximum(mGregorianCalendar.DAY_OF_YEAR));
        mGregorianCalendar.set(mGregorianCalendar.DAY_OF_YEAR, dayOfYear);
        mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            mView = layoutInflater.inflate(R.layout.view_dashboard_item, this, true);
        }
        mContentLayout  = (LinearLayout) mView.findViewById(R.id.dashboard_item_layout);
        setTitle();
        initializeContentLayout();
    }

    private void setTitle() {
        TextView titleView = (TextView) mView.findViewById(R.id.dashboard_item_title);
        titleView.setText(mType.replace('_',' '));
    }

    private void initializeContentLayout() {
        for (String itemString : mItems) {
            if(mType.equals(DashboardFragment.MEDICATION)) {
                mContentLayout.addView(getMedicationItemLayout(itemString));
            } else if(mType.equals(DashboardFragment.PROBLEMS) || mType.equals(DashboardFragment.HISTORY)) {
                mContentLayout.addView(getProblemItemLayout(itemString));
            } else {
                View content = getNewItemView(itemString);
                LinearLayout container = getOuterLayout(HORIZONTAL);
                container.addView(content);
                mContentLayout.addView(container);
            }
        }
    }

    private LinearLayout getMedicationItemLayout(String itemString) {
        LinearLayout outerLayout =  getOuterLayout(VERTICAL);

        LinearLayout oneLineLayout  = new LinearLayout(mContext);
        oneLineLayout.setOrientation(HORIZONTAL);
        TextView date = getNewDateView();
        TextView content = getNewItemView(itemString);
        oneLineLayout.addView(date);
        oneLineLayout.addView(content);

        TextView dosage = getNewDosageView();

        outerLayout.addView(oneLineLayout);
        outerLayout.addView(dosage);
        return outerLayout;
    }

    private LinearLayout getProblemItemLayout(String itemString) {
        LinearLayout outerLayout =  getOuterLayout(HORIZONTAL);

        TextView date = getNewDateView();
        TextView content = getNewItemView(itemString);
        outerLayout.addView(date);
        outerLayout.addView(content);

        return outerLayout;
    }

    private LinearLayout getOuterLayout(int orientation) {
        LinearLayout outerLayout = new LinearLayout(mContext);
        outerLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        outerLayout.setOrientation(orientation);
        outerLayout.setPadding(10, 5, 10, 5);
        outerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return outerLayout;
    }

    private TextView getNewItemView(String itemString) {
        TextView content = new TextView(mContext);
        String contentString =  " - " + itemString;
        content.setText(contentString);
        return content;
    }

    private TextView getNewDosageView() {
        TextView dosage = new TextView(mContext);
        SpannableString spanString = new SpannableString("3 per dag: 's morgens, 's middags & 's avonds");
        spanString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, spanString.length(), 0);
        dosage.setText(spanString);
        dosage.setTextColor(getResources().getColor(android.R.color.darker_gray));
        dosage.setPadding(10,0,0,0);
        return dosage;
    }

    private TextView getNewDateView() {
        TextView date = new TextView(mContext);
        date.setText(getNewTime());
        date.setTextColor(getResources().getColor(android.R.color.darker_gray));
        return date;
    }

    private String getNewTime() {
        Date date = mGregorianCalendar.getTime();
        mGregorianCalendar.add(mGregorianCalendar.DAY_OF_YEAR, -5);
        return mSimpleDateFormat.format(date);
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }


}
