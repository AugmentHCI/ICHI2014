package robindecroon.careconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import robindecroon.careconnect.util.ProfileDataGenerator;

/**
 * Created by robindecroon on 04/03/14.
 */
public class ContactAdapter extends BaseAdapter {


    private Context context;
    private List<String> names;

    public ContactAdapter(Context context, List<String> names) {
        this.names = names;
        this.context = context;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_contact, null);

        String fullName = (String) getItem(i);

        TextView nameView = (TextView) view.findViewById(R.id.contact_full_name);
        nameView.setText(fullName);

        TextView birthdayView = (TextView) view.findViewById(R.id.contact_birthday);
        String birthday = ProfileDataGenerator.getRandomDate();
        birthdayView.setText(birthday);

        TextView inszView = (TextView) view.findViewById(R.id.contact_insz);
        inszView.setText(ProfileDataGenerator.getRandomINSZ(birthday));

        TextView genderView = (TextView) view.findViewById(R.id.contact_gender);
        genderView.setText("Man");

        ImageView profilePicView = (ImageView) view.findViewById(R.id.contact_image);

        String uri = "drawable/";

        uri += fullName.replaceAll(" ", "").toLowerCase();

        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        profilePicView.setImageDrawable(context.getResources().getDrawable(imageResource));

        return view;

    }
}
