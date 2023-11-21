package es.vag.vmusic.main_menus

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
import es.vag.vmusic.cantantesFragments.AmyFragment
import es.vag.vmusic.cantantesFragments.ArianaFragment
import es.vag.vmusic.cantantesFragments.BadBunnyFragment
import es.vag.vmusic.cantantesFragments.BeatlesFragment
import es.vag.vmusic.cantantesFragments.BeethovenFragment
import es.vag.vmusic.cantantesFragments.BrooksFragment
import es.vag.vmusic.cantantesFragments.CentFragment
import es.vag.vmusic.cantantesFragments.EminemFragment
import es.vag.vmusic.cantantesFragments.HarryFragment
import es.vag.vmusic.cantantesFragments.LadyFragment
import es.vag.vmusic.cantantesFragments.MarleyFragment
import es.vag.vmusic.cantantesFragments.McgrawFragment
import es.vag.vmusic.cantantesFragments.MoraFragment
import es.vag.vmusic.cantantesFragments.MozartFragment
import es.vag.vmusic.cantantesFragments.MykeFragment
import es.vag.vmusic.cantantesFragments.NinaFragment
import es.vag.vmusic.cantantesFragments.QueenFragment
import es.vag.vmusic.cantantesFragments.RollingFragment
import es.vag.vmusic.cantantesFragments.ShaggyFragment
import es.vag.vmusic.cantantesFragments.SinatraFragment
import es.vag.vmusic.cantantesFragments.StraitFragment
import es.vag.vmusic.cantantesFragments.TupacFragment
import es.vag.vmusic.cantantesFragments.VivaldiFragment
import es.vag.vmusic.databinding.ActivityMainBinding
import es.vag.vmusic.generos.ClassicalFragment
import es.vag.vmusic.generos.CountryFragment
import es.vag.vmusic.generos.JazzFragment
import es.vag.vmusic.generos.LatinFragment
import es.vag.vmusic.generos.PopFragment
import es.vag.vmusic.generos.RapFragment
import es.vag.vmusic.generos.ReggaeFragment
import es.vag.vmusic.generos.RockFragment
import es.vag.vmusic.login.LoginActivity


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener,
    GeneroFragment.GenerosFragment,
    PopFragment.PopFragment, RockFragment.RockFragment, JazzFragment.JazzFragment, CountryFragment.CountryFragment,
    LatinFragment.LatinFragment, ReggaeFragment.ReggaeFragment, RapFragment.RapFragment,
    ClassicalFragment.ClassicalFragment{
    private lateinit var binding: ActivityMainBinding
    private val CAMERA_REQUEST_CODE = 200

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
            R.id.action_logout ->{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }

    override  fun onNavigationItemSelected(item: MenuItem) = when(item.itemId){
        R.id.item_genero -> {
            supportFragmentManager.commit {
                replace<GeneroFragment>(R.id.mainFragmentContainer)
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
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),CAMERA_REQUEST_CODE )
            } else {
                Toast.makeText(this,R.string.Permiso_concedido,Toast.LENGTH_SHORT).show()
            }
            true
        }
        R.id.item_home -> {
            supportFragmentManager.commit {
                replace<BienvenidaFragment>(R.id.mainFragmentContainer)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            true
        }
        else -> false
    }

    override fun onPopButtonClicked() {
        supportFragmentManager.commit {
            replace<PopFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == CAMERA_REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,R.string.Camera_permision_granted,Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,R.string.Camera_permision_denied,Toast.LENGTH_SHORT).show()
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

    override fun onRockButtonCicked() {
        supportFragmentManager.commit {
            replace<RockFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }

    }

    override fun onJazzButtonCicked() {
        supportFragmentManager.commit {
            replace<JazzFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onCountryButtonClicked() {
        supportFragmentManager.commit {
            replace<CountryFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onLatinButtonClicked() {
        supportFragmentManager.commit {
            replace<LatinFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onReggaeButtonClicked() {
        supportFragmentManager.commit {
            replace<ReggaeFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onRapButtonClicked() {
        supportFragmentManager.commit {
            replace<RapFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onClassicalButtonClicked() {
        supportFragmentManager.commit {
            replace<ClassicalFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onArianaButtonClicked() {
        supportFragmentManager.commit {
            replace<ArianaFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onLadyButtonClicked() {
            supportFragmentManager.commit {
                replace<LadyFragment>(R.id.mainFragmentContainer)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
    }

    override fun onHarryButtonClicked() {
        supportFragmentManager.commit {
            replace<HarryFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
            }
    }

    override fun onBeatlesButtonClicked() {
        supportFragmentManager.commit {
            replace<BeatlesFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onRollingButtonCicked() {
        supportFragmentManager.commit {
            replace<RollingFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onQueenButtonCicked() {
        supportFragmentManager.commit {
            replace<QueenFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onSinatraButtonClicked() {
        supportFragmentManager.commit {
            replace<SinatraFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onAmyButtonCicked() {
        supportFragmentManager.commit {
            replace<AmyFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onNinaButtonCicked() {
        supportFragmentManager.commit {
            replace<NinaFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onMcGrawButtonClicked() {
        supportFragmentManager.commit {
            replace<McgrawFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onStraitButtonCicked() {
        supportFragmentManager.commit {
            replace<StraitFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onBrooksButtonCicked() {
        supportFragmentManager.commit {
            replace<BrooksFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onBadBunnyButtonClicked() {
        supportFragmentManager.commit {
            replace<BadBunnyFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onMykeButtonCicked() {
        supportFragmentManager.commit {
            replace<MykeFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onMoraButtonCicked() {
        supportFragmentManager.commit {
            replace<MoraFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onMarleyButtonClicked() {
        supportFragmentManager.commit {
            replace<MarleyFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onShaggyButtonCicked() {
        supportFragmentManager.commit {
            replace<ShaggyFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onEminemButtonClicked() {
        supportFragmentManager.commit {
            replace<EminemFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onTupacButtonCicked() {
        supportFragmentManager.commit {
            replace<TupacFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onCentButtonCicked() {
        supportFragmentManager.commit {
            replace<CentFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onMozartButtonClicked() {
        supportFragmentManager.commit {
            replace<MozartFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onBeethovenButtonCicked() {
        supportFragmentManager.commit {
            replace<BeethovenFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onVivaldiButtonCicked() {
        supportFragmentManager.commit {
            replace<VivaldiFragment>(R.id.mainFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

}