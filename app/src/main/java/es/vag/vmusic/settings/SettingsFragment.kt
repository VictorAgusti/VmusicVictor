package es.vag.vmusic.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import es.vag.vmusic.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}