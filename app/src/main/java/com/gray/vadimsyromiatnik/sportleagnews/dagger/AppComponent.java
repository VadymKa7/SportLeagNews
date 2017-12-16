package com.gray.vadimsyromiatnik.sportleagnews.dagger;

import com.gray.vadimsyromiatnik.sportleagnews.App;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by vadimsyromiatnik on 12/9/17.
 */

@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AndroidBindingModule.class
})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {

    }
}

