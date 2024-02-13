package es.vag.vmusic.main_menus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import es.vag.vmusic.databinding.FragmentSearchBinding
import es.vag.vmusic.Adapters.CharacterAdapter
import es.vag.vmusic.offline.AppDB
import es.vag.vmusic.search_function.Character
import es.vag.vmusic.search_function.RetrofitObject
import es.vag.vmusic.search_function.RickAndMortyAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mAdapter: CharacterAdapter
    private lateinit var listCharacter:  MutableList<es.vag.vmusic.search_function.Character>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setUpRecycler()

        binding.searchButton.setOnClickListener {
            searchCharacterStatus(binding.statusEditText.text.toString())
        }
        return binding.root
    }

    private fun searchCharacterStatus(status: String) {
        if(!status.isNullOrEmpty()) {

                    if (status == "Alive" || status == "Dead" || status == "unknown") {
                        lifecycleScope.launch(Dispatchers.IO) {
                            val call = RetrofitObject.getInstance()
                                .create(RickAndMortyAPI::class.java).getCharacters(status)
                            val response = call.results
                            withContext(Dispatchers.Main) {
                                updateCharacters(response.subList(0, 10))
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(),
                            "Error getting the characters",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
        }else{
            Toast.makeText(requireContext(),
                "You must type a status",
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun updateCharacters(characters: List<es.vag.vmusic.search_function.Character>){
        listCharacter.clear()
        listCharacter.addAll(characters)
        mAdapter.notifyDataSetChanged()
    }

    private fun setUpRecycler() {
        listCharacter = emptyList<Character>().toMutableList()
        mAdapter = CharacterAdapter(listCharacter)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = mAdapter
    }


}

