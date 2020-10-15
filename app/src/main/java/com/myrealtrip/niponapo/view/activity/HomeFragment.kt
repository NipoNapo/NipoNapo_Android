package com.example.niponapo_3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.niponapo_3.PostedList
import com.example.niponapo_3.R
import com.example.niponapo_3.listAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //스피너 생성
        val album_data = listOf("선택하세요","늑대와 미녀","Love me right","중독")
        val have_data = listOf("선택하세요","카이","백현","시우민","수호","찬열","세훈")
        val want_data = listOf("선택하세요","카이","백현","시우민","수호","찬열","세훈")

        val adapter1 = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, album_data)
        val adapter2 = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, have_data)
        val adapter3 = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, want_data)

        album_spinner.adapter = adapter1
        have_spinner.adapter = adapter2
        want_spinner.adapter = adapter3
        var click = 0



        val postedList = arrayListOf(
            //PostedList(R.drawable.suho, "smuLove", "직거래", "2020-09-18","요청"),
            PostedList(R.drawable.sehun, "smu08", "택배", "2020-09-18", "요청"),
            PostedList(R.drawable.kai,"smuComp","우편, 등기", "2020-09-17", "요청됨"),
            PostedList(R.drawable.xiumin, "smuScien", "등기, 준등기", "2020-09-16", "요청")
        )

       post_list.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        post_list.setHasFixedSize(true)

        post_list.adapter = listAdapter(postedList)



    }}
