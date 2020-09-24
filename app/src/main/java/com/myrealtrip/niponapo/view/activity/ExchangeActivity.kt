package com.myrealtrip.niponapo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.SimpleItemAnimator
import com.myrealtrip.niponapo.BR
import com.myrealtrip.niponapo.R
import com.myrealtrip.niponapo.data.ApiService
import com.myrealtrip.niponapo.databinding.ActivityExchangeBinding
import com.myrealtrip.niponapo.data.remote.ExchangeApi
import com.myrealtrip.niponapo.util.ActionStore
import com.myrealtrip.niponapo.util.Dialog
import com.myrealtrip.niponapo.util.MessageDialogClickListener
import com.myrealtrip.niponapo.view.adapter.ExchangeAdapter
import com.myrealtrip.niponapo.view.viewmodel.ExchangeViewModel
import kotlinx.android.synthetic.main.activity_exchange.*
import kotlinx.android.synthetic.main.activity_write_post.*
import kotlinx.android.synthetic.main.message_dialog_fragment.view.*

class ExchangeActivity : AppCompatActivity(), ActionStore{

    private val mViewModel = ExchangeViewModel<ActionStore>()
    private val adapter = ExchangeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //바인딩
        val binding = DataBindingUtil.setContentView<ActivityExchangeBinding>(this, R.layout.activity_exchange)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        mViewModel.setNavigator(this)
        binding.let {
            spinner_date.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.array_date))
            spinner_date.onItemSelectedListener = itemSelectedListener
            it.viewModel = mViewModel
            (it.rcv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            it.adapter = adapter.apply {
                setOnItemSelectedListener { view, text, position ->
                    when(text) {
                        "수락" -> {
                            showMessage(getString(R.string.str_confirm), true, object : MessageDialogClickListener {
                                override fun confirmClick() {
                                    showMessage(getString(R.string.accept), false, object : MessageDialogClickListener {
                                        override fun confirmClick() {
                                            mViewModel.update(position)
                                        }
                                    })
                                }
                                override fun cancelClick() {
                                    showMessage(getString(R.string.cancel_complete), false, object : MessageDialogClickListener {})
                                }
                            })
                        }
                        "대기중" -> {

                        }
                        "요청중" -> {
                            showMessage(getString(R.string.cancel), true, object : MessageDialogClickListener {
                                override fun confirmClick() {
                                    showMessage(getString(R.string.cancel_request), false, object : MessageDialogClickListener {
                                        override fun confirmClick() {
                                            mViewModel.delete(position)
                                        }
                                    })
                                }
                                override fun cancelClick() {
                                    showMessage(getString(R.string.cancel_complete), false, object : MessageDialogClickListener {})
                                }
                            })
                        }
                        "매칭" -> {
                            showMessage(getString(R.string.match), false, object : MessageDialogClickListener {})
                        }
                    }
                }
            }
        }
        binding.setVariable(BR.viewModel, mViewModel)
        binding.setVariable(BR.adapter, adapter)
        //viewModel.setNavigator(this)

        question.setOnClickListener {
            showMessage(getString(R.string.str_question), false, listener = object : MessageDialogClickListener{})
        }

        val ad = ApiService.retrofit.create(ExchangeApi::class.java)
    }

    override fun showMessage(message: String, extra: Boolean, listener: MessageDialogClickListener?) {
        Dialog.showMessage(
            context = applicationContext,
            fragmentManager = supportFragmentManager,
            message = message,
            extra = extra,
            listener = listener
        )
    }

    private val itemSelectedListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0) {
                    mViewModel.search((view as TextView).text.toString())
                } else {
                    mViewModel.init()
                }
            }

        }
}
