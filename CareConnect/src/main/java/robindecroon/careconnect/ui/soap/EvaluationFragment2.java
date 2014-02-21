package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import robindecroon.careconnect.AutoCompleteAdapter;
import robindecroon.careconnect.R;


public class EvaluationFragment2 extends SOAPParentFragment {

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1235;

    private static final String ARG_LABEL = "label";

    private String mLabelText = "";

    private TextView icpcLabel;

    public static EvaluationFragment2 newInstance(String labelText) {
        EvaluationFragment2 fragment = new EvaluationFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_LABEL, labelText);
        fragment.setArguments(args);
        return fragment;
    }

    public EvaluationFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mLabelText = getArguments().getString(ARG_LABEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_evaluation2, container, false);
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

        TextView chronicDiseases = (TextView) rootView.findViewById(R.id.chronic_diseases);
        chronicDiseases.setText(getResources().getString(R.string.chronic_diseases) + " " + getResources().getString(R.string.of) + " " + getResources().getString(R.string.full_name));
        LinearLayout chronics = (LinearLayout) rootView.findViewById(R.id.chronic_evaluation);
        for (String chronicDisease : getResources().getStringArray(R.array.chronic_array)) {
            addWord(chronics, chronicDisease);
        }

        LinearLayout prehistory = (LinearLayout) rootView.findViewById(R.id.history_evaluation);
        for (String chronicDisease : getResources().getStringArray(R.array.prehistory_evaluation)) {
            addWord(prehistory, chronicDisease);
        }

        LinearLayout recent = (LinearLayout) rootView.findViewById(R.id.recent_evaluation);
        for (String chronicDisease : getResources().getStringArray(R.array.recent_evaluation)) {
            addWord(recent, chronicDisease);
        }

        LinearLayout favorites = (LinearLayout) rootView.findViewById(R.id.favorites_evaluation);
        for (String chronicDisease : getResources().getStringArray(R.array.favorites_evaluation)) {
            addWord(favorites, chronicDisease);
        }

        LinearLayout mostused = (LinearLayout) rootView.findViewById(R.id.most_used_evaluation);
        for (String chronicDisease : getResources().getStringArray(R.array.most_used_evaluation)) {
            addWord(mostused, chronicDisease);
        }

        return rootView;
    }

    public String getLabelText() {
        return mLabelText;
    }

    public void setLabelText(String text) {
        icpcLabel.setText("  " + text);
        icpcLabel.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.left), null, null, null);
    }

    /**
     * Read csv file.
     *
     * @return the map
     * @throws java.io.IOException Signals that an I/O exception has occurred.
     */
    public List<String> readICPCCSVFile() throws IOException {
        InputStream input = getActivity().getAssets().open("icpc.csv");
        BufferedReader in = new BufferedReader(new InputStreamReader(input, "ISO-8859-1"));

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
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    protected int getVoiceRecognitionCode() {
        return VOICE_RECOGNITION_REQUEST_CODE;
    }

}
