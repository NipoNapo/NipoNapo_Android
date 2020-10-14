package com.example.zlop


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import kotlinx.android.synthetic.main.fragment_id_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class IdSearchFragment : Fragment() {



    val list_of_email = arrayOf("naver.com","nate.com","gmail.com","hotmail.com", "daum.net", "사용자 입력")

    @SuppressLint("ResourceType", "UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




/*
        button2.setOnClickListener {
            Client.retrofitService.IdSearch(editText3.text.toString(), editText4.text.toString() + "@" + spinner.selectedItem.toString()).enqueue(object : Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {


                    when(response!!.code()){
                        200 -> {

                            Toast.makeText(activity, "고객님의 아이디는 "+ response + "입니다.", Toast.LENGTH_LONG)
                                .show()
                        }
                            405 -> Toast.makeText(activity, "아이디찾기 실패 : 아이디나 이메일 올바르지 않습니다", Toast.LENGTH_LONG).show()

                            500 -> Toast.makeText(activity, "회원가입 실패 : 서버 오류", Toast.LENGTH_LONG).show()

                        }




                    }
                })
            }



*/

        return inflater.inflate(R.layout.fragment_id_search, container, false)


        /*
           var v : View = inflater.inflate(R.layout.fragment_id_search, container, false)

            val adapterSpinner = ArrayAdapter.createFromResource(this.activity!!,R.array.E_mail, android.R.layout.simple_spinner_item)

            adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapterSpinner

            spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {


                    when(position){

                        0 -> {

                        }
                        1->{

                        }
                        2->{

                        }
                        3->{

                        }
                        4->{

                        }
                        else->{

                        }
                    }
                }
            }



        return inflater.inflate(R.layout.fragment_id_search, container, false)


*/

        // Inflate the layout for this fragment

        /*
        val myAdapter = ArrayAdapter.createFromResource(this.requireActivity(),R.array.E_mail,android.R.layout.simple_spinner_item)
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = myAdapter
        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){

                    0 -> {

                    }
                    1->{

                    }
                    2->{

                    }
                    3->{

                    }
                    4->{

                    }
                    else->{

                    }
                }
            }


        }

       return inflater.inflate(R.layout.fragment_id_search, container, false)
    }




         */



    }
}



