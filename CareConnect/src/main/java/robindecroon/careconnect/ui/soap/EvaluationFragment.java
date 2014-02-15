package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import robindecroon.careconnect.AutoCompleteAdapter;
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
        inputField = (AutoCompleteTextView) rootView.findViewById(R.id.evaluation_input);
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeakEvaluation);

        List<String> searchArrayList= null;
        try {
            searchArrayList = readICPCCSVFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AutoCompleteAdapter adapter = new AutoCompleteAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line, android.R.id.text1, searchArrayList);
        ((AutoCompleteTextView) inputField).setAdapter(adapter);
        initializeInputView();

        LinearLayout suggestions = (LinearLayout) rootView.findViewById(R.id.suggestion_evaluation);
        for (String suggestion : getResources().getStringArray(R.array.evaluation_suggestions)) {
            addWord(suggestions, suggestion);
        }

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.icpc_chapter_fragment, new ICPCChapterFragment()).commit();

        return rootView;
    }

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    /**
     * Read csv file.
     *
     * @return the map
     * @throws java.io.IOException Signals that an I/O exception has occurred.
     */
    public List<String> readICPCCSVFile() throws IOException {
        InputStream input = getActivity().getAssets().open("icpc.csv");
        BufferedReader in = new BufferedReader(new InputStreamReader(input));

        List<String> list = new ArrayList<String>();

        try {
            in.readLine();
            String line;
            while ((line = in.readLine()) != null) {
                try {
                    String[] values = line.split(";");
                    String code = values[1];
                    String description = values[2];
                    list.add(code + " " + description);
                } catch (Exception e) {
                    Log.e("ICPCParser", "Error in country CSV file!");
                }
            }
        } catch (Exception e) {
            Log.e("ICPCParser", "Error in country CSV file!");
        }
        return list;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_REQUEST_CODE;
    }

}
