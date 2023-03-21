package com.example.ilernavet

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_activity2_insertar.*

class MainActivity2Insertar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2_insertar)

        consul.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre", nombre.getText().toString())
            registro.put("dni", dni.getText().toString())
            registro.put("mascota", mascota.getText().toString())
            bd.insert("clientes", null, registro)
            bd.close()
            nombre.setText("")
            dni.setText("")
            mascota.setText("")
            Toast.makeText(this, "Se introdujeron los datos del cliente", Toast.LENGTH_SHORT).show()


            }

        atras.setOnClickListener{
            val intent: Intent = Intent( this, MainActivity2::class.java )
            startActivity(intent)
        }
    }
}