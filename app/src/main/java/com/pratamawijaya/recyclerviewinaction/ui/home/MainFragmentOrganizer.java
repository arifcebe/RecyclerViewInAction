package com.pratamawijaya.recyclerviewinaction.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.pratamawijaya.recyclerviewinaction.utils.FragmentOrganizer;

/**
 * Created by pratama on 5/29/16.
 */
public class MainFragmentOrganizer extends FragmentOrganizer {

  public MainFragmentOrganizer(FragmentManager fragmentManager, int containerResourceId) {
    super(fragmentManager, containerResourceId);
  }

  @Override protected Fragment getInitialFragment() {
    return HomeFragment.newInstance();
  }

  @Override public void onEvent(Object event) {

  }

  @Override public boolean handleBackNavigation() {
    Fragment fragment = getOpenFragment();
    if (fragment instanceof HomeFragment) {
      return false;
    } else {
      fragmentManager.popBackStack();
      return true;
    }
  }
}
