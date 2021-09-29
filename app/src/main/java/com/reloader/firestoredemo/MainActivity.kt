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
                val envio_fecha_sms = seleccion.data["sms_envio_fecha"] as Date
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

            Log.d("arrNewsFirebase", "$arrNews")

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


        //Ingresasr informacion
//        db.collection("data").document("Wy4vzjZEFG3qVMnFYwlx")
//            .set(
//                jsonDatos(
//                    2623, "+51961162784", 0,
//                    "", "2021-09-28", "",
//                    1311999, "Buenos días"
//                )
//            ).addOnSuccessListener {
//                Log.d("Firebase", "Se guardo la ciudad correctamente")
//            }.addOnFailureListener { error ->
//                Log.e("FirebaseError", error.toString())
//            }
    }


}

data class jsonDatos(
    val estado_id: Int = 0,
    val sms_destinatario: String = "",
    val sms_envio_error: Int = 0,
    val sms_envio_estado: String,
    val sms_envio_fecha: String,
    val sms_fecha_registro: String,
    val sms_id: Int = 0,
    val sms_mensaje: String
)


