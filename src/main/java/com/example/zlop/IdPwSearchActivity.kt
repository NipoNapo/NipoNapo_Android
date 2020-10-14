package com.example.zlop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_id_pw_search.*

class IdPwSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id_pw_search)

        val fragmentManager = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentManager

        tabs_main.setupWithViewPager(viewpager_main)

    }


}


class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {


        return when(position) {

            0 ->{IdSearchFragment()}
            else ->{PwSearchFragment()}

        }
    }

    override fun getCount(): Int {
            return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "ID"
            else -> {return "PW"}

        }
    }

}

