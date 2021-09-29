package com.reloader.firestoredemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.reloader.firestoredemo.Entidades.ESms
import java.util.*

class MainActivity : AppCompatActivity() {

    private var arrNews = mutableListOf<ESms>()
    private var envioLista = mutableListOf<ESms>()

    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()


        //Listado General
//        db.collection("data").get().addOnSuccessListener { resultado ->
//
//            for (documentos in resultado) {
//                // Log.d("Firebase", "Datos doc: ${documentos.data}")
//                Log.d("Firebase", "Datos doc: ${documentos.data.getValue("estado_id")}")
//                //Log.d("Firebase", "Datos doc: ${documentos.id}")
//            }
//        }

        //Consulta  por  estado_id

        db.collection("data").whereEqualTo("estado_id", 2623).get().addOnSuccessListener {

            for (seleccion in it) {
                //Log.d("Firebase", "Datos doc: ${seleccion.data}")
                //Log.d("Firebase", "Datos doc: ${seleccion.data}")

                val idestado = seleccion.data["estado_id"] as Any
                val destinatario_sms = seleccion.data["sms_destinatario"].toString()
                val envio_error_sms = seleccion.data["sms_envio_error"].toString()
                val envio_estado_sms = seleccion.data["sms_envio_estado"] as Boolean
                val envio_fecha_sms = seleccion.getTimestamp("sms_envio_fecha")
                val fecha_registro_sms = seleccion.data["sms_fecha_registro"] as Date
                val id_sms = seleccion.data["sms_id"] as Any
                val mensaje_sms = seleccion.data["sms_mensaje"].toString()

                arrNews.add(
                    ESms(
                        idestado,
                        destinatario_sms,
                        envio_error_sms,
                        envio_estado_sms,
                        envio_fecha_sms,
                        fecha_registro_sms,
                        id_sms,
                        mensaje_sms
                    )
                )

            }

            // Log.d("arrNewsFirebase", "$arrNews")


            val smslista = arrNews[count].sms_id
            // Log.d("contador", "$smslista")
            db.collection("data").whereEqualTo("sms_id", smslista).get().addOnSuccessListener {

                for (lista in it) {
                    // Log.d("Firebase", "Datos doc: ${lista.data}")
                    val codeAuto = lista.id
                    val idestado = lista.data["estado_id"] as Any
                    val destinatario_sms = lista.data["sms_destinatario"].toString()
                    val envio_error_sms = lista.data["sms_envio_error"].toString()
                    val envio_estado_sms = lista.data["sms_envio_estado"] as Boolean
                    val envio_fecha_sms = lista.getTimestamp("sms_envio_fecha")
                    val fecha_registro_sms = lista.data["sms_fecha_registro"] as Date
                    val id_sms = lista.data["sms_id"] as Any
                    val mensaje_sms = lista.data["sms_mensaje"].toString()

                    updateFirebaseData(codeAuto)
                }

            }

        }


        //consultar  imformacion
//        db.collection("data").get().addOnSuccessListener { document ->
//            document?.let {
////                Log.d("Firebase", "Document da: ${document.data}")
//
//                //val ciudad = document.toObject(Ciudad::class.java)
//
//                val doc = document.documents
//
//                Log.d("Firebase", "doc: ${doc.toString()}")
//
//
//
////                val color = document.getString("color")
////                val population = document.get("population").toString()
////                Log.d("Firebase", "Color: ${color}")
////                Log.d("Firebase", "population : ${population}")
//
////                Log.d("Firebase", "Color: ${ciudad?.color}")
////                Log.d("Firebase", "population : ${ciudad?.population}")
////                Log.d("Firebase", "PostalCode : ${ciudad?.pc}")
//
//
//            }
//        }.addOnFailureListener { error ->
//            Log.e("FirebaseError", error.toString())
//        }


//        Ingresasr informacion


    }

    private fun updateFirebaseData(codeAuto: String) {


//        val mensaje = hashMapOf(
//            "estado_id" to 2624,
//            "sms_destinatario" to "+51961162784",
//            "sms_envio_error" to "Retorno",
//            "sms_envio_estado" to true,
//            "sms_envio_fecha" to 1133333,
//            "sms_fecha_registro" to 1632934428,
//            "sms_id" to 1311998,
//            "sms_mensaje" to "Developer"
//        )

        val db = FirebaseFirestore.getInstance()

        var enviofecha: String = "1632943015"
        var t = enviofecha.toLong()
        var ts = Timestamp(t, 0)

        var fecharegistro: String = "1632934428"
        var t2 = fecharegistro.toLong()
        var ts2 = Timestamp(t2, 0)

        db.collection("data").document(codeAuto)
            .set(
                smsUpdate(
                    2624, "Jose", "converti a TimeStamp", false,
                    ts, ts2, 1311998,
                    "sin miedo al exito"
                )
            ).addOnSuccessListener {
                Log.d("Firebase", "Se guardo la ciudad correctamente")
            }.addOnFailureListener { error ->
                Log.e("FirebaseError", error.toString())
            }
    }

    data class smsUpdate(
        val estado_id: Any,
        val sms_destinatario: String,
        val sms_envio_error: String,
        val sms_envio_estado: Boolean,
        val sms_envio_fecha: Timestamp?,
        val sms_fecha_registro: Timestamp?,
        val sms_id: Any,
        val sms_mensaje: String
    )
}



