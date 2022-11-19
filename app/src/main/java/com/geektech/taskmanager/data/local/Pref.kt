package com.geektech.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.io.File

class Pref(private val context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("pref_name", MODE_PRIVATE)

    // onBoarding
    fun isOnBoardingShow(): Boolean {
        return pref.getBoolean(PREF_BOARDING_SHOW, false)
    }

    fun saveShowBoarding(isShow: Boolean) {
        pref.edit().putBoolean(PREF_BOARDING_SHOW, isShow).apply()
    }


    // title
    fun getTitle(): String? {
        return pref.getString(PREF_TITLE_PROFILE, "")
    }

    fun saveTitle(title: String) {
        pref.edit().putString(PREF_TITLE_PROFILE, title).apply()
    }


    // description
    fun getDescription(): String? {
        return pref.getString(PREF_DESCRIPTION_PROFILE, "")
    }

    fun saveDescription(description: String) {
        pref.edit().putString(PREF_DESCRIPTION_PROFILE, description).apply()
    }

    // age
    fun getAge(): String? {
        return pref.getString(PREF_AGE_PROFILE, "")
    }

    fun saveAge(age: String) {
        pref.edit().putString(PREF_AGE_PROFILE, age).apply()
    }

    //image

    fun getImage() : String?{
        return pref.getString(PREF_IMAGE_PROFILE, "")
    }

    fun saveImage(image: String){
        pref.edit().putString(PREF_IMAGE_PROFILE, image).apply()
    }

    companion object {
        private const val PREF_BOARDING_SHOW = "onboarding.show"
        private const val PREF_TITLE_PROFILE = "title"
        private const val PREF_DESCRIPTION_PROFILE = "description"
        private const val PREF_IMAGE_PROFILE = "image"
        private const val PREF_AGE_PROFILE = "age"
    }
}