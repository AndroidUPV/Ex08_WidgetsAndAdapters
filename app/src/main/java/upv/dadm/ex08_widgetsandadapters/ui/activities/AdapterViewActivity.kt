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
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import upv.dadm.ex08_widgetsandadapters.R
import upv.dadm.ex08_widgetsandadapters.data.DataSource
import upv.dadm.ex08_widgetsandadapters.ui.adapters.ProvinceAdapter

// Constants identifying the type of View used to display the information of Spanish provinces
const val DEFAULT = 0
const val LIST_VIEW = 1
const val GRID_VIEW = 2

// Constant used to send the selected type of adapter List/GridView
const val TYPE_OF_ADAPTER = "upv.dadm.ex08_widgetsandadapters.ui.activities.TYPE_OF_ADAPTER"

/**
 * Displays Spanish Provinces objects in List or Grid format using a ListView or gridView,
 * respectively (both of them are superseded and RecyclerView is recommended instead).
 * Each item can be clicked to display a message with the province's name, and
 * long clicked to remove it from the List/Grid.
 */
class AdapterViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the selected Activity layout and title,
        // the layout to display each item on the AdapterView,
        // and the reference to the AdapterView (ListView/GridView)
        var activityLayout = R.layout.activity_list_view
        var itemLayout = R.layout.layout_province_list
        var adapterViewId = R.id.lvProvinces

        when (intent.getIntExtra(TYPE_OF_ADAPTER, LIST_VIEW)) {
            GRID_VIEW -> {
                activityLayout = R.layout.activity_grid_view
                itemLayout = R.layout.layout_province_grid
                adapterViewId = R.id.gvProvinces
            }

            else -> {
                // Already initialised to LIST_VIEW (by default)
            }
        }
        // Set the activity content to that of the inflated resource layout
        setContentView(activityLayout)

        // Create the adapter that generates the Views from the data array
        // to be displayed in the ListView/GridView
        val adapter = ProvinceAdapter(
            this@AdapterViewActivity,
            itemLayout,
            DataSource.getProvincesArray(this@AdapterViewActivity)
        )
        // Set the adapter to the ListView/GridView
        val adapterView = findViewById<AdapterView<Adapter>>(adapterViewId)
        adapterView.adapter = adapter

        // This listener will be executed when any element within the ListView/GridView is clicked
        adapterView.setOnItemClickListener { _, _, position, _ ->
            // Display a message with the name of the province clicked
            Toast.makeText(
                this@AdapterViewActivity,
                adapter.getItem(position)?.name,
                Toast.LENGTH_SHORT
            ).show()
        }
        // This listener will be executed when any element within the ListView/GridView is long clicked
        adapterView.setOnItemLongClickListener { _, _, position, _ ->
            // Remove the element that has been long clicked from the array
            adapter.remove(adapter.getItem(position))
            return@setOnItemLongClickListener true
        }
    }
}
