package com.example.meditate2.auth

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.meditate2.R
import com.example.meditate2.photoactivities.PhotosActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        val btn: Button = view.findViewById(R.id.btn_login)


        btn.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "You need to fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    d("MainRe", "Successfully signed in: ${it.result?.user?.uid}")
                    val intent = Intent(activity, PhotosActivity::class.java)
                    startActivity(intent)
                    activity?.finish()

                }
                .addOnFailureListener {
                    Toast.makeText(activity, "{$it.message}", Toast.LENGTH_SHORT).show()
                    d("MainRe", "Unsuccessful")
                }
        }

        return view
    }
}
