package com.pratamawijaya.recyclerviewinaction.ui.staggeredlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pratamawijaya.recyclerviewinaction.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaggeredLayoutFragment extends Fragment {

  public StaggeredLayoutFragment() {
    // Required empty public constructor
  }

  public static StaggeredLayoutFragment newInstance() {
    Bundle args = new Bundle();
    StaggeredLayoutFragment fragment = new StaggeredLayoutFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_staggered_layout, container, false);
  }
}
