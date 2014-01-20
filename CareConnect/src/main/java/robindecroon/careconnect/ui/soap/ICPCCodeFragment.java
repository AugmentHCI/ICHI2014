package robindecroon.careconnect.ui.soap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 15/01/14.
 */
public class ICPCCodeFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_icpc_chapter, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.icpc_gridview);
        gridview.setAdapter(new ICPCCodeAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public static ICPCCodeFragment newInstance() {
        return new ICPCCodeFragment();

    }

    public class ICPCCodeAdapter extends BaseAdapter {
        private Context mContext;
        private String[] chapters;

        private boolean[] enabledButtons;

        public ICPCCodeAdapter(Context c) {
            mContext = c;
            chapters = getResources().getStringArray(R.array.icpc_a);
            enabledButtons = new boolean[chapters.length];
            for (int i = 0; i < enabledButtons.length; i++)
                enabledButtons[i] = true;
        }

        public int getCount() {
            return chapters.length;
        }

        public Object getItem(int position) {
            return chapters[position];
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, final ViewGroup parent) {
            final Button button = new Button(getActivity());
            final String text = (String) getItem(position);
            final int pos = position;
            button.setEnabled(enabledButtons[pos]);
            button.setText(text);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = getChildFragmentManager();
                    EvaluationFragment parentFragment = (EvaluationFragment) getParentFragment();
                    if (parentFragment != null) {
                        parentFragment.addText(text);
                        enabledButtons[pos] = false;
                        button.setEnabled(false);
                    }

                }
            });
            return button;
        }

    }
}
