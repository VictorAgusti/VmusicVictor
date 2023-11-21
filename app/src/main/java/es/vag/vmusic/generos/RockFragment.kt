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
import es.vag.vmusic.databinding.FragmentRockBinding
import java.lang.ClassCastException

class RockFragment : Fragment() {
    private lateinit var binding: FragmentRockBinding
    private var mListener: RockFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRockBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }
    interface RockFragment {
        fun onBeatlesButtonClicked()
        fun onRollingButtonCicked()
        fun onQueenButtonCicked()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? RockFragment
        if (mListener == null) {
            throw ClassCastException("Must implement RockFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val cantantesRock = mutableListOf<Cantante>(
            Cantante("Beatles", R.drawable.beatles),
            Cantante("Rolling Stones", R.drawable.rolling_stones),
            Cantante("Queen", R.drawable.queen)
        )
        val clickFunction = { cantante: Cantante ->
            if (cantante.name == "Beatles") {
                mListener?.onBeatlesButtonClicked()
            }
            if (cantante.name == "Rolling Stones") {
                mListener?.onRollingButtonCicked()
            }
            if (cantante.name == "Queen") {
                mListener?.onQueenButtonCicked()
            }
        }
        val cantantesAdapter = CantantesAdapter(cantantesRock,context,clickFunction)
        binding.recyclerCantantesRock.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerCantantesRock.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        binding.recyclerCantantesRock.adapter = cantantesAdapter


    }

}