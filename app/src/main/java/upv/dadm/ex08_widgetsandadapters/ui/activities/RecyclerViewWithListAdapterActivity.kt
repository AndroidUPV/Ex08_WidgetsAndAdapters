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
import upv.dadm.ex08_widgetsandadapters.data.DataSource
import upv.dadm.ex08_widgetsandadapters.databinding.ActivityRecyclerViewListAdapterBinding
import upv.dadm.ex08_widgetsandadapters.model.Province
import upv.dadm.ex08_widgetsandadapters.ui.adapters.ProvinceListAdapter

/**
 * Displays Spanish Provinces objects in List format using a RecyclerView
 * with a Vertical LayoutManager.
 * Each item can be clicked to display a message with the province's name, and
 * long clicked to remove it from the RecyclerView.
 * The adapter handles changes in data by computing the diff between the old and the new arrays.
 */
class RecyclerViewWithListAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewListAdapterBinding
    private lateinit var provinceList: ArrayList<Province>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        binding = ActivityRecyclerViewListAdapterBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Get the list of provinces, which is the original data to work with
        provinceList = DataSource.getProvincesArray(this@RecyclerViewWithListAdapterActivity)

        // Adapter for the RecyclerView with Vertical LinearLayoutManager
        binding.rvProvincesListAdapter.adapter = ProvinceListAdapter(
            ::adapterOnClick,
            ::adapterOnLongClick
        )
        // Submit a new list to be displayed (should be a copy/clone of the original data)
        (binding.rvProvincesListAdapter.adapter as ProvinceListAdapter)
            .submitList(provinceList.toMutableList())
    }

    /**
     * Displays the name of the selected province.
     */
    private fun adapterOnClick(name: CharSequence) {
        Toast.makeText(this@RecyclerViewWithListAdapterActivity, name, Toast.LENGTH_SHORT)
            .show()
    }

    /**
     * Removes the selected province from the RecyclerView with Vertical LinearLayoutManager.
     */
    private fun adapterOnLongClick(position: Int) {
        // Remove the province from the original data
        provinceList.removeAt(position)
        // Submit the new list to be displayed (should be a copy/clone of the original data)
        (binding.rvProvincesListAdapter.adapter as ProvinceListAdapter)
            .submitList(provinceList.toMutableList())
    }

}