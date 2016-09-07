package com.id.maris.suittest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText nameInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set actionbar/toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameInputText = (EditText)findViewById(R.id.nameEditText);


        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInputText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), EventFormActivity.class);
                if(!TextUtils.isEmpty(name)){
                    intent.putExtra("name", name);
                    startActivity(intent);
                }else{
                    nameInputText.setError("Harus diisi");
                }


            }
        });

    }


}
