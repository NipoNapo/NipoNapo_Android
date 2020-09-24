package com.myrealtrip.niponapo.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myrealtrip.niponapo.R
import com.myrealtrip.niponapo.databinding.ItemExchangeBinding
import com.myrealtrip.niponapo.data.model.ExchangeInfo
import com.myrealtrip.niponapo.util.OnItemSelectedListener

class ExchangeAdapter: ListAdapter<ExchangeInfo, ExchangeAdapter.ExchangeViewHolder<ExchangeInfo>>(
    object : DiffUtil.ItemCallback<ExchangeInfo>() {
        //item이 같은지 체크 후 content 체크 함
        override fun areItemsTheSame(oldItem: ExchangeInfo, newItem: ExchangeInfo): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ExchangeInfo, newItem: ExchangeInfo): Boolean {
            return false
        }
    }
 ) {
    private var onItemSelectedListener : OnItemSelectedListener<ExchangeInfo>? = null

    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener<ExchangeInfo>) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    override fun onBindViewHolder(holder: ExchangeViewHolder<ExchangeInfo>, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeViewHolder<ExchangeInfo> {
        return ExchangeViewHolder(
            ItemExchangeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ExchangeViewHolder<T>(
        private val binding: ItemExchangeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val mImageView: TextView = itemView.findViewById(R.id.exchange_state)
        fun onBind(item : T?) {
            binding.apply {
                setVariable(BR.item, item)
                executePendingBindings()
            }
            mImageView.setOnClickListener {
                onItemSelectedListener?.invoke(it, (it as TextView).text.toString(), adapterPosition)
            }
        }
    }
}