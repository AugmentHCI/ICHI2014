package robindecroon.careconnect.ui.soap;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.DragEvent;
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

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 15/01/14.
 */
public abstract class SOAPParentFragment extends Fragment {

    protected ImageButton speakButton;
    protected View rootView;
    protected EditText inputField;

    protected void initializeInputView() {
        inputField.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
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
                getParentFragment().startActivityForResult(intent, getVoiceRecognitionCode());
            }
        });
    }

    protected abstract int getVoiceRecognitionCode();

    protected void addText(String input) {
        String output = input.substring(0, 1).toUpperCase() + input.substring(1) + ".";
        String current = inputField.getText().toString();
        if (current.length() != 0)
            inputField.setText(current + " " + output);
        else
            inputField.setText(output);
        removeWordFromSuggestions(input);
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

    protected void addWord(final LinearLayout mostUsed, final String word) {
        final TextView word1 = new TextView(getActivity());
        word1.setBackground(getResources().getDrawable(R.drawable.shadow_white));
        word1.setText(word);
        word1.setTextSize(16);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(2, 0, 2, 0);
        word1.setLayoutParams(params1);
        word1.setPadding(12, 5, 10, 5);
        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addText(word);
            }
        });
        word1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("text", word);
                    Shadow shadow = new Shadow(view, word1.getText().toString());
                    view.startDrag(data, shadow, null, 0);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    word1.performClick();
                }
                return false;
            }

        });
        word1.bringToFront();
        mostUsed.addView(word1);
        mostUsed.invalidate();
    }


    protected ArrayList<View> getAllChildren(View v) {

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
        if (requestCode == getVoiceRecognitionCode() && resultCode == FragmentActivity.RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches == null) {
                Toast.makeText(getActivity(), getResources().getString(R.string.no_voice_recognition), Toast.LENGTH_LONG).show();
            }
            addText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public class Shadow extends View.DragShadowBuilder {
        private String text;

        public Shadow(View view, String text) {
            super(view);
            this.text = text;
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            Paint strokePaint = new Paint();
            strokePaint.setARGB(255, 255, 255, 255);
            strokePaint.setTextSize(getResources().getInteger(R.integer.drag_text_size));
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(2);

            Paint textPaint = new Paint();
            textPaint.setARGB(255, 0, 0, 0);
            textPaint.setTextSize(getResources().getInteger(R.integer.drag_text_size));

            canvas.drawText(text, 100, 100, strokePaint);
            canvas.drawText(text, 100, 100, textPaint);

        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize,
                                           Point shadowTouchPoint) {
            shadowSize.x = 800;
            shadowSize.y = 500;
            shadowTouchPoint.x = 50;
            shadowTouchPoint.y = 80;
        }
    }
}
