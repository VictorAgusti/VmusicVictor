package es.vag.vmusic.main_menus

import VideoFragment
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import es.vag.vmusic.R
import es.vag.vmusic.databinding.ActivityMainBinding
import es.vag.vmusic.login.LoginActivity
import es.vag.vmusic.settings.SettingsActivity


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNavigation.setOnItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu,menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }

    override  fun onNavigationItemSelected(item: MenuItem) = when(item.itemId){
        R.id.item_chat -> {
            supportFragmentManager.commit {
                replace<ChatFragment>(R.id.mainFragmentContainer)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            true
        }
        R.id.item_buscar -> {
            supportFragmentManager.commit {
                replace<SearchFragment>(R.id.mainFragmentContainer)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            true
        }
        R.id.item_tendencia -> {
            supportFragmentManager.commit {
                replace<VideoFragment>(R.id.mainFragmentContainer)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            true
        }
        R.id.item_home -> {
            supportFragmentManager.commit {
                replace<SearchFragment>(R.id.mainFragmentContainer)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            true
        }
        else -> false
    }

}