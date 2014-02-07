package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import robindecroon.careconnect.R;


public class SubjectiveFragment extends SOAPParentFragment {

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    public static SubjectiveFragment newInstance() {
        SubjectiveFragment fragment = new SubjectiveFragment();
        return fragment;
    }

    public SubjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_subjective, container, false);
        inputField = (EditText) rootView.findViewById(R.id.subjective_input);
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeak);

        initializeInputView();

        LinearLayout favorites = (LinearLayout) rootView.findViewById(R.id.favorites_container);
        for (String suggestion : getResources().getStringArray(R.array.favorites_subjective)) {
            addWord(favorites, suggestion);
        }
        LinearLayout mostUsed = (LinearLayout) rootView.findViewById(R.id.most_used_container);
        for (String suggestion : getResources().getStringArray(R.array.most_used_subjective)) {
            addWord(mostUsed, suggestion);
        }
        LinearLayout recent = (LinearLayout) rootView.findViewById(R.id.recent_container);
        for (String suggestion : getResources().getStringArray(R.array.recent_subjective)) {
            addWord(recent, suggestion);
        }
        LinearLayout prehis = (LinearLayout) rootView.findViewById(R.id.prehistory_container);
        for (String suggestion : getResources().getStringArray(R.array.prehistory_subjective)) {
            addWord(prehis, suggestion);
        }
        LinearLayout colleagues = (LinearLayout) rootView.findViewById(R.id.colleagues_container);
        for (String suggestion : getResources().getStringArray(R.array.colleagues_subjective)) {
            addWord(colleagues, suggestion);
        }

        return rootView;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_REQUEST_CODE;
    }
}
