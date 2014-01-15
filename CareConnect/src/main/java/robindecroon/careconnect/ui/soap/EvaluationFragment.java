package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import robindecroon.careconnect.R;


public class EvaluationFragment extends SOAPParentFragment {

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1235;

    public static EvaluationFragment newInstance() {
        EvaluationFragment fragment = new EvaluationFragment();
        return fragment;
    }

    public EvaluationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_evaluation, container, false);
        inputField = (EditText) rootView.findViewById(R.id.evaluation_input);
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeakEvaluation);

        initializeInputView();

        LinearLayout suggestions = (LinearLayout) rootView.findViewById(R.id.suggestion_evaluation);
        for (String suggestion : getResources().getStringArray(R.array.evaluation_suggestions)) {
            addWord(suggestions, suggestion);
        }

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.icpc_chapter_fragment, new ICPCChapterFragment()).commit();

        return rootView;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_REQUEST_CODE;
    }

}
