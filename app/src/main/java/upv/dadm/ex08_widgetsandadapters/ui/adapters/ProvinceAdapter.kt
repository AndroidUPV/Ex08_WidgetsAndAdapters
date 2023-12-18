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

package upv.dadm.ex08_widgetsandadapters.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import upv.dadm.ex08_widgetsandadapters.R
import upv.dadm.ex08_widgetsandadapters.model.Province

/**
 * Custom adapter to generate the Views required to display the information
 * of Spanish provinces in a ListView/GridView.
 * A ViewHolder pattern is used to hold references to the Views of each item displayed.
 */
class ProvinceAdapter(context: Context, private val resource: Int, data: ArrayList<Province>) :
    ArrayAdapter<Province>(context, resource, data) {

    /**
     * Holds a reference to all the elements within the Views.
     */
    private class ProvinceViewHolder(view: View) {
        // Get a reference for each element within the View
        // The ProvinceHolder will keep them
        private val flag: ImageView = view.findViewById(R.id.ivProvinceFlag)
        private val name: TextView = view.findViewById(R.id.tvProvinceName)
        private val plate: TextView = view.findViewById(R.id.tvProvincePlate)

        /**
         * Fills each element within the View with the provided Province object.
         */
        fun bind(province: Province) {
            flag.setImageResource(province.flag)
            name.text = province.name
            plate.text = province.plate
        }
    }

    /**
     *  Gets the View to display the data corresponding to the Spanish province indexed by position.
     *  Views are recycled, so only those fitting into the screen are actually created.
     *  A Holder pattern is used to speed up retrieving the reference to elements within each View.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val resultView: View
        // Create the View if it has not been created yet
        if (convertView == null) {
            // Create a View from the selected layout
            resultView = LayoutInflater.from(context).inflate(resource, parent, false)
            // Associate the Holder with the View
            resultView.tag = ProvinceViewHolder(resultView)
        } else {
            // View to be reused, if possible (not null)
            resultView = convertView
        }

        // Retrieve the Holder associated with the View (necessary for reused Views)
        val holder = resultView.tag as ProvinceViewHolder
        // Fill each element within the View with the required data from the array
        holder.bind(getItem(position) as Province)
        // Return the updated View
        return resultView
    }
}