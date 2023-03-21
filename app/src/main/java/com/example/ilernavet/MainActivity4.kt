package com.example.ilernavet

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main4.*



class MainActivity4 : AppCompatActivity(){

    lateinit var rs:Cursor
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        back2.setOnClickListener{
        val intent: Intent = Intent (this, MainActivity::class.java)
            startActivity(intent)}

       if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1){Manifest.permission.READ_EXTERNAL_STORAGE}, 121)
        }
        listImages()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 121 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            listImages()
    }
    private fun listImages() {
        var cols = listOf<String>(MediaStore.Images.Thumbnails.DATA).toTypedArray()
        rs = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            cols,  null,null, null)!!
        gridview.adapter = ImageAdapter(applicationContext)

    }
    inner class ImageAdapter : BaseAdapter{
        lateinit var context: Context
        constructor(context: Context){
            this.context = context

        }
        override fun getCount(): Int {
            return rs.count
        }

        override fun getItem(p0: Int): Any {
            return p0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()

        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var iv = ImageView(context)
            rs.moveToPosition(p0)
            var path = rs.getString(0)
            var bitmpap =BitmapFactory.decodeFile(path)
            iv.setImageBitmap(bitmpap)
            iv.layoutParams = AbsListView.LayoutParams(850,850)
            return iv;
        }

    }
}