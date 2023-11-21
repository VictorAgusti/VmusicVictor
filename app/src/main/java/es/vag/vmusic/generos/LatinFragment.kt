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
import es.vag.vmusic.databinding.FragmentLatinBinding
import java.lang.ClassCastException


class LatinFragment : Fragment() {

    private lateinit var binding: FragmentLatinBinding
    private var mListener: LatinFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLatinBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface LatinFragment {
        fun onBadBunnyButtonClicked()
        fun onMykeButtonCicked()
        fun onMoraButtonCicked()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? LatinFragment
        if (mListener == null) {
            throw ClassCastException("Must implement LatinFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesLatinos = mutableListOf<Cantante>(
            Cantante("Bad Bunny", R.drawable.bad_bunny),
            Cantante("Myke Towers", R.drawable.myke_towers),
            Cantante("Mora", R.drawable.mora)
        )
        val clickFunction = { cantante: Cantante ->
            if (cantante.name == "Bad Bunny") {
                mListener?.onBadBunnyButtonClicked()
            }
            if (cantante.name == "Myke Towers") {
                mListener?.onMykeButtonCicked()
            }
            if (cantante.name == "Mora") {
                mListener?.onMoraButtonCicked()
            }
        }
        val cantantesAdapter = CantantesAdapter(cantantesLatinos,context,clickFunction)
        binding.recyclerCantantesLatinos.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesLatinos.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesLatinos.adapter = cantantesAdapter


    }
}
