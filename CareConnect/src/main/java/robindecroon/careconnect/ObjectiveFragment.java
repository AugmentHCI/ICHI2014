package robindecroon.careconnect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import robindecroon.careconnect.libraries.RangeSeekBar;

public class ObjectiveFragment extends Fragment {

    private View rootView;

    private ColoredSeekBar weightSeekbar;
    private TextView weightText;
    private Button weightButtonMin;
    private Button weightButtonPlus;

    private RangeSeekBar<Integer> bloodPressureSeekbar;
    private TextView bloodPressureLowText;
    private TextView bloodPressureHighText;
    private Button bloodPressureLowMin;
    private Button bloodPressureLowPlus;
    private Button bloodPressureHighMin;
    private Button bloodPressureHighPlus;

    private SeekBar lengthSeekbar;
    private TextView lenghtText;
    private Button lengthButtonMin;
    private Button lengthButtonPlus;


    public static ObjectiveFragment newInstance() {
        ObjectiveFragment fragment = new ObjectiveFragment();
        return fragment;
    }

    public ObjectiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_objective, container, false);


        /*
         * Weight
         */
        weightSeekbar = (ColoredSeekBar) rootView.findViewById(R.id.weight_seekbar);
        weightText = (TextView) rootView.findViewById(R.id.weight_text);
        weightButtonMin = (Button) rootView.findViewById(R.id.weight_min);
        weightButtonPlus = (Button) rootView.findViewById(R.id.weight_plus);
        weightSeekbar.setOnSeekBarChangeListener(new ColoredSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                weightText.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        weightButtonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(weightText.getText().toString());
                if (value > 0) {
                    weightText.setText((value - 1) + "");
                    weightSeekbar.setProgress(value - 1);
                }
            }
        });
        weightButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(weightText.getText().toString());
                weightText.setText((value + 1) + "");
                weightSeekbar.setProgress(value + 1);

            }
        });
        weightSeekbar.setProgress(70);


        /*
         * Blood pressure
         */
        bloodPressureSeekbar = new RangeSeekBar<Integer>(0, 200, getActivity());
        bloodPressureHighMin = (Button) rootView.findViewById(R.id.blood_pressure_high_min);
        bloodPressureHighPlus = (Button) rootView.findViewById(R.id.blood_pressure_high_plus);
        bloodPressureHighText = (TextView) rootView.findViewById(R.id.blood_pressure_high_text);
        bloodPressureLowMin = (Button) rootView.findViewById(R.id.blood_pressure_low_min);
        bloodPressureLowPlus = (Button) rootView.findViewById(R.id.blood_pressure_low_plus);
        bloodPressureLowText = (TextView) rootView.findViewById(R.id.blood_pressure_low_text);

        bloodPressureSeekbar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                bloodPressureLowText.setText(minValue + "");
                bloodPressureHighText.setText(maxValue + "");
            }
        });
        bloodPressureLowMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = bloodPressureSeekbar.getSelectedMinValue();
                if(value > bloodPressureSeekbar.getAbsoluteMinValue()) {
                    bloodPressureLowText.setText((value - 1) + "");
                    bloodPressureSeekbar.setSelectedMinValue(value - 1);
                }
            }
        });
        bloodPressureLowPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = bloodPressureSeekbar.getSelectedMinValue();
                if(value < bloodPressureSeekbar.getSelectedMaxValue()) {
                    bloodPressureLowText.setText((value + 1) + "");
                    bloodPressureSeekbar.setSelectedMinValue(value + 1);
                }
            }
        });

        bloodPressureHighMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = bloodPressureSeekbar.getSelectedMaxValue();
                if(value > bloodPressureSeekbar.getSelectedMinValue()) {
                    bloodPressureHighText.setText((value - 1) + "");
                    bloodPressureSeekbar.setSelectedMaxValue(value - 1);
                }
            }
        });
        bloodPressureHighPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = bloodPressureSeekbar.getSelectedMaxValue();
                if(value < bloodPressureSeekbar.getAbsoluteMaxValue()) {
                    bloodPressureLowText.setText((value + 1) + "");
                    bloodPressureSeekbar.setSelectedMaxValue(value + 1);
                }
            }
        });
        bloodPressureSeekbar.setSelectedMinValue(80);
        bloodPressureSeekbar.setSelectedMaxValue(120);

        ViewGroup layout = (ViewGroup) rootView.findViewById(R.id.blood_pressure_seekbar);
        layout.addView(bloodPressureSeekbar);

        /*
         * Length
         */
        lengthSeekbar = (SeekBar) rootView.findViewById(R.id.length_seekbar);
        lenghtText = (TextView) rootView.findViewById(R.id.length_text);
        lengthButtonMin = (Button) rootView.findViewById(R.id.length_min);
        lengthButtonPlus = (Button) rootView.findViewById(R.id.length_plus);
        lengthSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lenghtText.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        lengthButtonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(lenghtText.getText().toString());
                if (value > 0) {
                    lenghtText.setText((value - 1) + "");
                    lengthSeekbar.setProgress(value - 1);
                }
            }
        });
        lengthButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(lenghtText.getText().toString());
                lenghtText.setText((value + 1) + "");
                lengthSeekbar.setProgress(value + 1);
            }
        });
        lengthSeekbar.setProgress(180);

        return rootView;
    }
}
