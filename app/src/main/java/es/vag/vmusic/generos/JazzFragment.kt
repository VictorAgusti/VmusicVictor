package es.vag.vmusic.generos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.vag.vmusic.main_menus.Cantante
import es.vag.vmusic.main_menus.CantantesAdapter
import es.vag.vmusic.R
import es.vag.vmusic.databinding.FragmentJazzBinding
import java.lang.ClassCastException


class JazzFragment : Fragment() {
    private lateinit var binding: FragmentJazzBinding
    private var mListener: JazzFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJazzBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface JazzFragment {
        fun onSinatraButtonClicked()
        fun onAmyButtonCicked()
        fun onNinaButtonCicked()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? JazzFragment
        if (mListener == null) {
            throw ClassCastException("Must implement JazzFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesJazz = mutableListOf<Cantante>(
            Cantante("Frank Sinatra", R.drawable.sinatra),
            Cantante("Amy Winehouse", R.drawable.amy_winehouse),
            Cantante("Nina Simone", R.drawable.nina_simone)
        )
        val clickFunction = { cantante: Cantante ->
            if (cantante.name == "Frank Sinatra") {
                mListener?.onSinatraButtonClicked()
            }
            if (cantante.name == "Amy Winehouse") {
                mListener?.onAmyButtonCicked()
            }
            if (cantante.name == "Nina Simone") {
                mListener?.onNinaButtonCicked()
            }
        }
        val cantantesAdapter = CantantesAdapter(cantantesJazz,context,clickFunction)
        binding.recyclerCantantesJazz.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesJazz.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesJazz.adapter = cantantesAdapter


    }
}
