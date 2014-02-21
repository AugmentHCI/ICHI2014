package robindecroon.careconnect.ui.soap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

import robindecroon.careconnect.MainActivity;
import robindecroon.careconnect.R;

public class SOAPFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SELECTION = "selection_number";


    private int mSelection;

    public static SOAPFragment newInstance() {
        SOAPFragment fragment = new SOAPFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 4);
        fragment.setArguments(args);
        return fragment;
    }

    public SOAPFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_SELECTION, mSelection);
        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_soep, container, false);
        //Set the pager with an adapter
        final ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        final String[] testArray = getResources().getStringArray(R.array.soep);
        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                mSelection = i;
                if (i == 0)
                    return SubjectiveFragment.newInstance();
                else if (i == 1)
                    return ObjectiveFragment.newInstance();
                else if (i == 2)
                    return EvaluationFragment.newInstance(getResources().getString(R.string.icpc_chapters));
                else
                    return PlanningFragment.newInstance();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return testArray[position];
            }

            @Override
            public int getCount() {
                return testArray.length;
            }
        });


        TabPageIndicator indicator = (TabPageIndicator) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        if (savedInstanceState != null) {
            mSelection = savedInstanceState.getInt(ARG_SELECTION);
            indicator.setCurrentItem(mSelection);
        }

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
