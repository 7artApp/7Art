package com.br.seventh_art.view.login.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.seventh_art.databinding.ActivitySignUpBinding
import com.br.seventh_art.view.login.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignUpActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
//        val currentUser = firebaseAuth.currentUser
        //VERIFICA SE EXISTE UM CURRENT USER (SPLASH SCREEN)
        firebaseAuth = FirebaseAuth.getInstance()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun onResume() {
        super.onResume()

        binding.apply {

            buttonSignUp.setOnClickListener { view ->
                createUser(view)
            }
            toolbarSignUp.setNavigationOnClickListener { view -> onBackPressed() }
        }
    }

    fun createUser(view: View) {
        val email = binding.emailSignUp.text.toString()
        val psword = binding.passwordSignUp.text.toString()
        createUserWithEmailPass(email, psword)
    }

    fun signIn(view: View) {
        val email = binding.emailSignUp.text.toString()
        val psword = binding.emailSignUp.text.toString()
        firebaseAuthWithEmailPass(email, psword)
    }

    fun getUser(): FirebaseUser? = firebaseAuth.currentUser ?: null

    private fun createUserWithEmailPass(email: String, psword: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, psword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                Log.v("LOGIN", user?.email ?: "Usuário desconectado")

            } else {
                Log.v("LOGIN", task.exception?.message!!)
            }
        }
    }

    fun firebaseAuthWithEmailPass(email: String, psword: String) {
        firebaseAuth.signInWithEmailAndPassword(email, psword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                Log.v("LOGIN", user?.email ?: "Usuário desconectado")
            } else {
                Log.v("LOGIN", task.exception?.message!!)
            }
        }
    }

    fun signOut(view: View) {
        firebaseAuth.signOut()
        Log.v("LOGIN", "Usuário desconectado")
    }
}





