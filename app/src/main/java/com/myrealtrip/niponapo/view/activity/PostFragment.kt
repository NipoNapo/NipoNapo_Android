package com.example.niponapo_3.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.niponapo_3.R
import com.example.niponapo_3.ui.post.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_post.*
import android.Manifest
import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import android.widget.Toast.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.niponapo_3.MainActivity
import com.example.niponapo_3.ui.home.HomeFragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_userinfo.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.ContextCompat.getSystemService as getSystemService


class PostFragment : Fragment() {
    //퍼미션 응답 처리 코드
    val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    val STORAGE_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    val FLAG_PERM_CAMERA = 98
    val FLAG_PERM_STORAGE = 99

    val FLAG_REQ_CAMERA = 101
    val FLAG_REQ_GALLERY = 102

    val album_data = listOf("선택하세요","늑대와 미녀","Love me right","중독")
    val have_data = listOf("선택하세요","카이","백현","시우민","수호","찬열","세훈")
    val want_data = listOf("선택하세요","카이","백현","시우민","수호","찬열","세훈")


    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        postViewModel =
                ViewModelProviders.of(this).get(PostViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_post, container, false)
        val textView: TextView = root.findViewById(R.id.text_post)
        postViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root

    }

    //버튼 기능 구현
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //spinner

        val adapter1 = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, album_data)
        val adapter2 = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, have_data)
        val adapter3 = android.widget.ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, want_data)

        p_album_spinner.adapter = adapter1
        p_have_spinner.adapter = adapter2
        p_want_spinner.adapter = adapter3


        //button
        p_img_btn.setOnClickListener {
            //permission check
            if(isPermitted(CAMERA_PERMISSION)){
                openCamera()
            } else {
                ActivityCompat.requestPermissions(activity!!, CAMERA_PERMISSION, FLAG_PERM_CAMERA)

            }
        }

        p_img_btn2.setOnClickListener {
            if(isPermitted(STORAGE_PERMISSION)) {
                openGallery()
            } else {
                ActivityCompat.requestPermissions(activity!!, STORAGE_PERMISSION, FLAG_PERM_STORAGE)
            }
        }

        p_post_btn.setOnClickListener {
            postpopUp()
        }



    }



    fun isPermitted(permissions:Array<String>) : Boolean{

        for(permission in permissions) {
            val result = ContextCompat.checkSelfPermission(context!!, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, FLAG_REQ_CAMERA)
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, FLAG_REQ_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("camera","req=$requestCode, result=$resultCode, data=$data")
        if(resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                FLAG_REQ_CAMERA -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    p_imgView.setImageBitmap((bitmap))
                }

                FLAG_REQ_GALLERY -> {
                    val uri = data?.data
                    p_imgView.setImageURI(uri)
                }
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            FLAG_PERM_CAMERA -> {
                var checked = true

                for(grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED) {
                        checked = false
                        break
                    }
                }
                if(checked) {
                    openCamera()
                }
            }
        }
    }

    private fun postpopUp() {
        val builder =  AlertDialog.Builder(context!!)
        val intent = Intent(activity!!, MainActivity::class.java)
        builder.setTitle("게시글 등록")
        builder.setMessage("게시글이 등록되었습니다.")
        builder.setPositiveButton("확인") {
            dialog: DialogInterface?, i: Int ->
            startActivity(intent)

        }
        builder.show()

    }




}
