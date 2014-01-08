package robindecroon.careconnect;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SubjectiveFragment extends Fragment {

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    private ImageButton speakButton;
    private View rootView;
    private EditText inputField;

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
        inputField.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if(dragEvent.getAction() == DragEvent.ACTION_DROP) {
                    try {
                        ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                        CharSequence paste = item.getText();
                        addText(paste.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
        speakButton = (ImageButton) rootView.findViewById(R.id.btnSpeak);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // identifying your application to the Google service
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
                // hint in the dialog
//                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo");
                // hint to the recognizer about what the user is going to say
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                // number of results
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
                // recognition language
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "nl");
                getParentFragment().startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
            }
        });
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
        LinearLayout location = (LinearLayout) rootView.findViewById(R.id.location_container);
        for (String suggestion : getResources().getStringArray(R.array.location_subjective)) {
            addWord(location, suggestion);
        }

        return rootView;
    }

    private void addWord(final LinearLayout mostUsed, final String word) {
        final TextView word1 = new TextView(getActivity());
        word1.setText(word);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(4, 0, 4, 0);
        word1.setLayoutParams(params1);
        word1.setPadding(5, 5, 5, 5);
        word1.setBackgroundColor(getResources().getColor(android.R.color.white));
        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addText(word);
            }
        });
        word1.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("text", word);
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    word1.performClick();
                }
                return false;
            }

        });
        mostUsed.addView(word1);
    }

    public void removeWordFromSuggestions(String word) {
        List<View> allViews = getAllChildren(rootView);
        for (View view : allViews) {
            if (view instanceof LinearLayout && view.getId() != 0) {
                LinearLayout suggestion = (LinearLayout) view;
                List<View> aLinearLayout = getAllChildren(suggestion);
                for (View aTextView : aLinearLayout) {
                    if (aTextView instanceof TextView && word.contains(((TextView) aTextView).getText()))
                        suggestion.removeView(aTextView);
                }
            }
        }
    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == FragmentActivity.RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches == null) {
                Toast.makeText(getActivity(), getResources().getString(R.string.no_voice_recognition), Toast.LENGTH_LONG).show();
            }
            addText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addText(String input) {
        String output = input.substring(0, 1).toUpperCase() + input.substring(1) + ".";
        String current = inputField.getText().toString();
        if (current.length() != 0)
            inputField.setText(current + " " + output);
        else
            inputField.setText(output);
        removeWordFromSuggestions(input);
    }


}
