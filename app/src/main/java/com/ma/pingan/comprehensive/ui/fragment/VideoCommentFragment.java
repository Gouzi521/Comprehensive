package com.ma.pingan.comprehensive.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma.pingan.comprehensive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoCommentFragment extends Fragment {


    public VideoCommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_comment, container, false);
    }

}
