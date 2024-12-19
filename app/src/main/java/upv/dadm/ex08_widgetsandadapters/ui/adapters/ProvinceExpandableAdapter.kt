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

package upv.dadm.ex08_widgetsandadapters.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import upv.dadm.ex08_widgetsandadapters.R
import upv.dadm.ex08_widgetsandadapters.model.Community
import upv.dadm.ex08_widgetsandadapters.model.Province

/**
 * Custom adapter to generate the Views required to display the information
 * of Spanish communities (Group) and their provinces (Child) in an ExpandableListView.
 * A ViewHolder pattern is used to hold references to the Views of each item displayed.
 */
class ProvinceExpandableAdapter(
    private val communities: ArrayList<Community>,
    private val provinces: ArrayList<ArrayList<Province>>
) : BaseExpandableListAdapter() {

    /**
     * Returns the number of communities.
     */
    override fun getGroupCount(): Int = communities.size

    /**
     * Returns the number of provinces for a given community.
     */
    override fun getChildrenCount(groupPosition: Int): Int = provinces[groupPosition].size

    /**
     * Returns the community at a given position.
     */
    override fun getGroup(groupPosition: Int): Any = communities[groupPosition]

    /**
     * Returns the province at a given position within a community also stated by its position.
     */
    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        provinces[groupPosition][childPosition]

    /**
     * Returns the community Id, which must be unique.
     */
    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    /**
     * Returns the child Id, which must be unique within its community.
     */
    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    /**
     * Returns whether an Id always refers to the same object.
     */
    override fun hasStableIds(): Boolean = true

    /**
     * Holds a reference to all the elements within the Views displaying Communities.
     */
    class CommunityViewHolder(view: View) {
        private val tvName = view.findViewById<TextView>(R.id.tvCommunityName)
        private val ivFlag = view.findViewById<ImageView>(R.id.ivCommunityFlag)

        /**
         * Fills each element within the View with the provided Community object.
         */
        fun bind(community: Community) {
            tvName.text = community.name
            ivFlag.setImageResource(community.flag)
        }
    }

    /**
     * Gets the View to display the data corresponding to the Spanish community indexed by position.
     * Views are recycled, so only those fitting into the screen are actually created.
     * A Holder pattern is used to speed up retrieving the reference to elements within each View.
     */
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val resultView: View
        // Create the View if it has not been created yet
        if (convertView == null) {
            // Create a View from the selected layout
            resultView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_group_expandable, parent, false)
            // Associate the Holder with the View
            resultView.tag = CommunityViewHolder(resultView)
        } else {
            // View to be reused, if possible (not null)
            resultView = convertView
        }
        // Fill each element within the View with the required data from the array
        (resultView.tag as CommunityViewHolder).bind(communities[groupPosition])
        // Return the updated View
        return resultView
    }

    /**
     * Holds a reference to all the elements within the Views displaying Provinces.
     */
    class ProvinceViewHolder(view: View) {
        private val tvName = view.findViewById<TextView>(R.id.tvProvinceName)
        private val ivFlag = view.findViewById<ImageView>(R.id.ivProvinceFlag)
        private val tvPlate = view.findViewById<TextView>(R.id.tvProvincePlate)

        /**
         * Fills each element within the View with the provided Province object.
         */
        fun bind(province: Province) {
            tvName.text = province.name
            ivFlag.setImageResource(province.flag)
            tvPlate.text = province.plate
        }
    }

    /**
     * Gets the View to display the data corresponding to the Spanish province indexed by position
     * within a given community also indexed by position.
     * Views are recycled, so only those fitting into the screen are actually created.
     * A Holder pattern is used to speed up retrieving the reference to elements within each View.
     */
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val resultView: View
        // Create the View if it has not been created yet
        if (convertView == null) {
            // Create a View from the selected layout
            resultView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_province_expandable, parent, false)

            // Associate the Holder with the View
            resultView.tag = ProvinceViewHolder(resultView)
        } else {
            // View to be reused, if possible (not null)
            resultView = convertView
        }

        // Fill each element within the View with the required data from the array
        (resultView.tag as ProvinceViewHolder).bind(provinces[groupPosition][childPosition])
        // Return the updated View
        return resultView
    }

    /**
     * Returns whether the provinces are selectable.
     * If so, they can have dividers between them
     */
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}