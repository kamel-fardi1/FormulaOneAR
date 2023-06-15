package com.kamel.fardi.formulaonear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextAdapter(
    private val texts: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_text, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val text = texts[position]
        holder.textView.text = text
        holder.itemView.setOnClickListener {
            onItemClick(text)
        }
    }

    override fun getItemCount(): Int {
        return texts.size
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
