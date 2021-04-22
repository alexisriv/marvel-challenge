package org.sixelasavir.product.marvelfans.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import org.sixelasavir.product.marvelfans.R
import org.sixelasavir.product.marvelfans.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val AUTH_REQUEST_CODE: Int = 7192
    }

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var listener: FirebaseAuth.AuthStateListener? = null
    private val providers: MutableList<AuthUI.IdpConfig> by lazy {
        mutableListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        binding.navView.itemIconTintList = null
        val navController: NavController = findNavController(R.id.contentFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.character_fragment, R.id.event_fragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        listener = FirebaseAuth.AuthStateListener {
            val user = firebaseAuth.currentUser

            if (user == null) {
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.LoginTheme)
                        .build(), AUTH_REQUEST_CODE
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(listener)
    }

    override fun onStop() {
        if (listener != null) firebaseAuth.removeAuthStateListener(listener)
        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when {
            requestCode == AUTH_REQUEST_CODE && resultCode == Activity.RESULT_OK -> {
                Toast.makeText(this, R.string.welcome, Toast.LENGTH_LONG).show()
            }
            else -> {
                // Nothing
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
