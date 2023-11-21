package es.vag.vmusic.login

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import es.vag.vmusic.R

class FirstDialogFragment : DialogFragment(){
    private lateinit var mListener: FirstDialogListener

    interface FirstDialogListener{
        fun onDialogPositiveClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is FirstDialogListener){
            mListener = context
        }else{
            throw Exception("Your fragment or activity must implement the interface FirstDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater
            builder
                .setView(inflater.inflate(R.layout.dialog_layout,null))
                .setPositiveButton("Confirm") { dialog, id ->
                    mListener.onDialogPositiveClick()
                }
            builder.create()
        } ?: throw java.lang.IllegalStateException("Activity cant be null")
    }
}