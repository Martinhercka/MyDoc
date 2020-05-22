package com.example.mydoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_termin.*
import java.util.*
import kotlin.collections.HashMap

class TerminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termin)

        poziadatbtn.setOnClickListener {
            requestForVisit()
        }
    }


    fun checkDate() {
        val gg = findViewById<EditText>(R.id.datum)
        Toast.makeText(baseContext, gg.text, Toast.LENGTH_SHORT).show()
        //val regex = "^(0[1-9]|1[012])[\/.\.](0[1-9]|[12][0-9]|3[01])[\/.\.](19|20)\d\d$".toRegex()


    }


    fun requestForVisit() {
        val usr = FirebaseAuth.getInstance().currentUser
        val reff = FirebaseDatabase.getInstance().reference.child("Request")


        val date = findViewById<EditText>(R.id.datum)
        val message = findViewById<EditText>(R.id.editText3)


        val hashMap: HashMap<String, String> = HashMap<String, String>()

        hashMap.put("date", date.text.toString())
        hashMap.put("Message", message.text.toString())
        if (usr != null)
            hashMap.put("Email", usr.email.toString())

        reff.updateChildren(hashMap as Map<String, Any>)


    }
}


