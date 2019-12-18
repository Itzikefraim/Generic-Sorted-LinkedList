# Generic-Sorted-LinkedList
Written by Itzik Efraim<br />
December 18, 2019<br />
<br />
My implementation of generic sorted linkedlist. <br />
The list can only hold objects that implements the Comparable interface. <br />
Node is implemented as a private inner class. <br />
The head of the linkedlist doesn't hold a value, it points to the first element in the list.<br />
To get an O(1) access to the end of list, I added a pointer to the rear.

