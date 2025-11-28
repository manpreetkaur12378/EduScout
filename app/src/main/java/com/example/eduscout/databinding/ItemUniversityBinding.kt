package com.example.eduscout.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.eduscout.R

class ItemUniversityBinding private constructor(
    val root: View,
    val tvName: TextView,
    val tvProvince: TextView,
    val tvWeb: TextView
) {
    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup? = null, attachToParent: Boolean = false): ItemUniversityBinding {
            val view = inflater.inflate(R.layout.item_university, parent, attachToParent)
            val tvName = view.findViewById<TextView>(R.id.tvName)
            val tvProvince = view.findViewById<TextView>(R.id.tvProvince)
            val tvWeb = view.findViewById<TextView>(R.id.tvWeb)
            return ItemUniversityBinding(view, tvName, tvProvince, tvWeb)
        }
    }
}
