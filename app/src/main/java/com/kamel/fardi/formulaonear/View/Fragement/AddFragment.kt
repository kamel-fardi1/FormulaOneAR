package com.kamel.fardi.formulaonear.View.Fragement


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.ar.sceneform.ux.ArFragment
import com.kamel.fardi.formulaonear.R

class AddFragment  : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedText = arguments?.getString("selectedText")
        // Load model.glb from assets folder or http url
        val arFragment = childFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        arFragment.setOnTapPlaneGlbModel(selectedText ?: "formularedbull.glb")

        // arFragment.setOnTapPlaneGlbModel("https://github.com/codemaker2015/3d-models/blob/master/glb_files/model.glb?raw=true")
    }
}