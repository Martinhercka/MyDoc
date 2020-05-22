package com.example.mydoctor



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.makeCall


class HomeActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setCurrentUserTitle()

        emergenybtn.setOnClickListener {
            callEmergency()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.profil -> {
                setContentView(R.layout.activity_profile)
                getProfileInfo()

            }

            R.id.signout -> {
                signOut()
                finish()
            }

            R.id.prescription -> {
                val i = Intent(this, PrescriptionActivity::class.java)
                startActivity(i)

            }

            R.id.home ->{
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }

            R.id.termin -> {
                val i = Intent(this, TerminActivity::class.java)
                startActivity(i)
            }


        }


        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun setCurrentUserTitle() {

        val usr = FirebaseAuth.getInstance().currentUser
        if (usr != null) {
            loggedUser.text = usr.email
        }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        finish()
    }


    fun getProfileInfo() {
        FirebaseDatabase.getInstance().reference
            .child("Doctors")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    var map = dataSnapshot.value as Map<*, *>
                    nextvisitview.text = map["nextvisit"].toString()
                    lastvisitview.text = map["lastvisit"].toString()
                    idview.text = map["ID"].toString()
                    dateofbirthview.text = map["dateofbirth"].toString()

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("userInfo", "Failed to read value.", error.toException())
                }
            })
    }

    fun callEmergency() {
        makeCall("112")
    }
}