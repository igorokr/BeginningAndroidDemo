package com.ba.presentation.beginning_android.demo.receiver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.ba.presentation.beginning_android.demo.R;

import java.text.SimpleDateFormat;

public class ReceiverFragment extends Fragment {

    private Button mButtonStart;
    private Button mButtonStop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_broadcast_reciever, container, false);

        mButtonStart = (Button) rootView.findViewById(R.id.btn_start);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmReceiver.start(getActivity());
            }
        });

        mButtonStop = (Button) rootView.findViewById(R.id.btn_stop);
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmReceiver.stop(getActivity());
            }
        });

        return rootView;
    }

}
