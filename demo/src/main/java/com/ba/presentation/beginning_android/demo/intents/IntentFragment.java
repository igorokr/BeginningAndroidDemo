package com.ba.presentation.beginning_android.demo.intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ba.presentation.beginning_android.demo.MainActivity;
import com.ba.presentation.beginning_android.demo.R;
import org.apache.http.protocol.HTTP;

public class IntentFragment extends Fragment {

    public IntentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_intent, container, false);

        Button btnOpenSettings = (Button) rootView.findViewById(R.id.btn_open_settings);
        btnOpenSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class); //TODO: start settings activity
                startActivity(intent);
            }
        });

        final EditText editText = (EditText) rootView.findViewById(R.id.et_share);
        Button btnShareText = (Button) rootView.findViewById(R.id.btn_share_text);
        btnShareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the text message with a string
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
                // TODO: add more type examples here
                sendIntent.setType(HTTP.PLAIN_TEXT_TYPE); // "text/plain" MIME type

                // Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(sendIntent);
                }
            }
        });


        return rootView;
    }
}
