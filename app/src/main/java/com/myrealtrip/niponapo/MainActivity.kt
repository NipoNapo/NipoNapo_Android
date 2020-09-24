package com.myrealtrip.niponapo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myrealtrip.niponapo.view.activity.ExchangeActivity
import com.myrealtrip.niponapo.view.activity.WritePostActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //startActivity(Intent(this, ExchangeActivity::class.java))
        startActivity(Intent(this, WritePostActivity::class.java))
    }
}
