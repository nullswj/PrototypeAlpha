package com.swj.prototypealpha.swj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swj.prototypealpha.R;
import com.venusic.handwrite.view.HandWriteView;


public class LookupFragment extends Fragment {
    FloatingActionButton fabtn_lookup;
    HandWriteView handWriteView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lookup, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fabtn_lookup = getActivity().findViewById(R.id.fabtn_clear);
        handWriteView = getActivity().findViewById(R.id.handw_view);

        fabtn_lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handWriteView.clear();
            }
        });
    }
}
