package com.pratamawijaya.recyclerviewinaction.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pratamawijaya.recyclerviewinaction.R;
import com.pratamawijaya.recyclerviewinaction.model.Mountain;
import com.pratamawijaya.recyclerviewinaction.utils.ImageLoader;
import java.util.List;

/**
 * Created by pratama on 5/29/16.
 */
public class LinearHorizontalAdapter
    extends RecyclerView.Adapter<LinearHorizontalAdapter.LinearHolder> {

  private Context context;
  private List<Mountain> mountainList;

  public LinearHorizontalAdapter(Context context, List<Mountain> mountainList) {
    this.context = context;
    this.mountainList = mountainList;
  }

  @Override public LinearHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new LinearHolder(
        LayoutInflater.from(context).inflate(R.layout.item_linear_horizontal, parent, false));
  }

  @Override public void onBindViewHolder(LinearHolder holder, int position) {
    if (mountainList != null && mountainList.size() > 0) {
      holder.bindItem(mountainList.get(position));
    }
  }

  @Override public int getItemCount() {
    return mountainList.size();
  }

  public class LinearHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img) ImageView img;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.location) TextView location;

    public LinearHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindItem(Mountain mountain) {
      name.setText(mountain.name);
      location.setText(mountain.location);
      ImageLoader.loadImage(context, img, mountain.img);
    }
  }
}
