package com.example.niponapo_3.ui.developinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.niponapo_3.R
import com.example.niponapo_3.ui.developinfo.DevelopinfoViewModel

class DevelopinfoFragment : Fragment() {

    private lateinit var developinfoViewModel: DevelopinfoViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        developinfoViewModel =
                ViewModelProviders.of(this).get(DevelopinfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_developinfo, container, false)
        val textView: TextView = root.findViewById(R.id.text_developinfo)
        developinfoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
