package com.example.mydoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_prescription.*
import kotlinx.android.synthetic.main.activity_profile.*

class PrescriptionActivity : AppCompatActivity() {
    val homeAct = HomeActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)
        getPrescriptions()


    }

    fun getPrescriptions() {
        FirebaseDatabase.getInstance().reference
            .child("Prescriptions")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    var map = dataSnapshot.value as Map<*, *>
                    recept1view.text = map["1 Prescription"].toString()
                    recept2view.text = map["2 Prescription"].toString()
                    recept3view.text = map["3 Prescription"].toString()

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("userInfo", "Failed to read value.", error.toException())
                }
            })
    }

}
