package robindecroon.careconnect.ui.soap;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class ICPCChapterFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_icpc_chapter, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.icpc_gridview);
        gridview.setAdapter(new ICPCAdapter());


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public class ICPCAdapter extends BaseAdapter {
        private String[] chapters;

        public ICPCAdapter() {
            chapters = getResources().getStringArray(R.array.icpc_chapters);
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button = new Button(getActivity());
            final String text = (String) getItem(position);
            button.setText(text);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = getFragmentManager();

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                    ICPCCodeFragment newCustomFragment = ICPCCodeFragment.newInstance((String) getItem(position));
                    transaction.replace(R.id.icpc_chapter_fragment, newCustomFragment);
                    transaction.commit();
                }
            });
            return button;
        }
    }
}
