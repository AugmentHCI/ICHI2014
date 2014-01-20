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


public class MessagesFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static int staticSelection;


    private List<Message> messages = DummyMessageFactory.getDummyMixedMessages();


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
        fm.beginTransaction().replace(R.id.leftpane, MessagesListFragment.newInstance(0)).commit();
        fm.beginTransaction().replace(R.id.rightpane, MessageContentFragment.newInstance(messages.get(staticSelection))).commit();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public void messageOpened(int messageNumber) {
        staticSelection = messageNumber;
    }

}
