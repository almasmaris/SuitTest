package com.id.maris.suittest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.id.maris.suittest.model.EventModel;

public class EventFormActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView nameTextView;
    private String selectedEvent;
    private String selectedGuest;
    private int selectedGuestBirthdate;
    private Button chooseEventButton;
    private Button chooseGuestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

        Intent intent = getIntent();

        //set actionbar/toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        nameTextView.setText("Nama: " + intent.getStringExtra("name"));

        chooseEventButton = (Button) findViewById(R.id.chooseEvent_button);
        chooseGuestButton = (Button) findViewById(R.id.chooseGuest_button);

        chooseEventButton.setOnClickListener(onChooseEventClickListener);
        chooseGuestButton.setOnClickListener(onChooseGuestClickListerner);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case android.R.id.home:
                //perhaps use intent if needed but i'm sure there's a specific intent action for up you can use to handle
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private final View.OnClickListener onChooseEventClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), EventActivity.class);
            startActivityForResult(intent, 1);
        }
    };

    private final View.OnClickListener onChooseGuestClickListerner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), GuestActivity.class);
            startActivityForResult(intent, 2);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                selectedEvent = data.getStringExtra("choosedEvent");
                chooseEventButton.setText(selectedEvent);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                selectedGuest = data.getStringExtra("choosedGuest");
                chooseGuestButton.setText(selectedGuest);

                String birthdate = data.getStringExtra("birthdate");
                String substring = birthdate.substring(Math.max(birthdate.length() - 2, 0));
                selectedGuestBirthdate = Integer.valueOf(substring);

                if(selectedGuestBirthdate % 2 == 0 && selectedGuestBirthdate % 3 == 0){
                    Toast.makeText(this, "iOS", Toast.LENGTH_SHORT).show();
                }else if(selectedGuestBirthdate % 2 == 0 ){
                    Toast.makeText(this, "Blackberry", Toast.LENGTH_SHORT).show();
                }else if(selectedGuestBirthdate % 3 == 0){
                    Toast.makeText(this, "Android", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "feature phone", Toast.LENGTH_SHORT).show();
                }


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


}
