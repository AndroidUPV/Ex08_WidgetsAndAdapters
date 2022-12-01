# Ex08_WidgetsAndAdapters
Lecture 02 - Development of Graphical User Interfaces (GUI)

Information about Spanish provinces (name, flag, car plate code) is displayed in different fashion using Adapters:
- An AutocompleteTextView suggests the name of the province as the user writes in the field.
- A couple of Spinners displays a dropdown list with the name of all provinces. One of them enables the user to add and delete new names to the displayed list.
- All the information is displayed in a vertical list using a ListView (legacy, its use is not recommended)
- All the information is displayed in a vertical grid using a GridView (legacy, its use is not recommended)
- All the information is displayed in a vertical list using an ExpandableListView (legacy, its use is not recommended). Each entry displays information of a Spanish community (name and flag), and after clicking, it is expanded to display the information of its provinces. Another click collapses the provinces.
- All the information is displayed in a vertical list, a horizontal list, and a vertical grid using RecyclerViews with different LayoutManagers.
- All the information is displayed in a vertical list using a RecyclerView with a ListAdapter that automatically computes the differences between the old and the new list.