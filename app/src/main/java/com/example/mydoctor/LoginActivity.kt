package com.example.mydoctor

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            checkCredentials()
        }

        val currentusr = auth.currentUser
        updateUI(currentusr)
    }

    public override fun onStart() {
        super.onStart()

    }

    fun updateUI(currentusr: FirebaseUser?) {
        if (currentusr != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        } else {
            Toast.makeText(baseContext, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doLogin() {
        auth.signInWithEmailAndPassword(loginField.text.toString(), passField.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Nesprávne prihlasovacie údaje", Toast.LENGTH_SHORT)
                        .show()
                    updateUI(null)
                }
            }
    }

    private fun checkCredentials() {

        if (loginField.text.isEmpty() && passField.text.isEmpty()) {
            Toast.makeText(
                baseContext,
                "Polia nemôžu ostať prázdne. Prosím vyplnte ich",
                Toast.LENGTH_SHORT
            ).show()
        } else if (loginField.text.isEmpty()) {
            Toast.makeText(baseContext, "E-mail nemôže ostať prázdny", Toast.LENGTH_SHORT).show()
        } else if (passField.text.isEmpty()) {
            Toast.makeText(baseContext, "Heslo nemôže ostať prázdne", Toast.LENGTH_SHORT).show()
        } else
            doLogin()
    }
}