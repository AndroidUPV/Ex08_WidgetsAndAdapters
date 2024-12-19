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

package upv.dadm.ex08_widgetsandadapters.data

import android.content.Context
import upv.dadm.ex08_widgetsandadapters.R
import upv.dadm.ex08_widgetsandadapters.model.Community
import upv.dadm.ex08_widgetsandadapters.model.Province

/**
 * Provides the data required for all Spanish provinces and communities.
 */
object DataSource {

    // Ordered array that indexes all the provinces within a community
    // as returned by the arrays defined as resources
    private val communitiesAndProvinces = arrayOf(
        intArrayOf(3, 11, 15, 19, 21, 24, 31, 39),
        intArrayOf(22, 43, 49),
        intArrayOf(5),
        intArrayOf(23),
        intArrayOf(26, 42),
        intArrayOf(12),
        intArrayOf(1, 14, 16, 20, 44),
        intArrayOf(6, 9, 27, 35, 37, 38, 40, 46, 48),
        intArrayOf(8, 17, 28, 41),
        intArrayOf(2, 13, 45),
        intArrayOf(7, 10),
        intArrayOf(0, 29, 34, 36),
        intArrayOf(25),
        intArrayOf(30),
        intArrayOf(32),
        intArrayOf(33),
        intArrayOf(4, 18, 47)
    )

    /**
     * Returns the array of Spanish Provinces to be displayed in the List/GridView.
     */
    fun getProvincesArray(context: Context): ArrayList<Province> {
        val names = context.resources.getStringArray(R.array.provinces)
        val plates = context.resources.getStringArray(R.array.plates)
        val flags = context.resources.obtainTypedArray(R.array.flags)

        val resultArray = ArrayList<Province>()
        for (i in names.indices) {
            resultArray.add(
                Province(
                    names[i],
                    flags.getResourceId(i, R.drawable.valencia),
                    plates[i]
                )
            )
        }
        flags.recycle()
        return resultArray
    }

    /**
     * Returns an array of Spanish Communities.
     */
    fun getCommunities(context: Context): ArrayList<Community> {
        val names = context.resources.getStringArray(R.array.communities)
        val flags = context.resources.obtainTypedArray(R.array.communities_flags)

        val resultArray = ArrayList<Community>()
        for (i in names.indices) {
            resultArray.add(
                Community(
                    names[i],
                    flags.getResourceId(i, R.drawable.com_valenciana)
                )
            )
        }
        flags.recycle()
        return resultArray
    }

    /**
     * Returns an array of arrays of Provinces with information of Spanish Provinces.
     * First array has an element for each Spanish community.
     * Second array contains a Province for each province of that community.
     */
    fun getProvincesPerCommunity(context: Context): ArrayList<ArrayList<Province>> {
        val names = context.resources.getStringArray(R.array.provinces)
        val plates = context.resources.getStringArray(R.array.plates)
        val flags = context.resources.obtainTypedArray(R.array.flags)

        val resultArray = ArrayList<ArrayList<Province>>()
        var provincesArray: ArrayList<Province>
        for (communityAndProvinces in communitiesAndProvinces) {
            provincesArray = ArrayList()
            for (provinceIndex in communityAndProvinces) {
                provincesArray.add(
                    Province(
                        names[provinceIndex],
                        flags.getResourceId(provinceIndex, R.drawable.valencia),
                        plates[provinceIndex]
                    )
                )
            }
            resultArray.add(provincesArray)
        }
        flags.recycle()
        return resultArray
    }
}