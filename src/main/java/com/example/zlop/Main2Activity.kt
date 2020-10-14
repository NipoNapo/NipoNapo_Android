package com.example.zlop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {


  //서버연결  val PREFERENCE = "template.android.hyogeuns"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
/*
서버연결
        var pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        var users = pref.getString("username", "")
        hello.text = "환영합니다" + users + "님"
*/
    }
}
