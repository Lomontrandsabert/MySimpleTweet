package com.codepath.apps.mysimpletweets;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentDialog extends android.support.v4.app.DialogFragment{
    private ImageView ivProfilePhoto;
    private TextView tvName;
    private EditText etTweet;
    private Button btnSend;

    public ImageView getIvProfilePhoto() {
        return ivProfilePhoto;
    }

    public TextView getTvName() {
        return tvName;
    }

    public EditText getEtTweet() {
        return etTweet;
    }

    public Button getBtnSend() {
        return btnSend;
    }

    public FragmentDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static FragmentDialog newInstance(String title) {
        FragmentDialog frag = new FragmentDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tweet, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        ivProfilePhoto = (ImageView) view.findViewById(R.id.ivProfilePhoto);
        tvName = (TextView)view.findViewById(R.id.tvName);
        etTweet = (EditText) view.findViewById(R.id.etTweet);
        btnSend = (Button) view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), etTweet.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "title");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        etTweet.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
