package robindecroon.careconnect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

public class SOEPFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SOEPFragment newInstance() {
        SOEPFragment fragment = new SOEPFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 4);
        fragment.setArguments(args);
        return fragment;
    }
    public SOEPFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.simple_tabs, container, false);
        //Set the pager with an adapter
        final ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        final String[] testArray = getResources().getStringArray(R.array.soep);
        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return SubjectiveFragment.newInstance();
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
        indicator.setCurrentItem(0);

        return rootView;
    }

}
