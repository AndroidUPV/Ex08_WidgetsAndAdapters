/*
 * Copyright (c) 2022-2023 Universitat Politècnica de València
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
import androidx.appcompat.app.AppCompatActivity
import upv.dadm.ex08_widgetsandadapters.R
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
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Set the title of the activity
        setTitle(R.string.expandable)

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