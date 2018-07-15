package com.example.miguel.cuatroenralla

import android.annotation.TargetApi
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.DialogInterface
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.media.MediaPlayer


class MainActivity : AppCompatActivity() {

    var cadena = "¿Estas seguro de que quieres salir?"
    var instru = "Cuatro en ralla:\n \n" +
            "1. En el primer turno solo se podrán colocar pegados al borde\n" +
            "2. En el segundo turno se pueden colocar en el borde o unicamente acontinuación de otro ya colocado\n" +
            "3. En el tercer turno seria lo mismo que en el anterior punto, solo se añade la siguiente columna\n" +
            "4. Continuar así hasta realizar un 4 en ralla en cualquier sentido (Horizontal, Vertical o Diagonal\n \n" +
            "- Dale al botón de 'Comenzar' cuando estes listo\n" +
            "- O para salir presiona 'Atras' en tu movil\n"

    val INTERVALO = 2000 //2 segundos para salir
    var tiempoPrimerClick: Long = 0

    //para la musica
    private var musicafondo: MediaPlayer? = null
    var MAX_VOLUME = 100
    var soundVolume = 90
    var volume = (1 - Math.log((MAX_VOLUME - soundVolume).toDouble()) / Math.log(MAX_VOLUME.toDouble())).toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //musica de fondo para la app
        musicafondo = MediaPlayer.create(this, R.raw.musica)
        musicafondo!!.setLooping(true)
        musicafondo!!.setVolume(volume, volume)
        musicafondo!!.start()
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBackPressed() {

        //se prepara la alerta creando nueva instancia
        val alertbox = AlertDialog.Builder(this)
        //seleccionamos la cadena a mostrar
        alertbox.setMessage(cadena)

        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            alertbox.setMessage(cadena)
            //elegimos un positivo SI y creamos un Listener
            alertbox.setPositiveButton("Si") { dialogInterface, i ->
                val salida = Intent(Intent.ACTION_MAIN) //Llamando a la activity principal
                musicafondo!!.stop() //para la musica de fondo
                finishAndRemoveTask()
            }

            //elegimos un positivo NO y creamos un Listener
            alertbox.setNegativeButton("No") { dialogInterface, i -> }
        }
        //mostramos el alertbox
        alertbox.show()
    }

}
