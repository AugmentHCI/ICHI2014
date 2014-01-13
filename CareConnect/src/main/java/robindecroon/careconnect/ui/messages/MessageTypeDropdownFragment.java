package robindecroon.careconnect.ui.messages;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.TextView;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 12/01/14.
 */
public class MessageTypeDropdownFragment extends Fragment implements
        AdapterView.OnItemClickListener,
        PopupWindow.OnDismissListener {

    public static final int VIEW_TYPE_SESSIONS = 0;
    public static final int VIEW_TYPE_OFFICE_HOURS = 1;
    public static final int VIEW_TYPE_SANDBOX = 2;

    private static final String STATE_VIEW_TYPE = "viewType";
    private static final String STATE_SELECTED_TRACK_ID = "selectedTrackId";

    private TracksAdapter mAdapter;
    private int mViewType;

    private Handler mHandler = new Handler();

    private ListPopupWindow mListPopupWindow;
    private ViewGroup mRootView;
    private ImageView mIcon;
    private TextView mTitle;
    private TextView mAbstract;
    private String mTrackId;

    private View testView;

    public interface Callbacks {
        public void onTrackSelected(String trackId);

        public void onTrackNameAvailable(String trackId, String trackName);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onTrackSelected(String trackId) {
        }

        @Override
        public void onTrackNameAvailable(String trackId, String trackName) {
        }
    };

    private Callbacks mCallbacks = sDummyCallbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new TracksAdapter(getActivity());

        if (savedInstanceState != null) {
            // Since this fragment doesn't rely on fragment arguments, we must
            // handle state restores and saves ourselves.
            mViewType = savedInstanceState.getInt(STATE_VIEW_TYPE);
            mTrackId = savedInstanceState.getString(STATE_SELECTED_TRACK_ID);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_VIEW_TYPE, mViewType);
        outState.putString(STATE_SELECTED_TRACK_ID, mTrackId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_messagetype_dropdown, null);
        mIcon = (ImageView) mRootView.findViewById(R.id.track_icon);
        mTitle = (TextView) mRootView.findViewById(R.id.track_title);
        mAbstract = (TextView) mRootView.findViewById(R.id.track_abstract);
        testView = mRootView.findViewById(R.id.test_view);

        mRootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListPopupWindow = new ListPopupWindow(getActivity());
                mListPopupWindow.setAdapter(mAdapter);
                mListPopupWindow.setModal(true);
                mListPopupWindow.setContentWidth(
                        getResources().getDimensionPixelSize(R.dimen.track_dropdown_width));
                mListPopupWindow.setAnchorView(mRootView);
                mListPopupWindow.setOnItemClickListener(MessageTypeDropdownFragment.this);
                mListPopupWindow.show();
                mListPopupWindow.setOnDismissListener(MessageTypeDropdownFragment.this);
            }
        });
        return mRootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new ClassCastException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
        if (mListPopupWindow != null) {
            mListPopupWindow.dismiss();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mViewType = (Integer) mAdapter.getItem(position);
        loadTrack("", true);

        if (mListPopupWindow != null) {
            mListPopupWindow.dismiss();
        }
    }

    private void loadTrack(String type, boolean triggerCallback) {
        int trackColor = 0;
        final Resources res = getResources();


        switch (mViewType) {
            case VIEW_TYPE_SESSIONS:
                mTitle.setText("Alle berichten");
                mAbstract.setText("Alle mogelijke berichten");
                trackColor = Color.BLUE;
                break;
            case VIEW_TYPE_OFFICE_HOURS:
                mTitle.setText("All sessions at Google I/O 2013.");
                mAbstract.setText("All office hours at Google I/O 2013.");
                trackColor = Color.GREEN;
                break;
            case VIEW_TYPE_SANDBOX:
                mTitle.setText("All companies");
                mAbstract.setText("All developer sandbox companies at Google I/O 2013.");
                trackColor = Color.RED;
                break;
        }

        testView.setBackgroundColor(trackColor);
        mIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        mCallbacks.onTrackNameAvailable(mTrackId, mTitle.getText().toString());

        if (triggerCallback) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallbacks.onTrackSelected(mTrackId);
                }
            });
        }
    }

    public void onDismiss() {
        mListPopupWindow = null;
    }

    public static class TracksAdapter extends BaseAdapter {

        private Activity mActivity;

        public TracksAdapter(FragmentActivity activity) {
            mActivity = activity;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = mActivity.getLayoutInflater().inflate(R.layout.list_item_track, parent, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1)).setText("All tracks");

            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }
    }

}

