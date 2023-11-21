package es.vag.vmusic.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.vag.vmusic.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mListener: LoginFragmentListener

    interface LoginFragmentListener{
        fun onLoginbtnClick()
        fun onRegisterBtnClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is LoginFragmentListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface LoginFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.registerbtn.setOnClickListener{
            FirstDialogFragment().show(requireActivity().supportFragmentManager,"Confirm_Dialog")
            mListener.onRegisterBtnClick()
        }
        binding.loginbtn.setOnClickListener{
            mListener.onLoginbtnClick()
        }

        return binding.root
    }


}

