import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.vag.vmusic.R
import es.vag.vmusic.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)

        binding.buttonvideo.setOnClickListener{
            prepareVideo()
            playVideo()
        }
        return binding.root

    }

    private fun prepareVideo(){

        if (binding.editTextText.text.toString() == "mora"){
            binding.videoView.setVideoURI(
                Uri.parse("android.resource://es.vag.vmusic/"+ R.raw.mora)
            )
        }

        else if (binding.editTextText.text.toString() == "feid"){
            binding.videoView.setVideoURI(
                Uri.parse("android.resource://es.vag.vmusic/"+R.raw.feid)
            )
        }
        else if (binding.editTextText.text.toString() == "eladio"){
            binding.videoView.setVideoURI(
                Uri.parse("android.resource://es.vag.vmusic/"+R.raw.eladio)
            )
        }
        else if (binding.editTextText.text.toString() == "saiko"){
            binding.videoView.setVideoURI(
                Uri.parse("android.resource://es.vag.vmusic/"+R.raw.saiko)
            )
        }
        else if (binding.editTextText.text.toString() == "rauw"){
            binding.videoView.setVideoURI(
                Uri.parse("android.resource://es.vag.vmusic/"+R.raw.rauw)
            )
        }
        else{
            Toast.makeText(requireActivity(),"Video no encontrado", Toast.LENGTH_LONG).show()
        }

        val mediaController = android.widget.MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)
        mediaController.setMediaPlayer(binding.videoView)
        binding.videoView.setMediaController(mediaController)


    }

    private fun playVideo(){
        binding.videoView.start()
    }

}