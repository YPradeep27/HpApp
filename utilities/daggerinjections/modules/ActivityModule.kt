package com.hellopharmacy.utilities.daggerinjections.modules

import android.app.Activity
import android.content.Context
import com.hellopharmacy.utilities.daggerinjections.annotations.scopes.ApplicationScope

import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val context: Activity)
{
    @Provides
    @ApplicationScope
    fun provideContext(): Context
    {
        return context
    }
/*
    @Provides
    @ApplicationScope
    fun provideAlerter(activity: Activity): Alerter
    {
        return Alerter.create(activity)
    }*/

}
