// Generic sorted linkedlist that only supports objets that implement Comparable interface.
// In this implementation the head of the linkedlist is always empty. (data == null)
// I implemented the Node class as a private inner class.
// Because the linkedlist is sorted, insert front and insert end are not necessary
// and insert is the only method for insertion.
// to make rear access  an O(1) operation, I implemented a pointer to the end of the list.
// Written by Itzik Efraim
// December 18, 2019
//
public class GenericSortedLinkedList<T extends Comparable<T>>
{
  // inner generic node class
  private class Node<T>
  {
    private T data;
    private Node<T> next;

    public Node()
    {
      this.data = null;
      this.next = null;
    }

    public T getData()
    {
      return this.data;
    }

    public Node<T> getNext()
    {
      return this.next;
    }

    public void setNext(Node<T> next)
    {
      this.next = next;
    }

    public void setData(T data)
    {
      this.data = data;
    }
  }
  // end of inner generic node class
  // ----------------------------------------------

  private Node<T> head;
  private Node<T> rear;
  private int size;

  public GenericSortedLinkedList()
  {
    head = new Node<>();
    rear = head;
    size = 0;
  }

  // initialize new node
  private void init(Node<T> head, T data)
  {
    head.setNext(null);
    head.setData(data);
  }

  // insert data to the list in increasing order (Using compareTo)
  public void insert(T data)
  {
    // initialize the node to insert.
    Node<T> toInsert = new Node<>();
    init(toInsert, data);
    // node to traverse the list
    Node<T> temp = head;
    // making sure head is not null
    if (temp != null)
    {
      // if the list is empty, inserting the new node.
      if (head.getNext() == null)
      {
        head.setNext(toInsert);
        rear = head.getNext();
      }
      // else searching the correct location for the new node
      else
      {
        // traversing the list until finding the spot where new node is
        // less than the node in the list, or getting to the end of the list
        while (temp.getNext() != null && (temp.getData() == null || temp.getNext().getData().compareTo(data) < 0))
          temp = temp.getNext();

        // inserting the new node to the list
        Node<T> swap = temp.getNext();
        // if we insert to the end of the list
        if (swap == null)
          rear = toInsert;
        temp.setNext(toInsert);
        temp.getNext().setNext(swap);
      }
      size++;
    }
  }

  // deletes rear, returns true after deletion.
  public void deleteRear()
  {
    // node to traverse the list
    Node<T> temp = head;
    // if list isn't empty
    if (size != 0)
    {
      // traverse to the end of the list
      while (temp.getNext() != rear)
        temp = temp.getNext();

      // deletes the old rear and sets the new rear.
      temp.setNext(null);
      rear = temp;
      size--;
    }
  }

  public void deleteFront()
  {
    // if list is not empty
    if (size != 0)
    {
      Node<T> temp = head.getNext();
      head.setNext(temp.getNext());
      temp.setNext(null);
      size--;
    }
  }

  // deletes specific value from the list
  // if deletion completed returns true, else returns false
  public boolean deleteData(T data)
  {
    // node to traverse the List
    Node<T> temp = head;

      // if list is empty
      if (head.getNext() == null)
        return false;

      // if we need to delte the first element in the list.
      if (head.getNext().getData().compareTo(data) == 0)
      {
        deleteFront();
        return true;
      }
      // if we need to delete the last element in the list.
      if (rear.getData().compareTo(data) == 0)
      {
        deleteRear();
        return true;
      }
      // finding the node we need to delete. to make things faster, because the list is sorted,
      // instead of looking for data that eqauls to the data we want to delete (this way if the data is not
      // in the list we will go through the whole lost), as soon as we find a node that contains data that is greater than the
      // we want to delete, we stop the traversal. this way most of the time we won't go through the whole list.
      while (temp.getNext() != null && temp.getNext().getData().compareTo(data) < 0)
        temp = temp.getNext();
      // if the node we found isn't equal to the node we want to delete
      // then the value isn't on the list and we can return false.
      if (temp.getNext() == null || temp.getNext().getData().compareTo(data) != 0)
        return false;

      // we only get here if we found the node to delete.
      Node<T> toDelete = temp.getNext();
      temp.setNext(toDelete.getNext());
      toDelete.setNext(null);
      size--;
      return true;
  }
  // returns true if list contains the value given by the user
  public boolean contains(T data)
  {
    // if list is empty returns false
    if (size == 0)
      return false;

    // node to traverse the List
    Node<T> temp = head;
    // looking for the value
    while (temp.getNext() != null && temp.getNext().getData().compareTo(data) < 0)
      temp = temp.getNext();
    // if value was found return true
    if (temp.getNext() != null && temp.getNext().getData().compareTo(data) == 0)
      return true;
    // we only get here if value was not found
    return false;
  }

  public Node<T> getRear()
  {
    return rear;
  }

  public Node<T> getFront()
  {
    return head.getNext();
  }

  public int size()
  {
    return size;
  }


  public void printList()
  {
    if (head.getNext() == null)
    {
      System.out.println("List is Empty.");
    }
    else
    {
      Node<T> t = head;
      while (t != null)
      {
        if (t.getData() != null)
          System.out.println(t.getData());
        t = t.getNext();
      }
    }
  }

}
