package com.example.niponapo_3.ui.userinfo

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.niponapo_3.R
import com.example.niponapo_3.Userinfo
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_userinfo.*
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import android.widget.ArrayAdapter as ArrayAdapter1

class UserinfoFragment : Fragment() {

    private lateinit var userinfoViewModel: UserinfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userinfoViewModel =
            ViewModelProviders.of(this).get(UserinfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_userinfo, container, false)
        val textView: TextView = root.findViewById(R.id.text_userinfo)
        userinfoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root


    }

    var click = 0


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //내정보 화면에서 id를 유저 id로 기본 셋팅
        var uif_id = view?.findViewById<TextView>(R.id.uif_id)
        if (uif_id != null) {
            uif_id.setText("smu7016")
        }

        //스피너 구현
        val email_data = listOf("선택하세요", "naver.com", "hanmail.net","gmail.com")
        val adapter = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, email_data)

        email_spinner.adapter = adapter

        val idol_data = listOf("선택하세요","EXO","Red Velvet","NCT","ITZY")
        val adapter2 = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, idol_data)

        idol_spinner.adapter = adapter2

        //중복확인 버튼 기능 구현
        email_check.setOnClickListener{
            email_check_msg.setText("사용가능한 이메일입니다.")
        }

        //정보저장 버튼 기능 구현
        save_btn.setOnClickListener{
            save_btn_msg.setText("저장되었습니다.")
        }

        //pw 확인 - 수정필요

        var pw_1 = uif_pw1.text.toString()
        var pw_2 = uif_pw2.text.toString()

        if(pw_1.length==0 && pw_2.length==0) {
            pw_msg.setText("")
        }
        else if(pw_1.length != 0 && pw_2.length==0) {
            pw_msg.setText("PW 확인을 입력해주세요.")
        }
        else if(pw_1.length != 0 && pw_2.length !=0) {
            if (pw_1 == pw_2) {
                pw_msg.setText("PW가 일치합니다.")
            }
            else {
                pw_msg.setText("PW가 일치하지않습니다. 다시 한 번 확인해주세요")
            }
        }


        //거래방식 옵션버튼 색깔 변하기

        option_btn1.setOnClickListener{
            click++
            if (click%2 == 0) {
                option_btn1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            else {
                option_btn1.setBackgroundColor(Color.parseColor("#C5C3C3"))
            }

        }
        option_btn2.setOnClickListener {
            click++
            if (click % 2 == 0) {
                option_btn2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            } else {
                option_btn2.setBackgroundColor(Color.parseColor("#C5C3C3"))
            }

        }
        option_btn3.setOnClickListener{
            click++
            if (click%2 == 0) {
                option_btn3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            else {
                option_btn3.setBackgroundColor(Color.parseColor("#C5C3C3"))
            }

        }
        option_btn4.setOnClickListener{
            click++
            if (click%2 == 0) {
                option_btn4.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            else {
                option_btn4.setBackgroundColor(Color.parseColor("#C5C3C3"))
            }

        }
        option_btn5.setOnClickListener{
            click++
            if (click%2 == 0) {
                option_btn5.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            else {
                option_btn5.setBackgroundColor(Color.parseColor("#C5C3C3"))
            }

        }

    }








//서버통신

//        var retrofit = Retrofit.Builder()
//            .baseUrl("") //server 주소 넣기
//            .addConverterFactory(GsonConverterFactory.create()) //Gson은 java class로 자동변환
//            .build()
//
//        var userinfo = retrofit.create(Userinfo::class.java)
//
//        //button 기능 넣기
//        
//   }
}


