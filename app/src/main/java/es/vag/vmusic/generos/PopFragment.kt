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
import es.vag.vmusic.databinding.FragmentPopBinding
import java.lang.ClassCastException

class PopFragment : Fragment() {
    private lateinit var binding: FragmentPopBinding
    private var mListener: PopFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface PopFragment {
        fun onArianaButtonClicked()
        fun onLadyButtonClicked()
        fun onHarryButtonClicked()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? PopFragment
        if (mListener == null) {
            throw ClassCastException("Must implement PopFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesPop = mutableListOf<Cantante>(
            Cantante("Ariana Grande", R.drawable.arianna_grande),
            Cantante("Lady Gaga", R.drawable.lady_gaga),
            Cantante("Harry Styles", R.drawable.harry_styles)
        )
            val clickFunction = { cantante: Cantante ->
                if (cantante.name == "Ariana Grande") {
                    mListener?.onArianaButtonClicked()
                }
                if (cantante.name == "Lady Gaga") {
                    mListener?.onLadyButtonClicked()
                }
                if (cantante.name == "Harry Styles") {
                    mListener?.onHarryButtonClicked()
                }
            }
        val cantantesAdapter = CantantesAdapter(cantantesPop,context,clickFunction)
        binding.recyclerCantantesPop.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesPop.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesPop.adapter = cantantesAdapter


    }
}