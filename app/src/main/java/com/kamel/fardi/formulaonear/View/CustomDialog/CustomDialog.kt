package com.kamel.fardi.formulaonear.View.CustomDialog


import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.lifecycle.*
import com.kamel.fardi.formulaonear.R
import com.kamel.fardi.formulaonear.Utils.*

class CustomDialog() : LifecycleService()  {
    private lateinit var MySharedPref: SharedPreferences
    //
    val sound = PlayMusic()
    fun ShowCustomDialog(context: Context?, view: View) {
        val dialog = Dialog(context!!)
        dialog.setContentView(view)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //Make it TRANSPARENT
        dialog.window!!.getAttributes().windowAnimations = R.style.DialogAnimation; //Set Animation
        dialog.show()
        sound.SoundNotification(context!!)
    }
}