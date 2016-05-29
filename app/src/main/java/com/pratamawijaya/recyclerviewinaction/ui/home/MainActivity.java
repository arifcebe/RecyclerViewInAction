package com.pratamawijaya.recyclerviewinaction.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pratamawijaya.recyclerviewinaction.R;
import com.pratamawijaya.recyclerviewinaction.base.BaseApplication;
import com.pratamawijaya.recyclerviewinaction.event.HomeClick;
import com.pratamawijaya.recyclerviewinaction.ui.gridlayout.GridLayoutFragment;
import com.pratamawijaya.recyclerviewinaction.ui.linearlayout.LinearLayoutFragment;
import com.pratamawijaya.recyclerviewinaction.ui.staggeredlayout.StaggeredLayoutFragment;
import com.pratamawijaya.recyclerviewinaction.utils.RxBus;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
  private static final int FRAGMENT_LINEARLAYOUT = 0;
  private static final int FRAGMENT_GRIDLAYOUT = 1;
  private static final int FRAGMENT_STAGGERED = 2;

  @BindView(R.id.container_fragment) FrameLayout fragmentContainer;

  protected MainFragmentOrganizer fragmentOrganizer;
  private RxBus rxBus;
  private CompositeSubscription compositeSubscription = new CompositeSubscription();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    rxBus = BaseApplication.getRxBus();

    fragmentOrganizer =
        new MainFragmentOrganizer(getSupportFragmentManager(), R.id.container_fragment);

    compositeSubscription.add(
        rxBus.toObserverable().ofType(HomeClick.class).subscribe(new Action1<HomeClick>() {
          @Override public void call(HomeClick homeClick) {
            Timber.d("call() :  %s", homeClick.pos);
            changeFragment(homeClick.pos);
          }
        }));
  }

  private void changeFragment(int pos) {
    switch (pos) {
      case FRAGMENT_LINEARLAYOUT:
        fragmentOrganizer.openFragment(LinearLayoutFragment.newInstance());
        break;
      case FRAGMENT_GRIDLAYOUT:
        fragmentOrganizer.openFragment(GridLayoutFragment.newInstance());
        break;
      case FRAGMENT_STAGGERED:
        fragmentOrganizer.openFragment(StaggeredLayoutFragment.newInstance());
        break;
    }
  }

  @Override public void onBackPressed() {
    if (!fragmentOrganizer.handleBackNavigation()) {
      finish();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (compositeSubscription != null) {
      compositeSubscription.unsubscribe();
    }
    fragmentOrganizer.freeUpResources();
  }
}
