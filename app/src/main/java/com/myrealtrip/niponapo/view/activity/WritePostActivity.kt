package com.myrealtrip.niponapo.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.myrealtrip.niponapo.BR
import com.myrealtrip.niponapo.R
import com.myrealtrip.niponapo.databinding.ActivityWritePostBinding
import com.myrealtrip.niponapo.util.ActionStore
import com.myrealtrip.niponapo.util.Dialog
import com.myrealtrip.niponapo.util.MessageDialogClickListener
import com.myrealtrip.niponapo.view.viewmodel.PostWriteViewModel
import kotlinx.android.synthetic.main.activity_write_post.*

class WritePostActivity : AppCompatActivity(), ActionStore {

    private val viewModel = PostWriteViewModel<ActionStore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //바인딩
        val binding = DataBindingUtil.setContentView<ActivityWritePostBinding>(this, R.layout.activity_write_post)
        viewModel.setNavigator(this)
        binding.setVariable(BR.viewModel, viewModel)

        //스피너
        spinner_album.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.array_album))
        spinner_have.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.array_have))
        spinner_want.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.array_want))
        spinner_album.onItemSelectedListener = listener
        spinner_have.onItemSelectedListener = listener
        spinner_want.onItemSelectedListener = listener

        //사진 가져오기 클릭
        photo.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_PICK).apply {
                data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                type = "image/*"
            }, REQUEST_CODE)
        }

        register.setOnClickListener {
            showMessage(getString(R.string.register), true, object : MessageDialogClickListener {
                override fun confirmClick() {
                    showMessage(getString(R.string.done), false, object : MessageDialogClickListener {
                        override fun confirmClick() {
                            //finish()
                        }
                    })
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE) {
            if(resultCode==RESULT_OK) {
                val uri = data!!.data!!
                viewModel.photo.set(uri.toString())
                //photo.setImageURI(uri)
                Glide.with(this)
                    .load(uri)
                    .centerCrop()
                    .into(photo)
            } else if(resultCode==RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val listener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(parent?.id) {
                R.id.spinner_album -> viewModel.albumPid.set(position)
                R.id.spinner_have -> viewModel.have.set(position)
                R.id.spinner_want -> viewModel.want.set(position)
            }
            parent?.setSelection(position)
        }
    }

    override fun onBackPressed() {
        showMessage(getString(R.string.back), true, object : MessageDialogClickListener {
            override fun confirmClick() {
                super.confirmClick()
                finish()
            }
        })
    }

    override fun showMessage(message: String, extra: Boolean, listener: MessageDialogClickListener?) {
        Dialog.showMessage(
            context = applicationContext,
            fragmentManager = supportFragmentManager,
            message = message,
            extra = extra,
            listener = listener
        )
    }

    companion object {
        const val REQUEST_CODE = 1000
    }
}
