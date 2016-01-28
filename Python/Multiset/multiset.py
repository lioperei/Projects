"""An implementation of the Multiset ADT using a Skip List.

Authors: c2pereis
"""


from skiplist import *


class MultiSet:
    ''' 
    A collection of arbitrary elements where elements can occur multiple times
    '''
    
    def __init__(self):
        '''(Multiset) -> NoneType
        Initialize a multiset.
        '''
        self._items = SkipList()
        
    def __contains__(self, item):
        '''(Multiset, object) -> bool
        Return True iff element item belongs to multiset
        '''
        for items in self._items:
            if items == item:
                return True
        return False
    
    def count(self, item):
        '''(Multiset, object) -> int
        Return the number of occurrences of element item in multiset
        '''
        i = 0
        for items in self._items:
            if items == item:
                i += 1
        return i
    
    def insert(self, item):
        '''(Multiset, object) -> NoneType
        Add element item to multiset
        '''
        self._items.insert(item)
    
    def remove(self, item): 
        '''(Multiset, object) -> NoneType
        Remove one occurrence of element item from multiset, do nothing if
        self does not contain element item
        '''
        self._items.remove(item)
    
    def clear(self):
        '''(Multiset) -> NoneType
        Remove all elements from multiset.
        '''
        for items in self._items:
            self.remove(items)
    
    def __len__(self):
        '''(Multiset) -> int
        Return the number of elements in multiset, counting each occurrence
        of an element separately
        '''
        i = 0
        for items in self._items:
            i += 1
        return i
        # return len(self._items)
    
    def __repr__(self):
        '''(Multiset) -> str
        Return a string representation of multiset of the form
        MultiSet([e1, e2, ..., en]) where each ei is the representation of one
        occurrence of one element in multiset; the order of the elements
        does not matter.
        '''
        r = ''
        for item in self._items:
            r += '{0}, '.format(str(item))
        l = r.rstrip(', ')
        return 'MultiSet([' + l + '])'

    def __eq__(self, other):
        '''(Multiset, Multiset) -> bool
        Return True iff multiset self contains exactly the same elements as
        multiset other. 
        '''
        for items in self._items:
            if self.count(items) != other.count(items):
                return False
        for items in other._items:
            if self.count(items) != other.count(items):
                return False
        return True
    
    def __le__(self, other):
        '''(Multiset, Multiset) -> bool
        Return True iff every element in multiset self also belongs to
        multiset other.
        '''
        for items in self._items:
            if self.count(items) > other.count(items):
                return False
        return True
    
    def __sub__(self, other):
        '''(Multiset, Multiset) -> Multiset
        Return a new multiset that contains every element that belongs to
        multiset self but not to multiset other
        '''
        third = MultiSet()
        for items in self._items:
            if not items in other:
                third.insert(items)
            else:
                other.remove(items)
        return third
    
    def __isub__(self, other):
        '''(Multiset, Multiset) -> NoneType
        Update multiset self so that every element in multiset other is
        removed from self
        '''
        for items in self._items:
            if items in other:
                other.remove(items)
                self.remove(items)
        return self
    
    def __add__(self, other):
        '''(Multiset, Multiset) -> Multiset
        Return a new multiset that contains every element that belongs to
        either multiset self or multiset other
        '''
        third = MultiSet()
##        for items in self._items:
##            third.insert(items)
##        for items in other._items:
##            if not third.contains(items):
##                third.insert(items)
        for items in self._items():
            if self.count(items) >= other.count(items):
                max_ocur = self.count(items)
            elif self.count(items) < other.count(items):
                max_ocur = other.count(items)
            for i in range(max_ocur):
                third.insert(items)
        return third
    
    def __iadd__(self, other):
        '''(Multiset, Multiset) -> NoneType
        Update multiset self so that every element in multiset other is
        added to self
        '''
        for items in other._items:
            self.insert(items)        
        return self
    
    def __and__(self, other):
        '''(Multiset, Multiset) -> Multiset
        Return a new multiset that contains every element that belongs to
        both multiset self and multiset other
        '''
        third = MultiSet()
        for items in self._items:
            if items in other:
                third.insert(items)
                other.remove(items)
        return third
    
    def __iand__(self, other):
        '''(Multiset, Multiset) -> NoneType
        Update multiset self so that it only contains elements that are
        common to multiset other and self
        '''
        for items in self._items:
            if not items in other:
                self.remove(items)
            else:
                other.remove(items)
        return self
    
    def isdisjoint(self, other): 
        '''(Multiset, Multiset) -> bool
        Return True iff multiset self has no element in common with
        multiset other
        '''
        for items in self._items:
            if other.count(items) > 0:
                return False
        return True
    
