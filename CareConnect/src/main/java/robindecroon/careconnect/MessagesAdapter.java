package robindecroon.careconnect;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by robindecroon on 06/01/14.
 */
public class MessagesAdapter implements ListAdapter {

    private Context mContext;

    public MessagesAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView test = new TextView(mContext);
        test.setText("Testtekst");
        test.setTextSize(20);
        int icon;
        if (new Random().nextBoolean()) {
            icon = R.drawable.labo_res;
        } else {
            icon = R.drawable.refer_icon;
        }
        test.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        test.setGravity(Gravity.CENTER_VERTICAL);
        test.setPadding(20, 20, 20, 20);
        return test;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
