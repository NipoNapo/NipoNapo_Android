package com.example.zlop

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.Manifest
import android.content.Context

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import android.widget.Toast.makeText as makeText1
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.PersistableBundle
import android.widget.CheckBox
import androidx.core.content.ContextCompat.startActivity


class MainActivity : AppCompatActivity() {



//서버연결
    var viewId: Int = R.layout.activity_main
    val PREFERENCE = "com.example.zlop"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        서버연결 부분
        */
        SharedPref.openSharedPrep(this)

        button.setOnClickListener{
            Client.retrofitService.logIn(editText.text.toString(), editText2.text.toString()).enqueue(object : Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {

                }


                override fun onResponse(call: Call<Void>, response: Response<Void>?) {


                    when(response!!.code()){

                        200 -> {

                        val pref = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putString("username", editText.text.toString())
                        editor.commit()
                        finish()
                        startActivity(Intent(this@MainActivity, Main2Activity::class.java))

                    }

                        405 -> Toast.makeText(this@MainActivity, "로그인 실패 : 아이다나 비번이 올바르지 않습니다.", Toast.LENGTH_LONG).show()

                        500 -> Toast.makeText(this@MainActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()




                    }



                }
            })
        }





        var auto = getSharedPreferences("auto", Activity.MODE_PRIVATE)

        var loginId = auto.getString("inputId", null)
        var loginPwd = auto.getString("inputPwd", null)
        var autoLogin: SharedPreferences.Editor = auto.edit()


        if (auto.getBoolean("Auto_Login_enabled", false)) {

            editText.setText(auto.getString("inputId", ""))
            editText2.setText(auto.getString("inputPwd", ""))
            checkBox.isChecked = true


        }

        checkBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if(isChecked) {
                    autoLogin.putString("inputId", editText.text.toString())
                    autoLogin.putString("inputPwd", editText2.text.toString())
                    autoLogin.putBoolean("Auto_Login_enabled",true)
                    autoLogin.commit()

                }else{

                    autoLogin.clear()
                    autoLogin.commit()
                }

            }


        })








        if(loginId != null && loginPwd != null){

            if(loginId.equals("smu7016") && loginPwd.equals("smu7016!!")){
                Toast.makeText(this, loginId + "님 자동로그인 입니다.",Toast.LENGTH_LONG).show()
                val Main2intent = Intent(this, Main2Activity::class.java)
                startActivity(Main2intent)
                finish()
            }
        }else if(loginId == null && loginPwd == null){

            button.setOnClickListener(object  : View.OnClickListener{
                override fun onClick(v: View?) {
                    if(editText.text.toString().equals("smu7016") &&editText2.text.toString().equals("smu7016!!")){
                        var auto = getSharedPreferences("auto", Activity.MODE_PRIVATE)

                        var autoLogin : SharedPreferences.Editor = auto.edit()
                        autoLogin.putString("inputId", editText.text.toString())
                        autoLogin.putString("inputPwd", editText2.text.toString())
                        autoLogin.commit()


                        val Main2intent = Intent(this@MainActivity, Main2Activity::class.java)
                        startActivity(Main2intent)
                        finish()


                    }
                }


            })


        }








        /*
        button.setOnClickListener {
            val Main2intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(Main2intent)

        }

    로그인 누르면 메인2로 가는 버튼
         */




            textView2.setOnClickListener {

                val idpwIntent = Intent(this, IdPwSearchActivity::class.java)
                startActivity(idpwIntent)

            }



            textView.setOnClickListener {

                val SignUpIntent = Intent(this, SingUpActivity::class.java)
                startActivity(SignUpIntent)

            }

        }





    }





