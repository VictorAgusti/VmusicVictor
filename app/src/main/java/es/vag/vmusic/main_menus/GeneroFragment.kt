package es.vag.vmusic.main_menus
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.vag.vmusic.R
import es.vag.vmusic.databinding.FragmentGeneroBinding
import java.lang.ClassCastException



class GeneroFragment : Fragment() {
    private lateinit var binding: FragmentGeneroBinding
    private var mListener: GenerosFragment? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGeneroBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    interface GenerosFragment {
       fun onPopButtonClicked()
       fun onRockButtonCicked()
       fun onJazzButtonCicked()
       fun onCountryButtonClicked()
       fun onLatinButtonClicked()
       fun onReggaeButtonClicked()
       fun onRapButtonClicked()
       fun onClassicalButtonClicked()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = context as? GenerosFragment
        if (mListener == null) {
            throw ClassCastException("Must implement GenerosFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun setUpRecyclerView(){
        val generos = mutableListOf<Genero>(
            Genero("Pop", R.drawable.logo_generos),
            Genero("Rock", R.drawable.logo_generos),
            Genero("Jazz", R.drawable.logo_generos),
            Genero("Country", R.drawable.logo_generos),
            Genero("Latin", R.drawable.logo_generos),
            Genero("Reggae", R.drawable.logo_generos),
            Genero("Rap", R.drawable.logo_generos),
            Genero("Classical", R.drawable.logo_generos)

        )
        val clickFunction = { genero: Genero ->
            if (genero.name == "Pop"){
                mListener?.onPopButtonClicked()
            }
            if (genero.name == "Rock"){
                mListener?.onRockButtonCicked()
            }
            if (genero.name == "Jazz"){
                mListener?.onJazzButtonCicked()
            }
            if (genero.name == "Country"){
                mListener?.onCountryButtonClicked()
            }
            if (genero.name == "Latin"){
                mListener?.onLatinButtonClicked()
            }
            if (genero.name == "Reggae"){
                mListener?.onReggaeButtonClicked()
            }
            if (genero.name == "Rap"){
                mListener?.onRapButtonClicked()
            }
            if (genero.name == "Classical"){
                mListener?.onClassicalButtonClicked()
            }

        }



        val generosAdapter = GenerosAdapter(generos,context,clickFunction)

        binding.recyclerGeneros.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recyclerGeneros.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        binding.recyclerGeneros.adapter = generosAdapter


    }


}