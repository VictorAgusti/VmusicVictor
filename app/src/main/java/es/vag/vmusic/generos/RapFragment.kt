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
import es.vag.vmusic.databinding.FragmentRapBinding
import java.lang.ClassCastException

class RapFragment : Fragment() {
    private lateinit var binding: FragmentRapBinding
    private var mListener: RapFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRapBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface RapFragment {
        fun onEminemButtonClicked()
        fun onTupacButtonCicked()
        fun onCentButtonCicked()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? RapFragment
        if (mListener == null) {
            throw ClassCastException("Must implement RapFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesRap = mutableListOf<Cantante>(
            Cantante("Eminem", R.drawable.eminem),
            Cantante("Tupac", R.drawable.tupac),
            Cantante("50cent", R.drawable.cent)
        )
        val clickFunction = { cantante: Cantante ->
            if (cantante.name == "Eminem") {
                mListener?.onEminemButtonClicked()
            }
            if (cantante.name == "Tupac") {
                mListener?.onTupacButtonCicked()
            }
            if (cantante.name == "50cent") {
                mListener?.onCentButtonCicked()
            }
        }
        val cantantesAdapter = CantantesAdapter(cantantesRap,context,clickFunction)
        binding.recyclerCantantesRap.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesRap.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesRap.adapter = cantantesAdapter


    }
}