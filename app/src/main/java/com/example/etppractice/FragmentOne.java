package com.example.etppractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_one,container,false);
        final EditText editText=view.findViewById(R.id.frag1_edit);
        Button button=view.findViewById(R.id.frag1_send_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=editText.getText().toString();
                Bundle bundle=new Bundle();
                bundle.putString("k1",s1);

                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();

                FragmentTwo fragment2=new FragmentTwo();
                fragment2.setArguments(bundle);

                ft.replace(R.id.frame_layout,fragment2);
                ft.commit();
            }
        });

        return view;
    }

}
