package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import robindecroon.careconnect.R;


public class PlanningFragment extends SOAPParentFragment {

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 4321;

    public static PlanningFragment newInstance() {
        PlanningFragment fragment = new PlanningFragment();
        return fragment;
    }

    public PlanningFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_planning, container, false);
        inputField = (EditText) rootView.findViewById(R.id.planning_input);
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeak);

        initializeInputView();

        LinearLayout favorites = (LinearLayout) rootView.findViewById(R.id.planning_favorites_container);
        for (String suggestion : getResources().getStringArray(R.array.planning_favorites)) {
            addWord(favorites, suggestion);
        }
        LinearLayout mostUsed = (LinearLayout) rootView.findViewById(R.id.planning_most_used_container);
        for (String suggestion : getResources().getStringArray(R.array.planning_most_used)) {
            addWord(mostUsed, suggestion);
        }
        LinearLayout recent = (LinearLayout) rootView.findViewById(R.id.planning_recent_container);
        for (String suggestion : getResources().getStringArray(R.array.planning_recent)) {
            addWord(recent, suggestion);
        }
        LinearLayout prehis = (LinearLayout) rootView.findViewById(R.id.planning_treatment_container);
        for (String suggestion : getResources().getStringArray(R.array.planning_treatment)) {
            addWord(prehis, suggestion);
        }
        LinearLayout colleagues = (LinearLayout) rootView.findViewById(R.id.planning_referal_container);
        for (String suggestion : getResources().getStringArray(R.array.planning_referal)) {
            addWord(colleagues, suggestion);
        }
        LinearLayout location = (LinearLayout) rootView.findViewById(R.id.planning_parameter_container);
        for (String suggestion : getResources().getStringArray(R.array.planning_parameter)) {
            addWord(location, suggestion);
        }

        return rootView;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_REQUEST_CODE;
    }
}
