/*
 * Copyright (c) 2022-2024 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex08_widgetsandadapters.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearSnapHelper
import upv.dadm.ex08_widgetsandadapters.data.DataSource
import upv.dadm.ex08_widgetsandadapters.databinding.ActivityRecyclerViewBinding
import upv.dadm.ex08_widgetsandadapters.ui.adapters.ProvinceGridRecyclerAdapter
import upv.dadm.ex08_widgetsandadapters.ui.adapters.ProvinceRecyclerAdapter

/**
 * Displays Spanish Provinces objects in List or Grid format using RecyclerViews
 * with different LayoutManagers (Linear + Vertical/Horizontal, Grid + Vertical).
 * Each item can be clicked to display a message with the province's name, and
 * long clicked to remove it from the RecyclerView.
 */
class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        // Enable edge-to-edge display
        enableEdgeToEdge()
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)
        // Prevent the layout from overlapping with system bars in edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Attach adapter for the RecyclerView with Vertical LinearLayoutManager
        binding.rvProvincesVertical.adapter = ProvinceRecyclerAdapter(
            DataSource.getProvincesArray(this@RecyclerViewActivity),
            ::adapterOnClick,
            ::verticalAdapterOnLongClick
        )

        // Attach adapter for the RecyclerView with Horizontal LinearLayoutManager
        binding.rvProvincesHorizontal.adapter = ProvinceRecyclerAdapter(
            DataSource.getProvincesArray(this@RecyclerViewActivity),
            ::adapterOnClick,
            ::horizontalAdapterOnLongClick
        )
        // Attach a SnapHelper to ensure that when the RecyclerView stops scrolling
        // the current item is fully displayed on the screen
        LinearSnapHelper().attachToRecyclerView(binding.rvProvincesHorizontal)

        // Attach adapter for the RecyclerView with Vertical GridLayoutManager
        binding.rvProvincesGrid.adapter = ProvinceGridRecyclerAdapter(
            DataSource.getProvincesArray(this@RecyclerViewActivity),
            ::adapterOnClick,
            ::gridAdapterOnLongClick
        )
    }

    /**
     * Displays the name of the selected province.
     */
    private fun adapterOnClick(name: CharSequence) {
        Toast.makeText(this@RecyclerViewActivity, name, Toast.LENGTH_SHORT).show()
    }

    /**
     * Removes the selected province from the RecyclerView with Vertical LinearLayoutManager.
     */
    private fun verticalAdapterOnLongClick(position: Int) {
        (binding.rvProvincesVertical.adapter as ProvinceRecyclerAdapter).removeProvince(position)
    }

    /**
     * Removes the selected province from the RecyclerView with Horizontal LinearLayoutManager.
     */
    private fun horizontalAdapterOnLongClick(position: Int) {
        (binding.rvProvincesHorizontal.adapter as ProvinceRecyclerAdapter).removeProvince(position)
    }

    /**
     * Removes the selected province from the RecyclerView with Vertical GridLayoutManager.
     */
    private fun gridAdapterOnLongClick(position: Int) {
        (binding.rvProvincesGrid.adapter as ProvinceGridRecyclerAdapter).removeProvince(position)
    }
}