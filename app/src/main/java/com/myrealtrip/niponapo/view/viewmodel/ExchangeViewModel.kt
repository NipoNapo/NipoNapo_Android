package com.myrealtrip.niponapo.view.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myrealtrip.niponapo.data.model.ExchangeInfo
import com.myrealtrip.niponapo.util.ActionStore
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

class ExchangeViewModel<N: ActionStore>: ViewModel() {

    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }
    private lateinit var mNavigator : WeakReference<N>

    fun setNavigator(navigator: N) { mNavigator = WeakReference(navigator) }
    fun getNavigator(): N = mNavigator.get()!!
    fun addDisposable(disposable: Disposable) =compositeDisposable.add(disposable)

    val todayDate: ObservableField<String> = ObservableField("")

    init {
        kotlin.run {
            val now = System.currentTimeMillis()
            val date = Date(now)
            val mFormat = SimpleDateFormat("yyyy-MM-dd")
            todayDate.set(mFormat.format(date))
        }
    }

    var exchangeList = MutableLiveData<List<ExchangeInfo>>(mutableListOf(
        ExchangeInfo(1,"idol123","1,3", "2020-09-01","대기중", "1"),
        ExchangeInfo(1,"hey8123","2,4","2020-09-01","수락", "2"),
        ExchangeInfo(1,"Ahn1234","1,3", "2020-09-09","대기중", "1"),
        ExchangeInfo(1,"Kim2323","2,4","2020-09-10","수락", "2"),
        ExchangeInfo(1,"Cool456","1,3", "2020-09-10","대기중", "1"),
        ExchangeInfo(1,"hey1551","2,4","2020-09-10","수락", "2"),
        ExchangeInfo(1,"iya9123","1,3", "2020-09-10","대기중", "1"),
        ExchangeInfo(1,"heyya34","2,4","2020-09-13","수락", "2"),
        ExchangeInfo(1,"mania1122","3,5","2020-09-14","매칭", "3"),
        ExchangeInfo(1,"angel9298","1,4","2020-09-15","요청중", "4"),
        ExchangeInfo(1,"beauty1223","1,4","2020-09-16","대기중", "5"),
        ExchangeInfo(1,"wonderful8872","1,5","2020-09-17","수락", "6")
    ))

    fun delete(position: Int) {
        exchangeList.value = exchangeList.value?.toMutableList()?.apply {
            this.removeAt(position)
        }
    }

    fun update(position: Int) {
        exchangeList.value = exchangeList.value?.toMutableList()?.apply {
            this[position].state = "매칭"
        }
    }

    var saveList = exchangeList.value?.toMutableList()!!

    fun init() {
        exchangeList.value = saveList
    }

    fun search(date: String) {
        exchangeList.value = saveList.filter {
            it.date == date
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
