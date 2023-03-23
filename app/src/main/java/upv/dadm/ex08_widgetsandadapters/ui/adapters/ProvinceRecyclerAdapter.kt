/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex08_widgetsandadapters.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import upv.dadm.ex08_widgetsandadapters.databinding.LayoutProvinceListBinding
import upv.dadm.ex08_widgetsandadapters.model.Province

/**
 * Custom adapter to generate the Views required to display the information
 * of Spanish provinces in a RecyclerView.
 * A ViewHolder keeps the references to all the Views for each item displayed.
 */
class ProvinceRecyclerAdapter(
    private val data: ArrayList<Province>,
    private val onClick: (CharSequence) -> Unit,
    private val onLongClick: (Int) -> Unit
) : RecyclerView.Adapter<ProvinceRecyclerAdapter.ProvinceViewHolder>() {

    /**
     * Holds a ViewBinding with reference to all the elements within the Views
     * and sets listeners to react to any click/longClick on the View.
     */
    class ProvinceViewHolder(
        private val binding: LayoutProvinceListBinding,
        private val onClick: (CharSequence) -> Unit,
        private val onLongClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            // This listener will be executed when the View is clicked
            // to display a message with the name of the province clicked
            itemView.setOnClickListener {
                onClick(binding.tvProvinceName.text)
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
            binding.tvProvinceName.text = province.name
            binding.tvProvincePlate.text = province.plate
            binding.ivProvinceFlag.setImageResource(province.flag)
        }
    }

    /**
     * Creates the ViewBinding and attaches it to a ViewHolder
     * to easily access all the elements within the View.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(
            LayoutProvinceListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick,
            onLongClick
        )
    }

    /**
     * Fills the elements within the View with the required data from the array.
     */
    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) =
        holder.bind(data[position])

    /**
     * Returns the number of items in the adapter.
     */
    override fun getItemCount(): Int = data.size

    /**
     * Removes the Province at the given position in the array and notifies observers.
     */
    fun removeProvince(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}