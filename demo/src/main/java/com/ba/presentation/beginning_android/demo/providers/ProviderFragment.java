package com.ba.presentation.beginning_android.demo.providers;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ba.presentation.beginning_android.demo.R;

public class ProviderFragment extends Fragment {

    private Button mButtonFindContact;
    private EditText mEditTextContactName;
    private TextView mTextViewDisplayName;

    public ProviderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content_provider, container, false);

        mEditTextContactName = (EditText) rootView.findViewById(R.id.et_contact_name);
        mTextViewDisplayName = (TextView) rootView.findViewById(R.id.tv_found_contact_view);

        mButtonFindContact = (Button) rootView.findViewById(R.id.btn_find_contact);
        mButtonFindContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contactName = ProviderFragment.this.findContactByName(mEditTextContactName.getText().toString(), getActivity());
                mTextViewDisplayName.setText(contactName);
            }
        });

        return rootView;
    }

    public String findContactByName(String name, Context context) {
        String ret = null;
        String selection = ContactsContract.Contacts.DISPLAY_NAME + " like'%" + name +"%'";
        String[] projection = new String[] { ContactsContract.Contacts.DISPLAY_NAME};
        Cursor c = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            ret = c.getString(0);
        }
        c.close();
        if(ret==null) {
            ret = "Not found";
        }
        return ret;
    }

}
