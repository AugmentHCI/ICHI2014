package robindecroon.careconnect.ui.messages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import robindecroon.careconnect.R;
import robindecroon.careconnect.factory.DummyMessageFactory;
import robindecroon.careconnect.messages.MessageType;
import robindecroon.careconnect.messages.MessagesAdapter;

public class MessagesListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnMessageListInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    private static final String ARG_FILTER = "filterType";

    private int mFilter;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private MessagesAdapter mAdapter;

    public static MessagesListFragment newInstance(int filter) {
        MessagesListFragment fragment = new MessagesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FILTER, filter);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MessagesListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFilter = getArguments().getInt(ARG_FILTER);
        }
        mAdapter = new MessagesAdapter(getActivity(), android.R.id.list, DummyMessageFactory.getDummyMixedMessages());
        updateFilter(mFilter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messageslist, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        mListView.setItemChecked(MessagesFragment.staticSelection, true);
        mListView.setSelection(MessagesFragment.staticSelection);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMessageListInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMessageListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onMessageListItemSelected(id);
        }
    }

    private void updateFilter(int type) {
        switch (type) {
            case 0:
                mAdapter.getFilter().filter(null);
                break;
            case 1:
                mAdapter.getFilter().filter(MessageType.LAB.toString());
                break;
            case 2:
                mAdapter.getFilter().filter(MessageType.REFERRAL.toString());
                break;
            case 3:
                mAdapter.getFilter().filter(MessageType.IMAGE.toString());
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMessageListInteractionListener {
        public void onMessageListItemSelected(long id);
    }

}
