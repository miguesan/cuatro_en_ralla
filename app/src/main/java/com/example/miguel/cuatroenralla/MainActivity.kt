package com.example.miguel.cuatroenralla

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.DialogInterface
import android.view.View


class MainActivity : AppCompatActivity() {

    var cadena = "¿Estas seguro de que quieres dejar la caza y escapar de tu deber como una Gallina?"
    var instru = "Cuatro en ralla:\n \n" +
            "1. Deverás llegar a la ubicación del mapa\n" +
            "2. Una vez allí buscar el codigo QR y darle al boton escanear\n" +
            "3. Escanea el codigo QR. Cuando lo hagas ve a la siguiente posicion\n" +
            "4. Cuando tengas los 3 cazados ganaras\n \n" +
            "** IMPORTANTE **\n" +
            "Tienes tiempo para hacer los puntos, asique mas te vale ser rapido.\n" +
            "Si eres una tortuga empezaras de nuevo\n \n" +
            "- Dale al botón de 'Comenzar Caza' cuando estes listo\n" +
            "- O si eres un gallina dale al botón de 'Salir' o 'Atras' en tu movil\n"

    val INTERVALO = 2000 //2 segundos para salir
    var tiempoPrimerClick: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun instrucciones(view: View) { //para salir de la aplicacion con un dialogo de confirmacion

        //se prepara la alerta creando nueva instancia
        val alertbox = AlertDialog.Builder(this)
        //seleccionamos la cadena a mostrar
        alertbox.setMessage(instru)
        //elegimos un positivo Ok y creamos un Listener
        alertbox.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
            val okey = Intent(Intent.ACTION_MAIN) //Llamando a la activity principal
            closeContextMenu()
        })
        //mostramos el alertbox
        alertbox.show()

    }

}
