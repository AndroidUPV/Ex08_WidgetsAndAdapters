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

package upv.dadm.ex08_widgetsandadapters.model

/**
 * A province object consists of a province name, its flag, and its old car plate code.
 */
data class Province(val name: String, val flag: Int, val plate: String)