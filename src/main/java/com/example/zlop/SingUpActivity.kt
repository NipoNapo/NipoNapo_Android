package com.example.zlop

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sing_up.*
import kotlinx.android.synthetic.main.activity_sing_up.view.*
import kotlinx.android.synthetic.main.fragment_id_search.*
import java.util.regex.Pattern
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*


class SingUpActivity : AppCompatActivity() {



    var viewId: Int = R.layout.activity_sing_up
    val list_of_email = arrayOf("선택하세요","naver.com","nate.com","gmail.com","hotmail.com", "daum.net", "사용자 입력")
    val list_of_idol = arrayOf("선택하세요","엑소","방탄소년단","NCT", "트와이스")
    val map = hashMapOf("엑소" to 1, "방탄소년단" to 2,"NCT" to 3, "트와이스" to 4)
    var checkk_how : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)




        button4.setOnClickListener {
            Client.retrofitService.getIdCheck(id_tv.text.toString()).enqueue(object : Callback<idcheck>{
                override fun onFailure(call: Call<idcheck>, t: Throwable) {

                }

                override fun onResponse(call: Call<idcheck>, response: Response<idcheck>) {


                    when(response!!.code()){

                        200 -> Toast.makeText(this@SingUpActivity, "사용가능한 아이디 입니다.", Toast.LENGTH_LONG).show()


                        405 -> Toast.makeText(this@SingUpActivity, "중복된 아이디", Toast.LENGTH_LONG).show()

                        500 -> Toast.makeText(this@SingUpActivity, "아이디 중복확인 실패 : 서버 오류", Toast.LENGTH_LONG).show()



                }


            }

            })

        }



        hashMapOf<String, String>()



        button7.setOnClickListener {
            Client.retrofitService.getEmailCheck(email_tv.text.toString()+ "@" + spinner4.selectedItem.toString()).enqueue(object : Callback<emailcheck>{
                override fun onFailure(call: Call<emailcheck>, t: Throwable) {
                }

                override fun onResponse(call: Call<emailcheck>, response: Response<emailcheck>) {
                    when(response!!.code()){

                        200 -> Toast.makeText(this@SingUpActivity, "사용가능한 이메일 입니다.", Toast.LENGTH_LONG).show()


                        405 -> Toast.makeText(this@SingUpActivity, "중복된 이메일입니다", Toast.LENGTH_LONG).show()

                        500 -> Toast.makeText(this@SingUpActivity, "이메일 중복확인 실패 : 서버 오류", Toast.LENGTH_LONG).show()



                    }


                }
            })
        }


        /*
        button7.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {


                var builder = AlertDialog.Builder(this@SingUpActivity)
                //builder.setTitle("사용 가능한 아이디 입니다.")
                builder.setMessage("사용 가능한 이메일 입니다.")

                var listener = object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {


                    }
                }

                builder.setPositiveButton("예", listener)
                // builder.setNegativeButton("아니요",null)
                builder.show()




            }


        })


*/

        button4.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {


                var builder = AlertDialog.Builder(this@SingUpActivity)
                //builder.setTitle("사용 가능한 아이디 입니다.")
                builder.setMessage("사용 가능한 아이디 입니다.")

                var listener = object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                    }
                }

                builder.setPositiveButton("예", listener)
                // builder.setNegativeButton("아니요",null)
                builder.show()



            }


        })



/*
        button9.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                var builder = AlertDialog.Builder(this@SingUpActivity)
                builder.setTitle("회원가입을 축하합니다!")
                builder.setMessage("smu7016")

                var listener = object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val Mainintent = Intent(this@SingUpActivity, MainActivity::class.java)
                        startActivity(Mainintent)

                    }
                }

                builder.setPositiveButton("예", listener)
               // builder.setNegativeButton("아니요",null)
                builder.show()




            }
        })



 */

    val check_map = mapOf<String, Int>("직거래" to 1, "택배" to 2, "우편" to 3, "등기" to 4, "준등기" to 5)










        findViewById<Button>(R.id.button9).setOnClickListener {
            Client.retrofitService.logUp(name_tv.text.toString(), id_tv.text.toString(), pw_tv.text.toString(),email_tv.text.toString()+ "@" + spinner4.selectedItem.toString(),map[spinner5.selectedItem]!!.toInt(),check_map["준등기"]!!.toInt()).enqueue(object :Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {

                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    when(response!!.code()){
                        200 -> {
                            Toast.makeText(this@SingUpActivity, "회원가입 성공", Toast.LENGTH_LONG).show()
                            finish ()
                        }
                        405 -> Toast.makeText(this@SingUpActivity, "회원가입 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@SingUpActivity, "회원가입 실패 : 서버 오류", Toast.LENGTH_LONG).show()


                    }

                }


            })
        }



        textInputLayout.isCounterEnabled = true
        textInputLayout.counterMaxLength = 10

        textInputLayout.editText?.addTextChangedListener(object : TextWatcher{

            var text:String ?= null

            override fun afterTextChanged(s: Editable?) {

                var length  = s.toString().length

                if(length > 0){
                    var ps : Pattern = Pattern.compile("^[a-zA-Z0-9ㄱ-ㅎ가-흐]+$")
                    if(!ps.matcher(s).matches()){
                        textInputLayout.editText?.setText(text)
                        textInputLayout.editText?.setSelection(textInputLayout.editText!!.length())
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                text = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }


        })

        textInputLayout3.isPasswordVisibilityToggleEnabled = true
        textInputLayout5.isPasswordVisibilityToggleEnabled = true

        textInputLayout5.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(textInputLayout3.editText?.text.toString().equals(textInputLayout5.editText?.text.toString())){
                    textInputLayout5.error = "비밀번호가 일치 합니다."
                    textInputLayout5.isErrorEnabled = false

                }else{

                    textInputLayout5.error = "비밀번호가 일치 하지 않습니다."
                }


            }


        })




        spinner5.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_idol)

        spinner5.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
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
                    else->{

                    }
                }

            }


        }


        spinner4.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_email)

        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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





        if(checkBox12.isChecked){
            checkk_how = "1"
        }

        else if(checkBox13.isChecked){
            checkk_how = "2"
        }

        else if(checkBox14.isChecked){
            checkk_how = "3"
        }
        else if(checkBox15.isChecked){
            checkk_how = "4"
        }
        else if(checkBox16.isChecked){
            checkk_how = "5"
        }
        else{
            checkk_how = "0"
        }



    }


    override fun onBackPressed() {


        var builder = AlertDialog.Builder(this)
        builder.setTitle("뒤로 돌아가시겠습니까?")
        builder.setMessage("입력하신 정보가 모두 삭제됩니다.")

        var listener = object:DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                   finish()
            }
        }

        builder.setPositiveButton("예", listener)
        builder.setNegativeButton("아니요",null)
        builder.show()


    }










}
