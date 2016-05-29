package com.pratamawijaya.recyclerviewinaction.base;

import android.app.Application;
import com.pratamawijaya.recyclerviewinaction.BuildConfig;
import com.pratamawijaya.recyclerviewinaction.utils.RxBus;
import timber.log.Timber;

/**
 * Created by pratama on 5/29/16.
 */
public class BaseApplication extends Application {

  private static RxBus rxBus;

  public static RxBus getRxBus() {
    if (rxBus == null) {
      rxBus = new RxBus();
    }
    return rxBus;
  }

  @Override public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
  }
}
