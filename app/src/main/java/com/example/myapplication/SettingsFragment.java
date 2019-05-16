package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.pref_settings,s);
        updatingSummaryEditTextPreferences(getPreferenceScreen().getSharedPreferences());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        updatingSummaryEditTextPreferences(sharedPreferences);
    }

    private void updatingSummaryEditTextPreferences(SharedPreferences sharedPreferences) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();
        for (int i = 0; i<count; i++)
        {
            Preference preference = preferenceScreen.getPreference(i);
            if (preference instanceof EditTextPreference)
            {
                EditTextPreference editTextPreference = (EditTextPreference) preference;
                String value = sharedPreferences.getString(editTextPreference.getKey(),"");
                editTextPreference.setSummary(value);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
