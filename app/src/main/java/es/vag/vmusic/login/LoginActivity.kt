package es.vag.vmusic.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import es.vag.vmusic.Managers.AuthManager
import es.vag.vmusic.main_menus.MainActivity
import es.vag.vmusic.R
import es.vag.vmusic.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() ,LoginFragment.LoginFragmentListener, RegisterFragment.RegisterFragmentListener,
    RecoveryPasswordFragment.OnRecoveryPasswordFragmentListener {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val currentUser = AuthManager().getCurrentUser()
        if(currentUser != null){
            onLogged()
        }
    }

    override fun onLogged() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRegisterBtnClick() {
        replaceFragment(RegisterFragment())
    }

    override fun onForgotPasswordBtnClick() {
        replaceFragment(RecoveryPasswordFragment())
    }

    override fun onRegistered() {
        onLogged()
    }

    override fun onRegisterBackLoginBtnClick() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onRecoveryPassMailSent() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onRecoveryBackLoginBtnClick() {
        onBackPressedDispatcher.onBackPressed()
    }


    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.commit {
            replace(R.id.FragmentContainer, fragment)
            addToBackStack(null)
            setReorderingAllowed(true)
        }
    }
}