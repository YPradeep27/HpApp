package com.hellopharmacy.utilities.daggerinjections.components

import com.hellopharmacy.utilities.daggerinjections.annotations.scopes.ApplicationScope
import com.hellopharmacy.utilities.daggerinjections.modules.DaggerModule
import com.hellopharmacy.utilities.common.BaseActivity
import com.hellopharmacy.utilities.common.BaseFragment
import com.hellopharmacy.utilities.daggerinjections.modules.RetrofitModule
import com.hellopharmacy.utilities.daggerinjections.modules.ViewModelModule

import dagger.Component


@ApplicationScope
@Component(modules = [DaggerModule::class ,  ViewModelModule::class , RetrofitModule::class])
interface MyComponent
{
    fun inject(activity: BaseActivity)
    fun inject(fragment: BaseFragment)
}