
package com.example.mydoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


    }
    fun getProfileInfo()
    {
        // [START write_message]
        // Write a message to the database

        FirebaseDatabase.getInstance().reference
            .child("Doctors")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    var map = dataSnapshot.value as Map <*, *>
                    nextvisitview.text = map["dateofbirth"].toString()




                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("userInfo", "Failed to read value.", error.toException())
                }
            })
        // [END read_message]

    }





}
