# Generic-Sorted-LinkedList
Written by Itzik Efraim
December 18, 2019
My implementation of generic sorted linkedlist.
The list can only hold objects that implements the Comparable interface.
Node is implemented as a private inner class.
The head of the linkedlist doesn't hold a value, it points to the first element in the list.
To get an O(1) access to the end of list, I added a pointer to the rear.

