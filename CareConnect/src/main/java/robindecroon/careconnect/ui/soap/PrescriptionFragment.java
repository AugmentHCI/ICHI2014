package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 15/01/14.
 */
public class PrescriptionFragment extends SOAPParentFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private final static int VOICE_RECOGNITION_CODE = 1236;

    public static Fragment newInstance() {
        PrescriptionFragment fragment = new PrescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_prescription, container, false);
        inputField = (EditText) rootView.findViewById(R.id.prescription_input);
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeakPrescription);

        initializeInputView();

        LinearLayout mostUsed = (LinearLayout) rootView.findViewById(R.id.soap_based);
        for (String suggestion : getResources().getStringArray(R.array.medication_soap)) {
            addWord(mostUsed, suggestion);
        }
        LinearLayout recent = (LinearLayout) rootView.findViewById(R.id.taken_before);
        for (String suggestion : getResources().getStringArray(R.array.medication_taken_before)) {
            addWord(recent, suggestion);
        }
        LinearLayout prehis = (LinearLayout) rootView.findViewById(R.id.family_based);
        for (String suggestion : getResources().getStringArray(R.array.medication_family)) {
            addWord(prehis, suggestion);
        }
        LinearLayout colleagues = (LinearLayout) rootView.findViewById(R.id.location_based);
        for (String suggestion : getResources().getStringArray(R.array.medication_location)) {
            addWord(colleagues, suggestion);
        }


        return rootView;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_CODE;
    }

}
