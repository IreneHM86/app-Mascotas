package com.example.ilernavet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_activity2_eliminar.*
import kotlinx.android.synthetic.main.activity_main_activity2_eliminar.atras
import kotlinx.android.synthetic.main.activity_main_activity2_eliminar.consul
import kotlinx.android.synthetic.main.activity_main_activity2_eliminar.dni
import kotlinx.android.synthetic.main.activity_main_activity2_eliminar.mascota
import kotlinx.android.synthetic.main.activity_main_activity2_eliminar.nombre
import kotlinx.android.synthetic.main.activity_main_activity2_insertar.*

class MainActivity2Eliminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2_eliminar)

        consul.setOnClickListener{
            val admin = AdminSQLiteOpenHelper( this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val cant =bd.delete("clientes", "dni=${dni.text.toString()}", null)
            bd.close()
            nombre.setText("")
            dni.setText("")
            mascota.setText("")
            if (cant ==1)
                Toast.makeText(this, "se borró el paciente con dicho dni", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText( this, "no existe un paciente con dicho dni", Toast.LENGTH_SHORT).show()
        }

        atras.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}