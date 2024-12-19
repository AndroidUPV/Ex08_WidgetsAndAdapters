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
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import upv.dadm.ex08_widgetsandadapters.R
import upv.dadm.ex08_widgetsandadapters.databinding.ActivityAutocompleteTextViewBinding

/**
 * Matching provinces are displayed when at least two characters
 * are written in the AutoCompleteTextView.
 */
class AutocompleteTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivityAutocompleteTextViewBinding.inflate(layoutInflater)
        // Enable edge-to-edge display
        enableEdgeToEdge()
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)
        // Get side margins in pixels
        val sideMarginPx = resources.getDimensionPixelSize(R.dimen.side_margins)
        // Prevent the layout from overlapping with system bars in edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sideMarginPx, systemBars.top, sideMarginPx, systemBars.bottom)
            insets
        }

        // Associate an Adapter that provides a View (android.R.layout.simple_dropdown_item_1line)
        // for each data object within an array (R.array.provinces) to the AutocompleteTextView
        binding.actvProvince.setAdapter(
            ArrayAdapter(
                this@AutocompleteTextViewActivity,
                android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.provinces)
            )
        )
    }
}