package com.pratamawijaya.recyclerviewinaction.ui.linearlayout;

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
import com.pratamawijaya.recyclerviewinaction.data.DataWarehouse;
import com.pratamawijaya.recyclerviewinaction.ui.adapter.LinearHorizontalAdapter;
import com.pratamawijaya.recyclerviewinaction.ui.adapter.LinearVerticalAdapter;
import com.pratamawijaya.recyclerviewinaction.ui.adapter.TextAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinearLayoutFragment extends Fragment implements TextAdapter.ClickListener {

  private static final int POS_VERTICAL = 0;
  private static final int POS_HORIZONTAL = 1;

  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  private TextAdapter textAdapter;
  private LinearVerticalAdapter linearAdapter;
  private LinearHorizontalAdapter linearHorizontalAdapter;

  private List<String> listMenu;
  private DataWarehouse dataWarehouse;

  private LinearLayoutManager linearLayoutManagerVertical;
  private LinearLayoutManager linearLayoutManagerHorizontal;

  public static LinearLayoutFragment newInstance() {
    Bundle args = new Bundle();
    LinearLayoutFragment fragment = new LinearLayoutFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public LinearLayoutFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    dataWarehouse = new DataWarehouse();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_linear_layout, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    setupRecyclerView();
  }

  private void setupRecyclerView() {
    //recyclerView.swapAdapter();
    listMenu = new ArrayList<>();
    listMenu.add("Vertical");
    listMenu.add("Horizontal");

    generateMountain();

    textAdapter = new TextAdapter(getActivity(), listMenu, this);
    linearAdapter = new LinearVerticalAdapter(getActivity(), dataWarehouse.listMountain);
    linearHorizontalAdapter =
        new LinearHorizontalAdapter(getActivity(), dataWarehouse.listMountain);

    linearLayoutManagerVertical = new LinearLayoutManager(getActivity());
    linearLayoutManagerHorizontal =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

    recyclerView.setLayoutManager(linearLayoutManagerVertical);
    recyclerView.setAdapter(textAdapter);
  }

  private void generateMountain() {

  }

  @Override public void onItemClick(int pos) {
    switch (pos) {
      case POS_VERTICAL:
        recyclerView.setAdapter(linearAdapter);
        break;
      case POS_HORIZONTAL:
        recyclerView.setLayoutManager(linearLayoutManagerHorizontal);
        recyclerView.setAdapter(linearHorizontalAdapter);
        break;
    }
  }
}
