package robindecroon.careconnect.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import robindecroon.careconnect.Constants;
import robindecroon.careconnect.MainActivity;
import robindecroon.careconnect.R;
import robindecroon.careconnect.util.ProfileDataGenerator;


public class ProfileFragment extends Fragment {

    private MapView mapView;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String ARG_FULLNAME = "profile_fullname";
    private static final String ARG_GENDER = "profile_gender";
    private static final String ARG_INSZ = "profile_insz";
    private static final String ARG_BIRTHDAY = "profile_birthday";

    private String mFullName;
    private String mGender;
    private String mInsz;
    private String mBirthday;


    public static ProfileFragment newInstance(String fullname, String insz, String gender, String birthday) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 1);
        args.putString(ARG_FULLNAME, fullname);
        args.putString(ARG_BIRTHDAY, birthday);
        args.putString(ARG_GENDER, gender);
        args.putString(ARG_INSZ,insz);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mFullName = getArguments().getString(ARG_FULLNAME);
            mGender = getArguments().getString(ARG_GENDER);
            mInsz = getArguments().getString(ARG_INSZ);
            mBirthday = getArguments().getString(ARG_BIRTHDAY);
        } else {
            mFullName = Constants.PATIENT_NAME;
            mGender = Constants.PATIENT_GENDER;
            mBirthday = ProfileDataGenerator.getRandomDate();
            mInsz = ProfileDataGenerator.getRandomINSZ(mBirthday);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        initGoogleMaps(savedInstanceState, rootView);

        initPatient(rootView);

        return rootView;
    }

    private void initPatient(View rootView) {
        TextView fullname = (TextView) rootView.findViewById(R.id.profile_fullname);
        fullname.setText(mFullName);

        TextView gender = (TextView) rootView.findViewById(R.id.profile_gender);
        gender.setText(mGender);

        TextView birthday = (TextView) rootView.findViewById(R.id.profile_birtday);
        birthday.setText(mBirthday);

        TextView insz = (TextView) rootView.findViewById(R.id.profile_insz);
        insz.setText(mInsz);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.profile_image);
        String uri = "drawable/";
        uri += mFullName.replaceAll(" ", "").toLowerCase();
        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
        imageView.setImageDrawable(getResources().getDrawable(imageResource));
    }

    private void initGoogleMaps(Bundle savedInstanceState, View rootView) {
        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        GoogleMap map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(50.866667, 4.7), 17);
        map.animateCamera(cameraUpdate);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
