package robindecroon.careconnect.ui.soap;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import robindecroon.careconnect.AutoCompleteAdapter;
import robindecroon.careconnect.MainActivity;
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
        args.putInt(ARG_SECTION_NUMBER, 5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_prescription, container, false);
        inputField = (EditText) rootView.findViewById(R.id.prescription_input);
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeakPrescription);
        ImageButton barcodeButton = (ImageButton) rootView.findViewById(R.id.btnBarcode);
        barcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager manager = getActivity().getPackageManager();
                Intent i;
                try {
                    i = manager.getLaunchIntentForPackage("de.gavitec.android");
                    if (i == null)
                        throw new PackageManager.NameNotFoundException();
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {

                }
            }
        });

        ((TextView) rootView.findViewById(R.id.taken_before_label)).setText(getResources().getString(R.string.taken_before) + " " + getResources().getString(R.string.by) + " " + getResources().getString(R.string.full_name));

        Set<String> allMedication = new HashSet<String>();
        allMedication.addAll(Arrays.asList(getResources().getStringArray(R.array.medication_most_used)));
        allMedication.addAll(Arrays.asList(getResources().getStringArray(R.array.medication_recent)));
        allMedication.addAll(Arrays.asList(getResources().getStringArray(R.array.chronic_medication_array)));
        allMedication.addAll(Arrays.asList(getResources().getStringArray(R.array.medication_soap)));
        allMedication.addAll(Arrays.asList(getResources().getStringArray(R.array.medication_taken_before)));
        allMedication.addAll(Arrays.asList(getResources().getStringArray(R.array.medication_family)));
        allMedication.addAll(Arrays.asList(getResources().getStringArray(R.array.medication_location)));
        List<String> list = new ArrayList<String>();
        list.addAll(allMedication);
        AutoCompleteAdapter adapter = new AutoCompleteAdapter(getActivity(), list);
        ((AutoCompleteTextView) inputField).setAdapter(adapter);
        initializeInputView();

        LinearLayout mostUsedBased = (LinearLayout) rootView.findViewById(R.id.most_used_medication);
        for (String suggestion : getResources().getStringArray(R.array.medication_most_used)) {
            addWord(mostUsedBased, suggestion);
        }
        LinearLayout recent = (LinearLayout) rootView.findViewById(R.id.recent_medication);
        for (String suggestion : getResources().getStringArray(R.array.medication_recent)) {
            addWord(recent, suggestion);
        }
        LinearLayout chronicBased = (LinearLayout) rootView.findViewById(R.id.chronic_medication);
        for (String suggestion : getResources().getStringArray(R.array.chronic_medication_array)) {
            addWord(chronicBased, suggestion);
        }
        LinearLayout soapBased = (LinearLayout) rootView.findViewById(R.id.soap_based);
        for (String suggestion : getResources().getStringArray(R.array.medication_soap)) {
            addWord(soapBased, suggestion);
        }
        LinearLayout takenBefore = (LinearLayout) rootView.findViewById(R.id.taken_before);
        for (String suggestion : getResources().getStringArray(R.array.medication_taken_before)) {
            addWord(takenBefore, suggestion);
        }
        LinearLayout familyBased = (LinearLayout) rootView.findViewById(R.id.family_based);
        for (String suggestion : getResources().getStringArray(R.array.medication_family)) {
            addWord(familyBased, suggestion);
        }
        LinearLayout locationBased = (LinearLayout) rootView.findViewById(R.id.location_based);
        for (String suggestion : getResources().getStringArray(R.array.medication_location)) {
            addWord(locationBased, suggestion);
        }

        return rootView;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_CODE;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
