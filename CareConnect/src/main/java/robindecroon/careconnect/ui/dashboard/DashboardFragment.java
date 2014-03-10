package robindecroon.careconnect.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;

import java.util.Arrays;

import robindecroon.careconnect.DashboardAdapter;
import robindecroon.careconnect.MainActivity;
import robindecroon.careconnect.R;


public class DashboardFragment extends Fragment {

    private GridView mDashboardGrid;
    private View rootView;

    public final static String MEDICATION = "medicatie";
    public final static String PROBLEMS = "problemen";
    public final static String ALLERGENS = "allergenen";
    public final static String INTOLERANCES = "intoleranties";
    public final static String HISTORY = "antecedenten";
    public final static String LAST_LAB_RESULTS = "laatste_labo_resultaten";



    private static final String ARG_SECTION_NUMBER = "section_number";

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 2);
        fragment.setArguments(args);
        return fragment;
    }

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Display display = ((WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        // check display size to figure out what image resolution will be loaded
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
        }

        height -= actionBarHeight;
        height -= result;

        int nbColumns = (int) getResources().getInteger(R.integer.number_of_columns);
        int nbRows = (int) getResources().getInteger(R.integer.number_of_rows);

        int dashboardElementWidth = width / nbColumns;
        int dashboardElementHeight = height / nbRows;

        mDashboardGrid = (GridView) rootView.findViewById(R.id.dashboard_grid);
        mDashboardGrid.setAdapter(new DashboardAdapter(getActivity(), Arrays.asList(new String[]{MEDICATION, PROBLEMS, ALLERGENS, INTOLERANCES, HISTORY, LAST_LAB_RESULTS}), dashboardElementWidth, dashboardElementHeight));

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
