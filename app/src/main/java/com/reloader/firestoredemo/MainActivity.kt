package com.reloader.firestoredemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        //consultar  imformacion
//        db.collection("ciudades").document("NY").get().addOnSuccessListener { document ->
//            document?.let {
////                Log.d("Firebase", "Document da: ${document.data}")
//
//                val ciudad = document.toObject(Ciudad::class.java)
//
////                val color = document.getString("color")
////                val population = document.get("population").toString()
////                Log.d("Firebase", "Color: ${color}")
////                Log.d("Firebase", "population : ${population}")
//
//                Log.d("Firebase", "Color: ${ciudad?.color}")
//                Log.d("Firebase", "population : ${ciudad?.population}")
//                Log.d("Firebase", "PostalCode : ${ciudad?.pc}")
//
//
//            }
//        }.addOnFailureListener { error ->
//            Log.e("FirebaseError", error.toString())
//        }

        db.collection("ciudades").document("NY").addSnapshotListener { value, error ->
            value?.let {
//                Log.d("Firebase", "Document da: ${document.data}")

                val ciudad = value.toObject(Ciudad::class.java)

//                val color = document.getString("color")
//                val population = document.get("population").toString()
//                Log.d("Firebase", "Color: ${color}")
//                Log.d("Firebase", "population : ${population}")

                Log.d("Firebase", "Color: ${ciudad?.color}")
                Log.d("Firebase", "population : ${ciudad?.population}")
                Log.d("Firebase", "PostalCode : ${ciudad?.pc}")
            }
        }

        //Ingresasr informacion
        db.collection("ciudades").document("LA").set(Ciudad(52200, "Red")).addOnSuccessListener {
            Log.d("Firebase", "Se guardo la ciudad correctamente")
        }.addOnFailureListener { error ->
            Log.e("FirebaseError", error.toString())
        }

    }

}

data class Ciudad(
    val population: Int = 0,
    val color: String = "",
    val pc: Int = 0
)

