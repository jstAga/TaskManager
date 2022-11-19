package com.geektech.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref(private val context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("pref_name", MODE_PRIVATE)

    // onBoarding
    fun isOnBoardingShow(): Boolean {
        return pref.getBoolean(BOARDING_SHOW, false)
    }

    fun saveShowBoarding(isShow: Boolean) {
        pref.edit().putBoolean(BOARDING_SHOW, isShow).apply()
    }


    // title
    fun getTitle(): String? {
        return pref.getString(TITLE_PROFILE, "")
    }

    fun saveTitle(title: String) {
        pref.edit().putString(TITLE_PROFILE, title).apply()
    }


    // description
    fun getDescription(): String? {
        return pref.getString(DESCRIPTION_PROFILE, "")
    }

    fun saveDescription(description: String) {
        pref.edit().putString(DESCRIPTION_PROFILE, description).apply()
    }

    companion object {
        private const val BOARDING_SHOW = "onboarding.show"
        private const val TITLE_PROFILE = "title"
        private const val DESCRIPTION_PROFILE = "description"
    }
}