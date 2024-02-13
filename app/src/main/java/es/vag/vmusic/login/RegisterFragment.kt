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
import es.vag.vmusic.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment(), View.OnClickListener {
  private lateinit var binding: FragmentRegisterBinding
    private lateinit var mListener: RegisterFragmentListener

    private val authManager: AuthManager by lazy { AuthManager() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener(this)
        binding.btnBackLogin.setOnClickListener(this)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is RegisterFragmentListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface RegisterFragmentListener")
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnRegister -> {
                val email = binding.etUsername.text.toString()
                val pass = binding.etPassword.text.toString()
                val pass2 = binding.etPassword2.text.toString()

                if(pass != pass2){
                    Toast.makeText(requireContext(), R.string.error_no_same_pass, Toast.LENGTH_SHORT).show()
                }else {
                    if (!email.isNullOrBlank() && !pass.isNullOrBlank()) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            val userRegistered = authManager.createUser(email, pass)
                            withContext(Dispatchers.Main) {
                                if (userRegistered != null) {
                                    mListener.onRegistered()
                                } else {//ERROR
                                    Toast.makeText(
                                        requireContext(),
                                        R.string.bad_credentials,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            R.string.error_no_user_or_pass,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            R.id.btnBackLogin -> {
                mListener.onRegisterBackLoginBtnClick()
            }
        }
    }

    interface RegisterFragmentListener{
        fun onRegistered()
        fun onRegisterBackLoginBtnClick()
    }
}