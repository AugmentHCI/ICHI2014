package robindecroon.careconnect.ui.messages;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import robindecroon.careconnect.R;
import robindecroon.careconnect.messages.Message;


public class MessageContentFragment extends Fragment {

    private final static String ARG_TITLE = "messageContentTitle";
    private final static String ARG_CONTENT = "messageContentContent";
    private final static String ARG_ICON = "messageContentIcon";
    private final static String ARG_DRAWABLE = "messageDrawableID";

    private String title;
    private String content;
    private int icon;
    private int drawableID;

    private View rootView;
    private TextView titleView;
    private TextView contentView;
    private ImageView iconView;

    public static MessageContentFragment newInstance(Message message) {
        return newInstance(message.getTitle(), message.getContent(), message.getIconType(), message.getDrawableId());
    }

    public static MessageContentFragment newInstance(String title, String content, int icon, int drawableID) {
        MessageContentFragment fragment = new MessageContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CONTENT, content);
        args.putInt(ARG_ICON, icon);
        args.putInt(ARG_DRAWABLE, drawableID);
        fragment.setArguments(args);
        return fragment;
    }

    public MessageContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            content = getArguments().getString(ARG_CONTENT);
            icon = getArguments().getInt(ARG_ICON);
            drawableID = getArguments().getInt(ARG_DRAWABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message_content, container, false);

        titleView = (TextView) rootView.findViewById(R.id.message_item_title);
        titleView.setText(this.title);

        iconView = (ImageView) rootView.findViewById(R.id.message_item_icon);
        Drawable drawableIcon = getResources().getDrawable(icon);
        iconView.setImageDrawable(drawableIcon);


        contentView = (TextView) rootView.findViewById(R.id.message_item_content);
        contentView.setText(this.content);
        if(drawableID != 0) {
            contentView.setCompoundDrawablesWithIntrinsicBounds(0,drawableID,0,0);
        }
        return rootView;
    }

}
