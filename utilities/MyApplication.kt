package com.hellopharmacy.utilities

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.hellopharmacy.BuildConfig
import com.hellopharmacy.utilities.daggerinjections.components.DaggerMyComponent
import com.hellopharmacy.utilities.daggerinjections.components.MyComponent
import com.hellopharmacy.utilities.daggerinjections.modules.DaggerModule
import timber.log.Timber

class MyApplication : MultiDexApplication()
{

    lateinit var myComponent: MyComponent

    override fun onCreate()
    {
        super.onCreate()

        //----------Dagger object builder--------
        myComponent = DaggerMyComponent.builder().daggerModule(
            DaggerModule(
                this
            )
        )
                    .build()

        //---------Timber object build-----------
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}
