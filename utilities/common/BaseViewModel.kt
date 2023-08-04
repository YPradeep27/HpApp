package com.hellopharmacy.utilities.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sudzbubblez.utilities.common.pojo.ApiStatus
import io.reactivex.disposables.CompositeDisposable

/**
 * Created on ***  on 28/12/2020.
 */


open class BaseViewModel: ViewModel()
{
    val loader: MutableLiveData<Boolean> = MutableLiveData()
    val status: MutableLiveData<ApiStatus> = MutableLiveData()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}