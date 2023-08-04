package com.hellopharmacy.utilities.daggerinjections.modules

import android.content.Context
import android.content.SharedPreferences
import com.hellopharmacy.utilities.Constants
import com.hellopharmacy.utilities.daggerinjections.annotations.scopes.ApplicationScope

import dagger.Module
import dagger.Provides

@Module
class DaggerModule(val context: Context)
{
    @Provides
    @ApplicationScope
    fun provideContext(): Context
    {
        return context
    }

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(context: Context): SharedPreferences
    {
        return context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

}
