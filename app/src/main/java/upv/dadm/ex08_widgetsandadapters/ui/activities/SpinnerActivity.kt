/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex08_widgetsandadapters.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import upv.dadm.ex08_widgetsandadapters.R
import upv.dadm.ex08_widgetsandadapters.databinding.ActivitySpinnerBinding

/**
 * Displays a Spinner with static content and
 * another Spinner with dynamic content (elements can be added/deleted).
 */
class SpinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinnerBinding

    // Adapter for the dynamic Spinner
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Set the title of the Activity
        setTitle(R.string.spinner)

        // Static Spinner: its contents never change

        // Listener to be activated when an item is selected in the Spinner
        binding.spStatic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Displays the currently selected item
                Toast.makeText(
                    this@SpinnerActivity,
                    parent?.selectedItem.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }

        }

        // Dynamic Spinner: its contents may change

        // Adapter that provides a View (android.R.layout.simple_spinner_dropdown_item)
        // for each data object within an array (ArrayList)
        adapter = ArrayAdapter(
            this@SpinnerActivity,
            android.R.layout.simple_spinner_item,
            ArrayList(resources.getStringArray(R.array.provinces).asList())
        )
        // Set the layout to create the dropdown View
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Associate the adapter to the dynamic Spinner
        binding.spDynamic.adapter = adapter

        // Listener to be activated when an item is selected in the Spinner
        binding.spDynamic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Enable the delete Button because there is an item selected
                binding.bDelete.isEnabled = true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Disable the delete Button because there is not any item selected
                binding.bDelete.isEnabled = false
            }
        }

        binding.bAdd.setOnClickListener { addProvince() }
        binding.bDelete.setOnClickListener { removeProvince() }
    }

    // Adds a String to the Spinner if the EditText is not empty
    private fun addProvince() {
        val province = binding.etProvince.text.toString()
        if (province.isNotEmpty()) {
            adapter.add(province)
            // Clear the EditText
            binding.etProvince.text.clear()
        }
        // Hide the soft keyboard
        currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // Removes the selected item from the Spinner
    private fun removeProvince() {
        adapter.remove(binding.spDynamic.selectedItem.toString())
    }

}