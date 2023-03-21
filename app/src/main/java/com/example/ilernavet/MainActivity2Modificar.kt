package com.example.ilernavet

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_activity2_insertar.*
import kotlinx.android.synthetic.main.activity_main_activity2_modificar.*
import kotlinx.android.synthetic.main.activity_main_activity2_modificar.atras
import kotlinx.android.synthetic.main.activity_main_activity2_modificar.consul
import kotlinx.android.synthetic.main.activity_main_activity2_modificar.dni
import kotlinx.android.synthetic.main.activity_main_activity2_modificar.mascota
import kotlinx.android.synthetic.main.activity_main_activity2_modificar.nombre

class MainActivity2Modificar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2_modificar)

        consul.setOnClickListener{
            val admin = AdminSQLiteOpenHelper( this, "administracion", null, 1)
            val bd= admin.writableDatabase
            val registro = ContentValues()
            registro.put("mascota", mascota.text.toString())
            registro.put("nombre", nombre.text.toString())
            val cant = bd.update("clientes", registro, "dni=${dni.text.toString()}", null)
            bd.close()
            if (cant ==1)
                Toast.makeText( this, "se modificaron los datos", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "no existe un paciente con el dni ingresado", Toast.LENGTH_SHORT).show()
        }

        atras.setOnClickListener{
            val intent: Intent = Intent( this, MainActivity2::class.java )
            startActivity(intent)
        }
    }
}