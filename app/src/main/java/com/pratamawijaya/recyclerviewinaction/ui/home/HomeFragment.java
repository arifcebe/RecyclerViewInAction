package com.pratamawijaya.recyclerviewinaction.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pratamawijaya.recyclerviewinaction.R;
import com.pratamawijaya.recyclerviewinaction.base.BaseApplication;
import com.pratamawijaya.recyclerviewinaction.event.HomeClick;
import com.pratamawijaya.recyclerviewinaction.ui.adapter.TextAdapter;
import com.pratamawijaya.recyclerviewinaction.utils.RxBus;
import com.pratamawijaya.recyclerviewinaction.utils.SimpleDividerItemDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements TextAdapter.ClickListener {

  @BindView(R.id.recycler_view) RecyclerView recyclerView;

  private TextAdapter adapter;
  private List<String> listMenu;
  private RxBus rxBus;

  public HomeFragment() {
    // Required empty public constructor
  }

  public static HomeFragment newInstance() {
    Bundle args = new Bundle();
    HomeFragment fragment = new HomeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    rxBus = BaseApplication.getRxBus();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_home, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    setupRecyclerView();
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  private void setupRecyclerView() {
    // default use VERTICAL orientation
    listMenu = new ArrayList<>();
    listMenu.add("LinearlayoutManager");
    listMenu.add("GridLayoutManager");
    listMenu.add("StaggeredLayoutManager");
    listMenu.add("Animation");
    listMenu.add("Item Decoration");

    adapter = new TextAdapter(getActivity(), listMenu, this);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(adapter);
    recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
  }

  @Override public void onItemClick(int pos) {
    rxBus.send(new HomeClick(pos));
  }
}
