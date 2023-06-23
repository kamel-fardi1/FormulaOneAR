package com.kamel.fardi.formulaonear
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamel.fardi.formulaonear.R
import com.kamel.fardi.formulaonear.View.Fragement.AddFragment

class texts : Fragment() {

     val textss = listOf("f1.glb", "formula1_apline.glb", "formularedbull.glb")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TextAdapter(textss) { text ->
            navigateToDetail(text)
        }
        recyclerView.adapter = adapter
    }

    private fun navigateToDetail(text: String) {
        val detailFragment = AddFragment().apply {
            arguments = Bundle().apply {
                putString("selectedText", text)
            }
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
