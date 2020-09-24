package com.myrealtrip.niponapo.util.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myrealtrip.niponapo.data.model.ExchangeInfo
import com.myrealtrip.niponapo.view.adapter.ExchangeAdapter

@BindingAdapter("adapter")
fun RecyclerView.binding(adapter: RecyclerView.Adapter<*>? = null) {
    this.adapter = adapter
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun<T> RecyclerView.binding(list: List<ExchangeInfo>? = null) {
    (adapter as? ExchangeAdapter)?.run {
        submitList(list)
    }
}