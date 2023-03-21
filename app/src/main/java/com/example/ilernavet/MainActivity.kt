package com.example.ilernavet

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp = MediaPlayer.create(this, R.raw.cancion)

        encendido.setOnClickListener {
            mp.start()
        }
        apagado.setOnClickListener {
            mp.stop()
        }

        b1.setOnClickListener {

            val intent:Intent = Intent (this, MainActivity2::class.java)
            startActivity(intent)
            val toast = Toast.makeText(this, "Accediendo a la Base de Datos",Toast.LENGTH_SHORT).show()

        }
        b2.setOnClickListener{

            val intent:Intent = Intent (this, MainActivity3::class.java)
            startActivity(intent)
            val toast = Toast.makeText(this, "Entrando en Activity 3",Toast.LENGTH_SHORT).show()
        }

        b3.setOnClickListener{

            val intent:Intent = Intent (this, MainActivity4::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mp.release()

    }

}