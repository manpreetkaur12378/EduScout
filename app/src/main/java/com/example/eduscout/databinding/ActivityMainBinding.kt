package com.example.eduscout.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.eduscout.R

class ActivityMainBinding private constructor(
    val root: View,
    val recycler: RecyclerView,
    val tvLastUpdated: TextView,
    val tvError: TextView,
    val fabRefresh: FloatingActionButton
) {
    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup? = null, attachToParent: Boolean = false): ActivityMainBinding {
            val root = inflater.inflate(R.layout.activity_main, parent, attachToParent)
            val recycler = root.findViewById<RecyclerView>(R.id.recycler)
            val tvLastUpdated = root.findViewById<TextView>(R.id.tvLastUpdated)
            val tvError = root.findViewById<TextView>(R.id.tvError)
            val fabRefresh = root.findViewById<FloatingActionButton>(R.id.fabRefresh)

            return ActivityMainBinding(root, recycler, tvLastUpdated, tvError, fabRefresh)
        }
    }
}
