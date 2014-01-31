package robindecroon.careconnect.ui.messages;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

    public static final int VIEW_TYPE_ALL = 0;
    public static final int VIEW_TYPE_LAB_RESULTS = 1;
    public static final int VIEW_TYPE_REFERRAL = 2;
    public static final int VIEW_TYPE_IMAGES = 3;


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
        public void onTrackSelected(int trackId);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onTrackSelected(int trackId) {
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
        loadTrack(true);
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
        loadTrack(true);

        if (mListPopupWindow != null) {
            mListPopupWindow.dismiss();
        }
    }

    private void loadTrack(boolean triggerCallback) {
        switch (mViewType) {
            case VIEW_TYPE_ALL:
                mTitle.setText(getResources().getString(R.string.all_messages));
                mAbstract.setText(getResources().getString(R.string.all_messages_subtitle));
                testView.setBackgroundColor(Color.GREEN);
                mIcon.setImageDrawable(getResources().getDrawable(R.drawable.message_icon));
                break;
            case VIEW_TYPE_LAB_RESULTS:
                mTitle.setText(getResources().getString(R.string.lab_results));
                mAbstract.setText(getResources().getString(R.string.lab_results_subtitle));
                mIcon.setImageDrawable(getResources().getDrawable(R.drawable.lab_result_icon));
                testView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                break;
            case VIEW_TYPE_REFERRAL:
                mTitle.setText(getResources().getString(R.string.referral));
                mAbstract.setText(getResources().getString(R.string.referral_subtitle));
                testView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                mIcon.setImageDrawable(getResources().getDrawable(R.drawable.referral_icon));
                break;
            case VIEW_TYPE_IMAGES:
                mTitle.setText(getResources().getString(R.string.images));
                mAbstract.setText(getResources().getString(R.string.xray_subtitle));
                testView.setBackgroundColor(getResources().getColor(android.R.color.black));
                mIcon.setImageDrawable(getResources().getDrawable(R.drawable.xray));
                break;
        }

        if (triggerCallback) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallbacks.onTrackSelected(mViewType);
                }
            });
        }
    }

    public void onDismiss() {
        mListPopupWindow = null;
    }

    public static class TracksAdapter extends BaseAdapter {

        private Activity mActivity;
        private Resources res;

        public TracksAdapter(FragmentActivity activity) {
            mActivity = activity;
            this.res = mActivity.getResources();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mActivity.getLayoutInflater().inflate(R.layout.list_item_track, parent, false);
                String text = "";
                Drawable icon = null;
                switch (position) {
                    case 0:
                        text = res.getString(R.string.all_messages);
                        icon = res.getDrawable(R.drawable.message_icon);
                        break;
                    case 1:
                        text = res.getString(R.string.lab_results);
                        icon = res.getDrawable(R.drawable.lab_result_icon);
                        break;
                    case 2:
                        text = res.getString(R.string.referral);
                        icon = res.getDrawable(R.drawable.referral_icon);
                        break;
                    case 3:
                        text = res.getString(R.string.images);
                        icon = res.getDrawable(R.drawable.xray);
                }
                ((TextView) convertView.findViewById(R.id.text1)).setText(text);
                ((ImageView) convertView.findViewById(R.id.icon1)).setImageDrawable(icon);
            }
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

