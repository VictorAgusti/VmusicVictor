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
import es.vag.vmusic.databinding.FragmentReggaeBinding
import java.lang.ClassCastException

class ReggaeFragment : Fragment() {
    private lateinit var binding: FragmentReggaeBinding
    private var mListener: ReggaeFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReggaeBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface ReggaeFragment {
        fun onMarleyButtonClicked()
        fun onShaggyButtonCicked()


    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? ReggaeFragment
        if (mListener == null) {
            throw ClassCastException("Must implement ReggaeFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesReggae = mutableListOf<Cantante>(
            Cantante("Bob Marley", R.drawable.bob_marley),
            Cantante("Shaggy", R.drawable.shaggy),
        )
        val clickFunction = { cantante: Cantante ->
            if (cantante.name == "Bob Marley") {
                mListener?.onMarleyButtonClicked()
            }
            if (cantante.name == "Shaggy") {
                mListener?.onShaggyButtonCicked()
            }
        }
        val cantantesAdapter = CantantesAdapter(cantantesReggae,context,clickFunction)
        binding.recyclerCantantesReggae.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesReggae.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesReggae.adapter = cantantesAdapter


    }
}
