package robindecroon.careconnect.messages;

import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import robindecroon.careconnect.R;
import robindecroon.careconnect.factory.DummyMessageFactory;

/**
 * Created by robindecroon on 06/01/14.
 */
public class MessagesAdapter implements ListAdapter {

    private FragmentActivity mContext;

    private List<Message> messages;

    public MessagesAdapter(FragmentActivity context) {
        this.mContext = context;
        messages = DummyMessageFactory.getDummyMessages();
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
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
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
        if (view == null) {
            Message message = (Message) getItem(i);
            view = mContext.getLayoutInflater().inflate(R.layout.list_item_message, viewGroup, false);
            ((TextView) view.findViewById(R.id.list_text_message)).setText(message.getTitle());
            Drawable icon = mContext.getResources().getDrawable(message.getIconType());
            ((ImageView) view.findViewById(R.id.list_icon_message)).setImageDrawable(icon);
        }
        return view;
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
