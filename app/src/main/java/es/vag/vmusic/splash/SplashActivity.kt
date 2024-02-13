package es.vag.vmusic.splash
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import es.vag.vmusic.R
import es.vag.vmusic.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        heavyTask()
    }

    private fun heavyTask(){
        lifecycleScope.launch(Dispatchers.IO) {

            //We simulate an expensive task by making the thread suspend for 5 seconds.
            delay(5000)

            withContext(Dispatchers.Main) {
                //When the task has finished,
                // we are going to change to the next Activity
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                //Finish the current activity
                finish()
            }
        }
    }
}