package com.ma.pingan.comprehensive.dagger.component;

import com.ma.pingan.comprehensive.MainActivity;

import dagger.Component;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    MainActivity inject(MainActivity activity);
}
