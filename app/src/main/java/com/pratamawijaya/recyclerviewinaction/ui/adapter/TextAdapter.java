package com.pratamawijaya.recyclerviewinaction.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pratamawijaya.recyclerviewinaction.R;
import java.util.List;

/**
 * Created by pratama on 5/20/16.
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.MainHolder> {

  public interface ClickListener {
    void onItemClick(int pos);
  }

  private Context context;
  private List<String> strings;
  private ClickListener listener;

  public TextAdapter(Context context, List<String> strings, ClickListener listener) {
    this.context = context;
    this.strings = strings;
    this.listener = listener;
  }

  @Override public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MainHolder(LayoutInflater.from(context).inflate(R.layout.item_main, parent, false));
  }

  @Override public void onBindViewHolder(MainHolder holder, int position) {
    if (strings != null && strings.size() > 0) {
      holder.bindData(strings.get(position), position);
    }
  }

  @Override public int getItemCount() {
    return strings.size();
  }

  public class MainHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.container_text) LinearLayout container;

    public MainHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindData(String string, final int pos) {
      txtTitle.setText("" + string);
      container.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          listener.onItemClick(pos);
        }
      });
    }
  }
}
