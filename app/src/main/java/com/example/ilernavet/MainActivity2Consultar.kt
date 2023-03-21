package com.example.ilernavet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_activity2_consultar.*
import kotlinx.android.synthetic.main.activity_main_activity2_consultar.atras
import kotlinx.android.synthetic.main.activity_main_activity2_consultar.consul
import kotlinx.android.synthetic.main.activity_main_activity2_consultar.dni
import kotlinx.android.synthetic.main.activity_main_activity2_consultar.mascota
import kotlinx.android.synthetic.main.activity_main_activity2_insertar.*

class MainActivity2Consultar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2_consultar)

        consul.setOnClickListener{
            val admin = AdminSQLiteOpenHelper( this, "administracion", null, 1 )
            val bd= admin.writableDatabase
            val fila = bd.rawQuery("select mascota, nombre from clientes where dni=${dni.text.toString()}",null)
            if (fila.moveToFirst()) {
                dni.setText(fila.getString(0))
                mascota.setText(fila.getString(1))
            }else
                Toast.makeText(this, "No existe un paciente con dicho dni", Toast.LENGTH_SHORT).show()
            bd.close()

        }
        atras.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }

    }

}