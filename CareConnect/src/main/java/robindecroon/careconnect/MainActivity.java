package robindecroon.careconnect;

import android.app.ActionBar;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

;import java.util.List;

import robindecroon.careconnect.factory.DummyMessageFactory;
import robindecroon.careconnect.messages.Message;
import robindecroon.careconnect.ui.CareConnectNavigationDrawerFragment;
import robindecroon.careconnect.ui.soap.SOAPFragment;
import robindecroon.careconnect.ui.dashboard.DashboardFragment;
import robindecroon.careconnect.ui.messages.MessageContentFragment;
import robindecroon.careconnect.ui.messages.MessageTypeDropdownFragment;
import robindecroon.careconnect.ui.messages.MessagesFragment;
import robindecroon.careconnect.ui.messages.MessagesListFragment;
import robindecroon.careconnect.ui.profile.ProfileFragment;

public class MainActivity extends FragmentActivity
        implements CareConnectNavigationDrawerFragment.NavigationDrawerCallbacks, MessagesListFragment.OnMessageListInteractionListener, MessageTypeDropdownFragment.Callbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private CareConnectNavigationDrawerFragment mCareConnectNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    private List<Message> messages = DummyMessageFactory.getDummyMessages();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCareConnectNavigationDrawerFragment = (CareConnectNavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mCareConnectNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(position == 0) {
            fragmentManager.beginTransaction().replace(R.id.container, ProfileFragment.newInstance()).commit();
        } else if (position == 2) {
            fragmentManager.beginTransaction().replace(R.id.container, MessagesFragment.newInstance()).commit();
        } else if (position == 3) {
            fragmentManager.beginTransaction().replace(R.id.container, SOAPFragment.newInstance()).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.container, DashboardFragment.newInstance()).commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mCareConnectNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.dashboard, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_camera) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 0);
        } else if(id == R.id.action_calendar) {
            // A date-time specified in milliseconds since the epoch.
            Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
            builder.appendPath("time");
            ContentUris.appendId(builder, System.currentTimeMillis());
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMessageListItemSelected(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rightpane, MessageContentFragment.newInstance(messages.get(id))).commit();
    }

    @Override
    public void onTrackSelected(String trackId) {

    }

    @Override
    public void onTrackNameAvailable(String trackId, String trackName) {

    }
}
