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

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import upv.dadm.ex08_widgetsandadapters.databinding.ActivityMainBinding

/**
 * Some Buttons launch different activities to display information about
 * Spanish communities and provinces using sveral Adapter-based elements,
 * like AutoCompleteTextView, Spinner, ListView, GridView, and RecyclerView.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivityMainBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Launch activity to display to display a TextView that autocompletes with Spanish provinces
        binding.bAutocomplete.setOnClickListener {
            launchAdaptersActivity(AutocompleteTextViewActivity::class.java, DEFAULT)
        }
        // Launch activity to display to display Spanish provinces in a Spinner
        binding.bSpinner.setOnClickListener {
            launchAdaptersActivity(SpinnerActivity::class.java, DEFAULT)
        }
        // Launch activity to display to display Spanish provinces in ListView
        binding.bList.setOnClickListener {
            launchAdaptersActivity(AdapterViewActivity::class.java, LIST_VIEW)
        }
        // Launch activity to display to display Spanish provinces in a GridView
        binding.bGrid.setOnClickListener {
            launchAdaptersActivity(AdapterViewActivity::class.java, GRID_VIEW)
        }
        // Launch activity to display to display Spanish communities and provinces in an ExpandableListView
        binding.bExpandable.setOnClickListener {
            launchAdaptersActivity(ExpandableListViewActivity::class.java, DEFAULT)
        }
        // Launch activity to display to display Spanish provinces in several RecyclerViews
        binding.bRecycler.setOnClickListener {
            launchAdaptersActivity(RecyclerViewActivity::class.java, DEFAULT)
        }
        // Launch activity to display to display Spanish provinces in a RecyclerView using a ListAdapter
        binding.bListAdapter.setOnClickListener {
            launchAdaptersActivity(RecyclerViewWithListAdapterActivity::class.java, DEFAULT)
        }
    }

    private fun launchAdaptersActivity(activity: Class<out AppCompatActivity>, listGridFlag: Int) {
        startActivity(
            Intent(this@MainActivity, activity)
                .putExtra(TYPE_OF_ADAPTER, listGridFlag)
        )
    }
}