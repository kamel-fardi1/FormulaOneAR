package com.kamel.fardi.formulaonear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.sceneform.ux.ArFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load model.glb from assets folder or http url
        val arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        arFragment.setOnTapPlaneGlbModel("formula1_alpine.glb")
        
        //        arFragment.setOnTapPlaneGlbModel("https://github.com/codemaker2015/3d-models/blob/master/glb_files/model.glb?raw=true")
    }

}