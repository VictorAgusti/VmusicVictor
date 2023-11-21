package es.vag.vmusic.cantantesFragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.vag.vmusic.R
import es.vag.vmusic.databinding.FragmentArianaBinding


class ArianaFragment : Fragment(){

    private lateinit var binding: FragmentArianaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArianaBinding.inflate(inflater, container, false)
        val mp = MediaPlayer.create(context, R.raw.ariana_cancion)
        binding.button2.setOnClickListener{
           if(mp.isPlaying){
               mp.pause()
               binding.button2.text = "Play"
           }
           else{
               mp.start()
               binding.button2.text = "Pause"
           }
        }
        return binding.root
    }



}

