package com.example.getip.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.getip.core.Resource
import com.example.getip.data.ApiInterface
import com.example.getip.data.model.DataIP
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val api:ApiInterface):ViewModel() {

    private val compositeDisposable=CompositeDisposable()

    private var mutableData:MutableLiveData<Resource<DataIP>> = MutableLiveData()
    val data:LiveData<Resource<DataIP>> get() = mutableData

    fun getApi(){
        mutableData.value=Resource.loading()
        compositeDisposable.add(
            api.getApi()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableData.value=Resource.success(it)
                },{
                    mutableData.value=Resource.error(it.localizedMessage)
                })
        )
    }
}