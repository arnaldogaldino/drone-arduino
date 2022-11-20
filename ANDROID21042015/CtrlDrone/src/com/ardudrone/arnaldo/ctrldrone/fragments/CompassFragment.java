package com.ardudrone.arnaldo.ctrldrone.fragments;

import com.ardudrone.arnaldo.ctrldrone.CtrlActivity;
import com.ardudrone.arnaldo.ctrldrone.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class CompassFragment  extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static CompassFragment newInstance(int sectionNumber) {
    	CompassFragment fragment = new CompassFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_compass, container, false);
        setHasOptionsMenu(true);
        rootView.getContext();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CtrlActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	 getActivity().getSupportFragmentManager();
         switch (item.getItemId()) {
            default:
                 return super.onOptionsItemSelected(item);
         }
    }    
}
