/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex08_widgetsandadapters.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import upv.dadm.ex08_widgetsandadapters.R
import upv.dadm.ex08_widgetsandadapters.model.Province

/**
 * Custom adapter to generate the Views required to display the information
 * of Spanish provinces in a RecyclerView.
 * A ViewHolder keeps the references to all the Views for each item displayed.
 * Updates are handled by computing the different between the old and the new arrays.
 */
class ProvinceListAdapter(
    private val onClick: (CharSequence) -> Unit,
    private val onLongClick: (Int) -> Unit
) : ListAdapter<Province, ProvinceListAdapter.ProvinceViewHolder>(ProvinceDiffCallback) {

    /**
     * Computes the diff between two provinces in the array.
     */
    object ProvinceDiffCallback : DiffUtil.ItemCallback<Province>() {
        /**
         * Determine whether two provinces are the same.
         */
        override fun areItemsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem == newItem
        }
        /**
         * Determine whether two provinces have the same data.
         */
        override fun areContentsTheSame(oldItem: Province, newItem: Province): Boolean {
            return oldItem.name == newItem.name
        }

    }

    /**
     * Holds a reference to all the elements within the Views
     * and sets listeners to react to any click/longClick on the View.
     */
    class ProvinceViewHolder(
        itemView: View,
        private val onClick: (CharSequence) -> Unit,
        private val onLongClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvProvinceName)
        private val tvPlate = itemView.findViewById<TextView>(R.id.tvProvincePlate)
        private val ivFlag = itemView.findViewById<ImageView>(R.id.ivProvinceFlag)

        init {
            // This listener will be executed when the View is clicked
            // to display a message with the name of the province clicked
            itemView.setOnClickListener {
                onClick(tvName.text)
            }
            // This listener will be executed when the View is long clicked
            // to remove the long clicked province from the array
            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        /**
         * Fills the elements within the View with provided Province object.
         */
        fun bind(province: Province) {
            tvName.text = province.name
            tvPlate.text = province.plate
            ivFlag.setImageResource(province.flag)
        }

    }

    /**
     * Creates the View from the provided layout resource and attaches it to a ViewHolder
     * to easily access all the elements within the View.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_province_list, parent, false),
            onClick,
            onLongClick
        )
    }

    /**
     * Fills the elements within the View with the required data from the array.
     */
    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}