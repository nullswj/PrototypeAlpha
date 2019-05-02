package com.swj.prototypealpha.swj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.swj.prototypealpha.R;


public class WordFragment extends Fragment {

    public static TextView text_word_foundation;

    public static TextView text_word_record;

    EditText edit_word_foundation;

    EditText edit_word_question;

    FloatingActionButton  fab_foundation_submit;

    FloatingActionButton fab_record_submit;

    private void initUI()
    {
        text_word_foundation = getActivity().findViewById(R.id.word_foundation_lable);
        text_word_record = getActivity().findViewById(R.id.word_question_record_lable);

        edit_word_foundation = getActivity().findViewById(R.id.word_doundation_edit);
        edit_word_foundation.setText("现场情况符合要求");
        edit_word_question = getActivity().findViewById(R.id.word_question_record_edit);
        edit_word_question.setText("要求1：XXXXXXXXXXXXXXXXXXXX"+
                "要求2：XXXXXXXXXXXXXXXXX");

        fab_record_submit = getActivity().findViewById(R.id.flb_word_question_record_submit);
        fab_foundation_submit = getActivity().findViewById(R.id.flb_word_foundation_submit);

    }

    private void setOnClickListener()
    {
        fab_foundation_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foundation = edit_word_foundation.getText().toString()+'\n';
                String old_foundation = text_word_foundation.getText().toString();
                text_word_foundation.setText(old_foundation+foundation);

            }
        });
        fab_record_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String record = edit_word_question.getText().toString()+'\n';
                String old_rocord = text_word_record.getText().toString();
                text_word_record.setText(old_rocord+record);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        setOnClickListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_word, container, false);
    }


}
