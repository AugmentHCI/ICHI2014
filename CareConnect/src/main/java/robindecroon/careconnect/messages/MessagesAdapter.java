package robindecroon.careconnect.messages;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 06/01/14.
 */
public class MessagesAdapter extends ArrayAdapter implements Filterable, ListAdapter {

    private List<Message> allMessages;
    private List<Message> messages;
    private Context mContext;

    public MessagesAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource);
        mContext = context;
        this.messages = objects;
        this.allMessages = objects;
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int i) {
        return ((Message) getItem(i)).getId();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public boolean isEmpty() {
        return messages.isEmpty();
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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (view == null) {
            Message message = (Message) getItem(i);
            view = ((FragmentActivity) mContext).getLayoutInflater().inflate(R.layout.list_item_message, viewGroup, false);
            ((TextView) view.findViewById(R.id.list_text_message)).setText(message.getTitle());
            Drawable icon = mContext.getResources().getDrawable(message.getIconType());
            ((ImageView) view.findViewById(R.id.list_icon_message)).setImageDrawable(icon);
//        }
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    messages = (List<Message>) results.values;
                    notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = allMessages;
                    results.count = allMessages.size();

                } else { // We perform filtering operation
                    List<Message> filteredRowItems = new ArrayList<Message>();

                    for (int i = 0; i < getCount(); i++) {
                        if (((Message) getItem(i)).getType().toString().equals(constraint.toString())) {
                            filteredRowItems.add((Message) getItem(i));
                        }
                    }
                    results.values = filteredRowItems;
                    results.count = filteredRowItems.size();
                }
                return results;
            }
        };
        return filter;
    }

}
