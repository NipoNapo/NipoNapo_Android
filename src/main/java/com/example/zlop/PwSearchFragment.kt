package com.example.zlop


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_id_search.*
import kotlinx.android.synthetic.main.fragment_id_search.button2
import kotlinx.android.synthetic.main.fragment_id_search.editText3
import kotlinx.android.synthetic.main.fragment_id_search.editText4
import kotlinx.android.synthetic.main.fragment_id_search.spinner
import kotlinx.android.synthetic.main.fragment_pw_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class PwSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

/*
        button2.setOnClickListener {
            Client.retrofitService.PwSearch(
                editText5.text.toString(),
                editText73.text.toString(),
                editText4.text.toString() + "@" + spinner.selectedItem.toString()
            ).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    when(response!!.code()){
                        200 -> {

                            Toast.makeText(activity, "고객님의 아이디는"+ response +"입니다.", Toast.LENGTH_LONG)
                                .show()
                        }
                        405 -> Toast.makeText(activity, "아이디찾기 실패 : 아이디나 이메일 올바르지 않습니다", Toast.LENGTH_LONG).show()

                        500 -> Toast.makeText(activity, "회원가입 실패 : 서버 오류", Toast.LENGTH_LONG).show()

                    }                }
            })


        }






*/


        return inflater.inflate(R.layout.fragment_pw_search, container, false)
    }


}
