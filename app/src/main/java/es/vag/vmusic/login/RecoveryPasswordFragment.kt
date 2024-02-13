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
import es.vag.vmusic.databinding.FragmentRecoveryPasswordBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecoveryPasswordFragment : Fragment(), View.OnClickListener {
   private lateinit var binding: FragmentRecoveryPasswordBinding
    private lateinit var mListener: OnRecoveryPasswordFragmentListener

    private val authManager: AuthManager by lazy { AuthManager() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecoveryPasswordBinding.inflate(inflater, container, false)

        binding.btnSendMail.setOnClickListener(this)
        binding.btnBackLogin.setOnClickListener(this)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnRecoveryPasswordFragmentListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface onRecoveryPasswordFragmentListener")
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSendMail -> {
                val email = binding.etMail.text.toString()

                if (!email.isNullOrBlank()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val mailSent = authManager.resetPassword(email)
                        withContext(Dispatchers.Main) {
                            if (mailSent) {
                                Toast.makeText(
                                    requireContext(),
                                    R.string.msg_mail_sent,
                                    Toast.LENGTH_SHORT
                                ).show()
                                mListener.onRecoveryPassMailSent()
                            } else {//ERROR
                                Toast.makeText(
                                    requireContext(),
                                    R.string.error,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.error_no_email,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            R.id.btnBackLogin -> {
                mListener.onRecoveryBackLoginBtnClick()
            }
        }
    }



    interface OnRecoveryPasswordFragmentListener{
        fun onRecoveryPassMailSent()
        fun onRecoveryBackLoginBtnClick()
    }
}