package com.example.ilernavet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        bbdd1.setOnClickListener{
            val intent: Intent = Intent( this, MainActivity2Insertar::class.java )
            startActivity(intent)
        }

        bbdd2.setOnClickListener{
            val intent: Intent = Intent( this, MainActivity2Consultar::class.java )
            startActivity(intent)
        }

        bbdd3.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity2Modificar::class.java)
            startActivity(intent)
        }

        bbdd4.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity2Eliminar::class.java)
            startActivity(intent)
        }

        bbdd5.setOnClickListener {
            val intent: Intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}