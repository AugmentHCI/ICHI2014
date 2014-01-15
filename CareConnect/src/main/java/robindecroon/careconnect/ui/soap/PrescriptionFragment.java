package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 15/01/14.
 */
public class PrescriptionFragment extends SOAPParentFragment {

    private final static int VOICE_RECOGNITION_CODE = 1236;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_evaluation, container, false);
        inputField = (EditText) rootView.findViewById(R.id.evaluation_input);
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeakEvaluation);

        initializeInputView();


        return rootView;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_CODE;
    }
}
