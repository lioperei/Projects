
from skiplist import *

class MultiSet():

    def __init__(self):
        self.list = SkipList()

    def __contains__(self, elem):
        return elem in self.list
            
    def count(self, elem):
        occur = 0
        for item in self.list:
            if item == elem:
                occur += 1
        return occur

    def insert(self, elem):
        self.list.insert(elem)

    def remove(self, elem):
        self.list.remove(elem)

    def clear():
        for item in self.list:
            self.list.remove(item)

    def __len__(self):
        return self.list.size

    def __repr__(self):
        rep = ''
        for item in self.list:
            rep +=  str(item) + ','
        return 'Multiset([' + rep.rstrip(',') + '])'

    def __eq__(self, other):
        for item in self.list:
            if not item in other.list:
                return False
            if self.count(item) != other.count(item):
                return False
        return True

    def __le__(self, other):
        for item in self.list:
            if item not in other.list:
                return False
        return True

    def __sub__(self, other):
        s3 = MultiSet()
        for item in self.list:
            if item not in other.list:
                s3.insert(item)
        return s3

    def __add__(self, other):
        s3 = MultiSet()
        for items in self.list:
            if self.count(items) >= other.count(items):
                max_ocur = self.count(items)
            elif self.count(items) < other.count(items):
                max_ocur = other.count(items)
            if items not in s3:
                for i in range(max_ocur):
                    s3.insert(items)
        return s3
    
if __name__ == '__main__':
    s = MultiSet()
    t = MultiSet()
    for i in range(6):
        s.insert(i)
    for i in range(9):
        t.insert(i)
        t.insert(i)

    

    
            

    
