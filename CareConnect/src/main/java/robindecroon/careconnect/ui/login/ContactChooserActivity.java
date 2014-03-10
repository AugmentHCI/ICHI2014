package robindecroon.careconnect.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Arrays;

import robindecroon.careconnect.Constants;
import robindecroon.careconnect.ContactAdapter;
import robindecroon.careconnect.R;

public class ContactChooserActivity extends Activity {

    private final static String NAME1 = "Piet Piraat";
    private final static String NAME2 = "Kabouter Plop";
    private final static String NAME3 = "Darkwing Duck";
    private final static String NAME4 = "Donald Duck";
    private final static String NAME5 = "Lotso de Knuffelbeer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_chooser);

        final String[] names = new String[]{NAME1,NAME2,NAME3,NAME4,NAME5};

        GridView grid = (GridView) findViewById(R.id.contact_grid);
        grid.setAdapter(new ContactAdapter(this, Arrays.asList(names) ));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(ContactChooserActivity.this, LoginActivity.class);
                intent.putExtra(Constants.FULL_NAME,names[position]);

                TextView birthday = (TextView) v.findViewById(R.id.contact_birthday);
                intent.putExtra(Constants.BIRTHDAY, birthday.getText());

                TextView insz = (TextView) v.findViewById(R.id.contact_insz);
                intent.putExtra(Constants.INSZ, insz.getText());

                TextView gender = (TextView) v.findViewById(R.id.contact_gender);
                intent.putExtra(Constants.GENDER, gender.getText());

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
