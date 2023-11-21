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
import es.vag.vmusic.databinding.FragmentCountryBinding
import java.lang.ClassCastException


class CountryFragment : Fragment() {
    private lateinit var binding: FragmentCountryBinding
    private var mListener: CountryFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface CountryFragment {
        fun onMcGrawButtonClicked()
        fun onStraitButtonCicked()
        fun onBrooksButtonCicked()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? CountryFragment
        if (mListener == null) {
            throw ClassCastException("Must implement CountryFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesCountry = mutableListOf<Cantante>(
            Cantante("Tim McGraw", R.drawable.tim_mcgraw),
            Cantante("George Strait", R.drawable.george_strait),
            Cantante("Garth Brooks ", R.drawable.garth_brooks)
        )
        val clickFunction = { cantante: Cantante ->
            if (cantante.name == "Tim McGraw") {
                mListener?.onMcGrawButtonClicked()
            }
            if (cantante.name == "George Strait") {
                mListener?.onStraitButtonCicked()
            }
            if (cantante.name == "Garth Brooks") {
                mListener?.onBrooksButtonCicked()
            }
        }
        val cantantesAdapter = CantantesAdapter(cantantesCountry,context,clickFunction)
        binding.recyclerCantantesCountry.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesCountry.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesCountry.adapter = cantantesAdapter


    }
}
