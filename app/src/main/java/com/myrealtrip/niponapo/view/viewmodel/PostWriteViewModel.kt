package com.myrealtrip.niponapo.view.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.myrealtrip.niponapo.data.ApiService
import com.myrealtrip.niponapo.data.model.Post
import com.myrealtrip.niponapo.data.remote.PostApi
import com.myrealtrip.niponapo.util.ActionStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class PostWriteViewModel<N: ActionStore>: ViewModel()  {

    private val postApi = ApiService.retrofit.create(PostApi::class.java)
    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }
    private lateinit var mNavigator : WeakReference<N>

    fun setNavigator(navigator: N) { mNavigator = WeakReference(navigator) }
    fun getNavigator(): N = mNavigator.get()!!
    fun addDisposable(disposable: Disposable) =compositeDisposable.add(disposable)

    val id = ObservableField<String>("")
    val how = ObservableField<String>("")
    val albumPid = ObservableField(0)
    val have = ObservableField(0)
    val want = ObservableField(0)
    val photo = ObservableField<String>("")

    //글 등록하기
    fun register() {
        addDisposable(
            postApi.createPost(
                Post(
                    id.get()!!,
                    how.get()!!,
                    albumPid.get()!!,
                    have.get()!!,
                    want.get()!!,
                    photo.get()!!)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.e("response", it.success.toString()) }, {t: Throwable? -> t?.printStackTrace() })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}