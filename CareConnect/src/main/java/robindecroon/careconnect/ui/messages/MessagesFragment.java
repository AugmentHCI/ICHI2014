package robindecroon.careconnect.ui.messages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import robindecroon.careconnect.MainActivity;
import robindecroon.careconnect.R;
import robindecroon.careconnect.factory.DummyMessageFactory;
import robindecroon.careconnect.messages.Message;


public class MessagesFragment extends Fragment implements MessageTypeDropdownFragment.Callbacks {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private List<Message> messages = DummyMessageFactory.getDummyMessages();

    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 3);
        fragment.setArguments(args);
        return fragment;
    }

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().replace(R.id.message_dropdown, new MessageTypeDropdownFragment()).commit();
        fm.beginTransaction().replace(R.id.leftpane, MessagesListFragment.newInstance()).commit();
        fm.beginTransaction().replace(R.id.rightpane, MessageContentFragment.newInstance(messages.get(0))).commit();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onTrackSelected(String trackId) {

    }

    @Override
    public void onTrackNameAvailable(String trackId, String trackName) {

    }
}
