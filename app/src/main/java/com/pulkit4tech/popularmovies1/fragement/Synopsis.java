package com.pulkit4tech.popularmovies1.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pulkit4tech.popularmovies1.R;
import com.pulkit4tech.popularmovies1.data.Data_item;

/**
 * A simple {@link Fragment} subclass.
 */
public class Synopsis extends Fragment {
    Data_item data;
    public Synopsis() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_synopsis, container, false);
        TextView synopsis = (TextView) view.findViewById(R.id.synopsis);
        data = (Data_item) getActivity().getIntent().getParcelableExtra("my_data");
        synopsis.setText(data.overview);
        return view;
    }

}
