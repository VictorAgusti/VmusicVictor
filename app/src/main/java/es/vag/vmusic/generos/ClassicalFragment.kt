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
import es.vag.vmusic.databinding.FragmentClassicalBinding
import java.lang.ClassCastException


class ClassicalFragment : Fragment() {
    private lateinit var binding: FragmentClassicalBinding
    private var mListener: ClassicalFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClassicalBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface ClassicalFragment {
        fun onMozartButtonClicked()
        fun onBeethovenButtonCicked()
        fun onVivaldiButtonCicked()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? ClassicalFragment
        if (mListener == null) {
            throw ClassCastException("Must implement ClassicalFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesClassical = mutableListOf<Cantante>(
            Cantante("Mozart", R.drawable.mozart),
            Cantante("Beethoven", R.drawable.beethoven),
            Cantante("Vivaldi", R.drawable.vivaldi)
        )
        val clickFunction = { cantante: Cantante ->
            if (cantante.name == "Mozart") {
                mListener?.onMozartButtonClicked()
            }
            if (cantante.name == "Beethoven") {
                mListener?.onBeethovenButtonCicked()
            }
            if (cantante.name == "Vivaldi") {
                mListener?.onVivaldiButtonCicked()
            }
        }
        val cantantesAdapter = CantantesAdapter(cantantesClassical,context,clickFunction)
        binding.recyclerCantantesClassical.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesClassical.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesClassical.adapter = cantantesAdapter


    }
}