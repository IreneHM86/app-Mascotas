package com.example.ilernavet


import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.ilernavet.databinding.ActivityMain3Binding
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main3.*
import java.io.File
import java.io.OutputStream


class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.openCamera.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    createPhotoFile()
                    val photoUri: Uri = FileProvider.getUriForFile(
                        this,
                        BuildConfig.APPLICATION_ID + ".fileprovider", file
                    )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamera.launch(intent)
        }
        binding.saveToGallery.setOnClickListener {
            saveToGallery()
        }

        back.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val bitmap = getBitmap()
                binding.img.setImageBitmap(bitmap)
            }
        }

    private lateinit var file: File
    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)

    }

    private fun saveToGallery() {
        val content = createContent()
        val uri = save(content)
        clearContent(content, uri)
        Toast.makeText(this, "Imagen guardada en la galeria", Toast.LENGTH_SHORT).show()

    }

    private fun createContent(): ContentValues {
        val fileName = file.name
        val fileType = "image/jpg"
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, fileType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    private fun save(content: ContentValues): Uri {
        var outputStream: OutputStream?
        var uri: Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use { output ->
            getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, output)
        }
        return uri!!
    }

    private fun clearContent(content: ContentValues, uri: Uri) {
        content.clear()
        content.put(MediaStore.MediaColumns.IS_PENDING, 0)
        contentResolver.update(uri, content, null, null)

    }

    private fun getBitmap(): Bitmap {
        return BitmapFactory.decodeFile(file.toString())
    }
}
