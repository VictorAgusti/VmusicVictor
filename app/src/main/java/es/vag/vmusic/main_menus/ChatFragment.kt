package es.vag.vmusic.main_menus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.vag.vmusic.Adapters.MessagesAdapter
import es.vag.vmusic.Managers.FirestoreManager
import es.vag.vmusic.Models.Message
import es.vag.vmusic.R
import es.vag.vmusic.databinding.FragmentChatBinding
import es.vag.vmusic.search_function.RetrofitObject
import es.vag.vmusic.search_function.RickAndMortyAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var messages: MutableList<Message>
    private lateinit var mAdapter: MessagesAdapter

    private val firestoreManager: FirestoreManager by lazy { FirestoreManager() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.btnSend.setOnClickListener{
            val body = binding.editTextMessage.text.toString()
            if(!body.isNullOrEmpty()) {
                createNewMessage(body)
                binding.editTextMessage.setText("")
            }else{
                Toast.makeText(requireContext(),
                    "You must type a message",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
        setRecyclerView()
        return binding.root
    }
    private fun setRecyclerView(){
        messages = mutableListOf()
        binding.recyclerViewChat.setLayoutManager(
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        mAdapter = MessagesAdapter(requireContext(), messages)
        binding.recyclerViewChat.adapter = mAdapter

        lifecycleScope.launch (Dispatchers.IO){
            firestoreManager.getMessagesFlow()
                .collect{ messagesUpdated ->
                    messages.clear()
                    messages.addAll(messagesUpdated)
                    withContext(Dispatchers.Main){
                        mAdapter.notifyDataSetChanged()
                    }
                }

        }
    }

    private fun createNewMessage(body: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                    val newMessage = Message(content = body)
                    val inserted = firestoreManager.addMessage(newMessage)

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Snackbar.make(binding.mainConstraint, R.string.message_error, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}