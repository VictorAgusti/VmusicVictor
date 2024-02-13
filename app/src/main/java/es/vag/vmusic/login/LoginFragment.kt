package es.vag.vmusic.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import es.vag.vmusic.Managers.AuthManager
import es.vag.vmusic.R
import es.vag.vmusic.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mListener: LoginFragmentListener

    private val authManager: AuthManager by lazy { AuthManager() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginbtn.setOnClickListener(this)
        binding.registerbtn.setOnClickListener(this)
        binding.recuperarContrasenya.setOnClickListener(this)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is LoginFragmentListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface LoginFragmentListener")
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.loginbtn -> {
                val email = binding.usernametxt.text.toString()
                val pass = binding.passwordtxt.text.toString()
                if(!email.isNullOrBlank() && ! pass.isNullOrBlank()){
                    lifecycleScope.launch(Dispatchers.IO) {
                        val userLogged = authManager.login(email,pass)
                        withContext(Dispatchers.Main){
                            if(userLogged != null){
                                //Toast.makeText(requireContext(), userLogged.email, Toast.LENGTH_SHORT).show()
                                mListener.onLogged()
                            }else{//ERROR
                                Toast.makeText(requireContext(), R.string.bad_credentials, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }else{
                    Toast.makeText(requireContext(), R.string.error_no_user_or_pass, Toast.LENGTH_SHORT).show()
                }
            }
            R.id.registerbtn -> {
                mListener.onRegisterBtnClick()
            }
            R.id.recuperar_contrasenya -> {
                mListener.onForgotPasswordBtnClick()
            }
        }
    }


    interface LoginFragmentListener{
        fun onLogged()
        fun onRegisterBtnClick()
        fun onForgotPasswordBtnClick()
    }


}

