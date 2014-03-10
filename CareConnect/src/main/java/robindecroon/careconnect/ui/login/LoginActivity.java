package robindecroon.careconnect.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import robindecroon.careconnect.Constants;
import robindecroon.careconnect.MainActivity;
import robindecroon.careconnect.R;

public class LoginActivity extends Activity {

    private TextView mFullName;
    private TextView mINSZ;
    private TextView mGender;
    private TextView mBirthdate;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final EditText pinInput = (EditText) findViewById(R.id.pincode_input);
        pinInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if(text.equals("1")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if(getIntent().getExtras() != null) {
                        intent.putExtra(Constants.FULL_NAME, mFullName.getText());
                        intent.putExtra(Constants.BIRTHDAY, mBirthdate.getText());
                        intent.putExtra(Constants.GENDER,mGender.getText());
                        intent.putExtra(Constants.INSZ, mINSZ.getText());
                    }
                    startActivity(intent);
                }
            }
        });

        for(int i = 0; i < 10; i++) {
            int resourceId = this.getResources().getIdentifier("btn" + i, "id", getPackageName());
//            Log.i("MYAG",""+ R.id.btn0);
            Button button = (Button) findViewById(resourceId);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String old = String.valueOf(pinInput.getText());
                    pinInput.setText(old + finalI);
                }
            });


            Button cancelButton = (Button) findViewById(R.id.cancelbutton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pinInput.setText("");
                }
            });
        }

        mFullName = (TextView) findViewById(R.id.login_fullname);
        mGender = (TextView) findViewById(R.id.login_gender);
        mINSZ = (TextView) findViewById(R.id.login_insz);
        mBirthdate = (TextView) findViewById(R.id.login_birthdate);
        mImage = (ImageView) findViewById(R.id.login_image);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String fullName = extras.getString(Constants.FULL_NAME);

            mFullName.setText(fullName);
            mGender.setText(extras.getString(Constants.GENDER));
            mINSZ.setText(extras.getString(Constants.INSZ));
            mBirthdate.setText(extras.getString(Constants.BIRTHDAY));

            String uri = "drawable/";
            uri += fullName.replaceAll(" ", "").toLowerCase();
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            mImage.setImageDrawable(getResources().getDrawable(imageResource));
        }
    }
}