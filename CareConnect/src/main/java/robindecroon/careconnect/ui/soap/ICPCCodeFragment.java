package robindecroon.careconnect.ui.soap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import robindecroon.careconnect.R;

/**
 * Created by robindecroon on 15/01/14.
 */
public class ICPCCodeFragment extends Fragment {

    private static final String ARG_PARAM1 = "chapter";

    private View rootView;

    private String mChapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_icpc_chapter, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.icpc_gridview);
        String[] chapters = getResources().getStringArray(R.array.icpc_chapters);

        gridview.setAdapter(new ICPCCodeAdapter(mChapter));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public static ICPCCodeFragment newInstance(String chapter) {
        ICPCCodeFragment fragment = new ICPCCodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, chapter);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChapter = getArguments().getString(ARG_PARAM1);
        }
    }

    /**
     * Read csv file.
     *
     * @return the map
     * @throws java.io.IOException Signals that an I/O exception has occurred.
     */
    public Map<String,List<String>> readICPCCSVFile() throws IOException {
        InputStream input = getActivity().getAssets().open("icpc.csv");
        BufferedReader in = new BufferedReader(new InputStreamReader(input));

        String[] chapters = getResources().getStringArray(R.array.icpc_chapters);

        Map<String, List<String>> lists = new HashMap<String, List<String>>();
        for(String chapter: chapters) {
            lists.put(chapter, new ArrayList<String>());
        }

        try {
            in.readLine();
            String line;
            while ((line = in.readLine()) != null) {
                try {
                    String[] values = line.split(";");
                    String chapter = chapters[Integer.parseInt(values[0]) - 1];
                    String code = values[1];
                    String description = values[2];
                    lists.get(chapter).add(code + " "  + description);
                } catch (Exception e) {
                    Log.e("ICPCParser", "Error in country CSV file!");
                }
            }
        } catch (Exception e) {
            Log.e("ICPCParser", "Error in country CSV file!");
        }
        return lists;
    }

    public class ICPCCodeAdapter extends BaseAdapter {
        private List<String> codes;

        private boolean[] enabledButtons;

        public ICPCCodeAdapter(String chapter) {
            try {
                codes = readICPCCSVFile().get(chapter);
            } catch (IOException e) {
                e.printStackTrace();
            }
            enabledButtons = new boolean[codes.size()];
            for (int i = 0; i < enabledButtons.length; i++)
                enabledButtons[i] = true;
        }

        public int getCount() {
            return codes.size();
        }

        public Object getItem(int position) {
            return codes.get(position);
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
            button.setHeight(60);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
