package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import robindecroon.careconnect.R;
import robindecroon.careconnect.libraries.RangeSeekBar;
import robindecroon.careconnect.util.ColoredSeekBar;

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

    private ColoredSeekBar haertrateSeekbar;
    private TextView haertrateText;
    private Button haertrateButtonMin;
    private Button haertrateButtonPlus;

    private ColoredSeekBar temperatureSeekbar;
    private TextView temperatureText;
    private Button temperatureButtonMin;
    private Button temperatureButtonPlus;

    private ColoredSeekBar blood_sugarSeekbar;
    private TextView blood_sugarText;
    private Button blood_sugarButtonMin;
    private Button blood_sugarButtonPlus;


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
                if (value > bloodPressureSeekbar.getAbsoluteMinValue()) {
                    bloodPressureLowText.setText((value - 1) + "");
                    bloodPressureSeekbar.setSelectedMinValue(value - 1);
                }
            }
        });
        bloodPressureLowPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = bloodPressureSeekbar.getSelectedMinValue();
                if (value < bloodPressureSeekbar.getSelectedMaxValue()) {
                    bloodPressureLowText.setText((value + 1) + "");
                    bloodPressureSeekbar.setSelectedMinValue(value + 1);
                }
            }
        });

        bloodPressureHighMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = bloodPressureSeekbar.getSelectedMaxValue();
                if (value > bloodPressureSeekbar.getSelectedMinValue()) {
                    bloodPressureHighText.setText((value - 1) + "");
                    bloodPressureSeekbar.setSelectedMaxValue(value - 1);
                }
            }
        });
        bloodPressureHighPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = bloodPressureSeekbar.getSelectedMaxValue();
                if (value < bloodPressureSeekbar.getAbsoluteMaxValue()) {
                    bloodPressureLowText.setText((value + 1) + "");
                    bloodPressureSeekbar.setSelectedMaxValue(value + 1);
                }
            }
        });
        // set values in onResume to force onDraw() method to color the lines.

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

        /*
         * haertrate
         */
        haertrateSeekbar = (ColoredSeekBar) rootView.findViewById(R.id.haertrate_seekbar);
        haertrateText = (TextView) rootView.findViewById(R.id.haertrate_text);
        haertrateButtonMin = (Button) rootView.findViewById(R.id.haertrate_min);
        haertrateButtonPlus = (Button) rootView.findViewById(R.id.haertrate_plus);
        haertrateSeekbar.setOnSeekBarChangeListener(new ColoredSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                haertrateText.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        haertrateButtonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(haertrateText.getText().toString());
                if (value > 0) {
                    haertrateText.setText((value - 1) + "");
                    haertrateSeekbar.setProgress(value - 1);
                }
            }
        });
        haertrateButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(haertrateText.getText().toString());
                haertrateText.setText((value + 1) + "");
                haertrateSeekbar.setProgress(value + 1);

            }
        });
        haertrateSeekbar.setProgress(70);

        /*
         * temperature
         */
        temperatureSeekbar = (ColoredSeekBar) rootView.findViewById(R.id.temperature_seekbar);
        temperatureText = (TextView) rootView.findViewById(R.id.temperature_text);
        temperatureButtonMin = (Button) rootView.findViewById(R.id.temperature_min);
        temperatureButtonPlus = (Button) rootView.findViewById(R.id.temperature_plus);
        temperatureSeekbar.setOnSeekBarChangeListener(new ColoredSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                temperatureText.setText((i / 10 + 20f) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        temperatureButtonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(temperatureText.getText().toString());
                value = (value - 20f);
                if (value > 0) {
                    temperatureText.setText(((int) value - 1) + "");
                    temperatureSeekbar.setProgress((int) value - 1);
                }
            }
        });
        temperatureButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(temperatureText.getText().toString());
                value = (value - 20f);
                temperatureText.setText(((int) value + 1) + "");
                temperatureSeekbar.setProgress((int) value + 1);
            }
        });
        temperatureSeekbar.setProgress(160);

                /*
         * blood_sugar
         */
        blood_sugarSeekbar = (ColoredSeekBar) rootView.findViewById(R.id.blood_sugar_seekbar);
        blood_sugarText = (TextView) rootView.findViewById(R.id.blood_sugar_text);
        blood_sugarButtonMin = (Button) rootView.findViewById(R.id.blood_sugar_min);
        blood_sugarButtonPlus = (Button) rootView.findViewById(R.id.blood_sugar_plus);
        blood_sugarSeekbar.setOnSeekBarChangeListener(new ColoredSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                blood_sugarText.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        blood_sugarButtonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(blood_sugarText.getText().toString());
                if (value > 0) {
                    blood_sugarText.setText((value - 1) + "");
                    blood_sugarSeekbar.setProgress(value - 1);
                }
            }
        });
        blood_sugarButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(blood_sugarText.getText().toString());
                blood_sugarText.setText((value + 1) + "");
                blood_sugarSeekbar.setProgress(value + 1);
            }
        });
        blood_sugarSeekbar.setProgress(70);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        bloodPressureSeekbar.setSelectedMaxValue(120);
        bloodPressureSeekbar.setSelectedMinValue(80);
    }
}
