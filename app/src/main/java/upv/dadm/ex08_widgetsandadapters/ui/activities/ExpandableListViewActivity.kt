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
import upv.dadm.ex08_widgetsandadapters.data.DataSource
import upv.dadm.ex08_widgetsandadapters.databinding.ActivityExpandableListViewBinding
import upv.dadm.ex08_widgetsandadapters.model.Province
import upv.dadm.ex08_widgetsandadapters.ui.adapters.ProvinceExpandableAdapter

/**
 * Displays Spanish Communities and Provinces objects in a List using an ExpandableListView.
 * Communities can be clicked to expand and display their Provinces.
 * Each Province can be clicked to display a message with its name.
 */
class ExpandableListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivityExpandableListViewBinding.inflate(layoutInflater)
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

        // Create the adapter that generates the Views from the data array
        // to be displayed in the ExpandableListView
        val adapter = ProvinceExpandableAdapter(
            DataSource.getCommunities(this@ExpandableListViewActivity),
            DataSource.getProvincesPerCommunity(this@ExpandableListViewActivity)
        )
        // Set the adapter to the ExpandableListView
        binding.elvProvinces.setAdapter(adapter)

        // This listener will be executed when any child within the ExpandableListView is clicked
        binding.elvProvinces.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val province = adapter.getChild(groupPosition, childPosition) as Province
            Toast.makeText(
                this@ExpandableListViewActivity, province.name, Toast.LENGTH_SHORT
            ).show()
            return@setOnChildClickListener true
        }
    }
}