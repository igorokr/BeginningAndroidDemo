package com.ba.presentation.beginning_android.demo.intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.ba.presentation.beginning_android.demo.R;

public class DisplaySharedTextActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_shared_text);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);

        TextView displayTextView = (TextView) findViewById(R.id.tv_shared_text);
        displayTextView.setText(text);
    }

}
