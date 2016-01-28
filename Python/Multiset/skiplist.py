"""A basic implementation of the skip list data structure.

Author: Francois Pitt, February 2013.
"""

### CHEAT! This module actually implements just a regular linked list, but one
### that keeps its elements sorted. When the list is small, the difference in
### performance won't be noticeable anyway... And this is much simpler to
### implement! :)
### Among other things, you will fix this in Part II of the project.


class SkipList(object):
    """A skip list stores a sequence of elements in sorted order, using a
    randomized linked structure that provides expected worst-case performance
    O(log n) for every operation.
    """

    def __init__(self, container=[]):
        """(SkipList) -> NoneType
        Initialize this skip list to contain every element within container
        (default: create an empty skip list).
        """
        # First, initialize attributes for an empty skip list.
        # To simplify some of the code in other methods, we use a "header": an
        # extra node at the front of the skip list that does not store any
        # skip list element. This eliminates the need for special cases when
        # inserting or removing values at the front of the linked list.
        self.head = _SkipNode(None)
        self.size = 0
        # Next, add every element from container.
        for item in container:
            self.insert(item)

    def __len__(self):
        """(SkipList) -> int
        Return the number of items in this skip list.
        """
        return self.size

    def __contains__(self, item):
        """(SkipList, object) -> bool
        Return True iff this skip list contains item.
        """
        pred = self._predecessor(item)  # see below
        return pred.link and pred.link.data == item

    def insert(self, item):
        """(SkipList, object) -> NoneType
        Insert item at the appropriate position in this skip list.
        """
        pred = self._predecessor(item)  # see below
        pred.link = _SkipNode(item, pred.link)
        self.size += 1

    def remove(self, item):
        """(SkipList, object) -> NoneType
        Remove one occurrence of item from this skip list. Has no effect if
        item does not belong to the list.
        """
        pred = self._predecessor(item)  # see below
        if pred.link and pred.link.data == item:
            pred.link = pred.link.link
            self.size -= 1

    def __repr__(self):
        """(SkipList) -> str
        Return a string representation of this skip list, showing its linked
        structure, for debugging purposes.
        """
        r = ". -> "
        n = self.head.link
        while n:
            r += repr(n.data) + " -> "
            n = n.link
        return r

    def __iter__(self):
        """(SkipList) -> _SkipIter
        Return an iterator object over this skip list.
        """
        return _SkipIter(self.head)

    def _predecessor(self, item):
        # Helper method used in __contains__, insert, and remove.
        """(SkipList, object) -> _SkipNode
        Return a node p in this skip list such that:
         .  either p == self.head or p.data < item;
         .  either p.link == None, or p.link.data >= item.
        """
        p = self.head
        while p.link and p.link.data < item:
            p = p.link
        return p

    ### You may add methods here to implement additional functionality, as
    ### required for MultiSet.


class _SkipNode(object):  # "private" class because name starts with _
    """A node in the skip list.
    """


    def __init__(self, data, link=None):
        """(_SkipNode, object, _SkipNode) -> NoneType
        Initialize this node to store data and have next node link.
        """
        self.data = data
        self.link = link


class _SkipIter(object):  # "private" class because name starts with _
    """An iterator (allowing the use of for-loops) for skip lists.
    """

    def __init__(self, start):
        """(_SkipIter, _SkipNode) -> NoneType
        Initialize this iterator with node start, a header node that does not
        store an actual skip list element.
        """
        self.current = start

    def __iter__(self):
        """(_SkipIter) -> _SkipIter
        Return this iterator, as required for iterator objects.
        """
        return self

    def __next__(self):
        """(_SkipIter) -> object
        Return the next item for this iterator, if there is one. Raises
        StopIteration if this is none.
        """
        if not self.current.link:
            raise StopIteration()
        self.current = self.current.link
        return self.current.data

    ### When we write code like this, where 'skip' is a variable referring to
    ### some skip list object:
    ###
    ###     for x in skip:
    ###         ...
    ###
    ### Python creates an iterator object to loop over the elements in 'skip'.
