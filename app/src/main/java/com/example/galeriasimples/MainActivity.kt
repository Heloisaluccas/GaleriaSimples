package com.example.galeriasimples

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var ivexemplo: ImageView
    lateinit var btgaleria: Button


    val resultGaleria =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result -> val bitmap: Bitmap = if(Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(contentResolver, result.data?.data)
        } else {
            val source = ImageDecoder.createSource(this.contentResolver, result.data?.data!!)
            ImageDecoder.decodeBitmap(source)
        }
            ivexemplo.setImageBitmap(bitmap)
        }
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            ivexemplo = findViewById(R.id.ivexemplo)
            btgaleria = findViewById(R.id.btgaleria)

            btgaleria.setOnClickListener {
                resultGaleria.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
            } }
}