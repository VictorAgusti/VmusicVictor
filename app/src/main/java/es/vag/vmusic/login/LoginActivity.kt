package es.vag.vmusic.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import es.vag.vmusic.main_menus.MainActivity
import es.vag.vmusic.R
import es.vag.vmusic.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() , FirstDialogFragment.FirstDialogListener,
    LoginFragment.LoginFragmentListener {
    private var registered = false
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onDialogPositiveClick() {
        Snackbar.make(binding.root,getString(R.string.RegistroCompletado), Snackbar.LENGTH_SHORT).show()
    }

    override fun onLoginbtnClick() {
        if (registered){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else{
            Snackbar.make(binding.root,getString(R.string.RegistroIncompleto), Snackbar.LENGTH_SHORT)
                .setAction("Registrarse ahora"){
                    FirstDialogFragment().show(this.supportFragmentManager,"Confirm_Dialog")
                    onRegisterBtnClick()
                }
                .show()
        }

    }

    override fun onRegisterBtnClick() {
        registered = true
    }
}