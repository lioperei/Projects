"""A a test of the implementation of the Multiset ADT using a Skip List.
The test suite of the class Multiset using unittest and TestCases

Authors: c2pereis
"""

import unittest
from multiset import *


class TestMultiSetInit(unittest.TestCase):
    '''
    Test cases for init method in class MultiSet
    '''
    
    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()
        self.s1 = SkipList()

    def tearDown(self):
        '''
        Reset to intial conditions.
        '''
        self.u1.clear()
    
    def test_in_1(self):
        ''' 
        Test MuliSet._items is a SkipList using the repr method.
        Also check that the Skiplist used has the right attributes.
        '''
        self.assertEqual(repr(self.u1._items), repr(self.s1),
                         "MultiSet does not use SkipList")
        self.assertEqual(self.u1._items.size, self.s1.size,
                         "MultiSet does not use SkipList")
        self.assertEqual(self.u1._items.head.link, self.s1.head.link,
                         "MultiSet does not use SkipList")


class TestMultiSetCount(unittest.TestCase):
    '''
    Test cases for count method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()

    def tearDown(self):
        '''
        Reset to intial conditions.
        '''
        self.m1.clear()
        
    def test_count_1(self):
        '''
        Test a element's occurences.
        '''
        self.m1.insert(1)
        self.assertEqual(self.m1.count(1), 1)
    
    def test_count_2(self):
        '''
        Test the occurence of a non-exist element.
        '''
        self.assertEqual(self.m1.count(1), 0)
        
    def test_count_3(self):
        '''
        Test the occurence of a mutliple existant element of the MultiSet.
        '''
        self.m1.insert(1)
        self.m1.insert(2)
        self.m1.insert(1)
        self.assertEqual(self.m1.count(1), 2)

        
class TestMultiSetInsert(unittest.TestCase):
    '''
    Test cases for insert method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()

    def tearDown(self):
        '''
        Reset to intial conditions
        '''
        self.m1.clear()
        
    def test_insert_0(self):
        '''
        Test insert function does insert a element to a multiset
        '''
        self.m1.insert(3)
        self.assertTrue(3 in self.m1)
        self.m1.insert(5)
        self.assertTrue(5 in self.m1)
        self.assertFalse(4 in self.m1)


class TestMultiSetRemove(unittest.TestCase):
    '''
    Test cases for remove method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()

    def tearDown(self):
        '''
        Reset to intial conditions
        '''
        self.m1.clear()
        
    def test_remove_1(self):
        '''
        Test remove function will remove element once
        '''
        self.m1.insert(2)
        self.m1.insert(2)
        self.m1.remove(2)
        self.assertEqual(self.m1.count(2), 1)
        self.m1.insert(1)
        self.m1.remove(1)
        self.assertEqual(self.m1.count(1), 0)
        
    def test_remove_2(self):
        '''
        Test to remove a non-exist element
        '''
        self.m1.remove(1)
        self.assertEqual(self.m1.count(1), 0)


class TestMultiSetClear(unittest.TestCase):
    
    '''
    Test cases for clear method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()
        self.m2 = MultiSet()
        self.m3 = MultiSet()
        for i in range(5):
            self.m3.insert(i)
    
    def test_clear_1(self):
        '''
        Test clear function does clear that multiset
        '''
        self.m3.clear()
        self.assertTrue(self.m1 == self.m3)

    def test_clear_2(self):
        '''
        Test clear function on an already cleared MultiSet
        '''
        self.m2.clear()
        self.assertTrue(self.m1 == self.m2)


class TestMultiSetLen(unittest.TestCase):
    
    '''
    Test cases for Len method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()

    def tearDown(self):
        '''
        Reset to intial conditions
        '''
        self.m1.clear()

    def test_len_1(self):
        '''
        Test the length of a multiset with no elements
        '''        
        self.assertEqual(len(self.m1), 0)  
    
    def test_len_2(self):
        '''
        Test the length of a multiset with several elements
        '''
        i = 6
        for x in range(i):
            self.m1.insert(x)
        self.assertEqual(len(self.m1), i)


class TestMultiSetRepr(unittest.TestCase):
    
    '''
    Test cases for __repr__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()

    def test_repr_1(self):
        '''
        Test the string representation of a multiset with several elements
        '''
        self.m1.insert(1)
        self.m1.insert(1)
        self.m1.insert(2)
        self.assertEqual(str(self.m1), 'MultiSet([1, 1, 2])')
    
    def test_repr_2(self):
        '''
        Test the string representation of a multiset with no elements
        '''        
        self.assertEqual(str(self.m1), 'MultiSet([])')

        
class TestMultiSetEq(unittest.TestCase):
    
    '''
    Test cases for __eq__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()   
        self.m2 = MultiSet()
        
    def test_eq_1(self):
        '''
        Test two different multisets whether have same elements
        '''
        self.m1.insert(1)
        self.assertFalse(self.m1 == self.m2)
    
    def test_eq_2(self):
        '''
        Test two same multisets whether have same elements
        '''                
        self.assertTrue(self.m1 == self.m2)


class TestMultiSetLe(unittest.TestCase):
    
    '''
    Test cases for __le__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.m1 = MultiSet()   
        self.m2 = MultiSet()
    
    def test_le_1(self):
        '''
        Test two different length multisets whether one belongs to another
        '''
        self.m1.insert(1)
        self.m1.insert(2)
        self.m2.insert(1)
        self.assertTrue(self.m2 <= self.m1)
        
    def test_le_2(self):
        '''
        Test two same multisets whether one belongs to another
        '''        
        self.assertTrue(self.m2 <= self.m1)    
    
    def test_le_3(self):
        '''
        Test whether longer multisets belongs to shorter multisets
        '''
        for x in range(6):
            self.m1.insert(x)
        for x in range(3):
            self.m2.insert(x)
        self.assertFalse(self.m1 <= self.m2)


class TestMultiSetSub(unittest.TestCase):
    
    '''
    Test cases for __sub__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()   
        self.u2 = MultiSet()
    
    def test_sub_1(self):
        '''
        Test substract function with one empty multiset
        '''
        self.u1.insert(1)
        self.assertEqual(self.u1 - self.u2, self.u1)
        
    def test_sub_2(self):
        '''
        Test substract function with two multisets that are subsets of another
        '''
        for i in range(5):
            self.u1.insert(i)
            self.u2.insert(i)
        self.assertEqual(str(self.u1 - self.u2), 'MultiSet([])')


class TestMultiSetIsub(unittest.TestCase):
    
    '''
    Test cases for __isub__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()
        self.u1.insert(1)
        self.u1.insert(1)
        self.u1.insert(2)
        self.u2 = MultiSet()        
        
    def test_isub_1(self):
        '''
        Test isubstract function with one empty multiset
        '''
        self.u1 -= self.u2
        self.assertEqual(str(self.u1), 'MultiSet([1, 1, 2])')
        
    def test_isub_2(self):
        '''
        Test substract function with two different multisets
        '''
        self.u2.insert(1)   
        self.u2.insert(5) 
        self.u1 -= self.u2
        self.assertEqual(str(self.u1), 'MultiSet([1, 2])')


class TestMultiSetAdd(unittest.TestCase):
    
    '''
    Test cases for __add__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()
        self.u2 = MultiSet()
        for x in [1, 1, 2]:
            self.u1.insert(x)

    def test_add_1(self):
        '''
        Test addition function with one empty multiset
        '''
        self.assertEqual(str(self.u1 + self.u2), 'MultiSet([1, 1, 2])')
    
    def test_add_2(self):
        '''
        Test addotion function with two different multisets
        '''        
        self.u2.insert(1)   
        self.u2.insert(5) 
        self.assertEqual(str(self.u1 + self.u2), 'MultiSet([1, 1, 1, 2, 5])')


class TestMultiSetIadd(unittest.TestCase):
    
    '''
    Test cases for __iadd__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()
        self.u2 = MultiSet()
        for x in [1, 1, 2]:
            self.u1.insert(x)        
    
    def test_iadd_1(self):
        '''
        Test iaddition function with one empty multiset
        '''        
        self.u1 += self.u2
        self.assertEqual(str(self.u1), 'MultiSet([1, 1, 2])')
        
    def test_iadd_2(self):
        '''
        Test iaddition function with two different multisets
        '''        
        self.u2.insert(1)   
        self.u2.insert(5) 
        self.u1 += self.u2
        self.assertEqual(str(self.u1), 'MultiSet([1, 1, 1, 2, 5])')


class TestMultiSetAnd(unittest.TestCase):
    
    '''
    Test cases for __and__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()
        self.u2 = MultiSet()
        for x in [1, 1, 2]:
            self.u1.insert(x)        
    
    def test_and_1(self):
        '''
        Test and function with one empty multiset
        '''                
        self.assertEqual(str(self.u1 & self.u2), 'MultiSet([])')
    
    def test_and_2(self):
        '''
        Test and function with two different multisets
        '''
        self.u2.insert(1)   
        self.u2.insert(1) 
        self.u2.insert(5) 
        self.assertEqual(str(self.u1 & self.u2), 'MultiSet([1, 1])')


class TestMultiSetIand(unittest.TestCase):
    
    '''
    Test cases for __iadd__ method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()
        self.u2 = MultiSet()
        for x in [1, 1, 2]:
            self.u1.insert(x)                
        
    def test_iand_1(self):
        '''
        Test iand function with one empty multiset
        '''                
        self.u1 &= self.u2
        self.assertEqual(str(self.u1), 'MultiSet([])')
        
    def test_iand_2(self):
        '''
        Test iand function with two different multisets
        '''        
        self.u2.insert(1)
        self.u2.insert(1)
        self.u2.insert(5) 
        self.u1 &= self.u2
        self.assertEqual(str(self.u1), 'MultiSet([1, 1])')


class TestMultiSetIsDisjoint(unittest.TestCase):
    
    '''
    Test cases for isdisjoint method in class MultiSet
    '''

    def setUp(self):
        '''
        Set up initial conditions.
        '''
        self.u1 = MultiSet()
        self.u2 = MultiSet()
        for x in [1, 1, 2]:
            self.u1.insert(x)
            
    def test_isdisjoint_1(self):
        '''
        Test isdisjoint function with one empty multiset
        '''                
        self.assertTrue(self.u1.isdisjoint(self.u2))
        
    def test_isdisjoint_2(self):
        '''
        Test isdisjoint function with two different multisets
        '''                
        self.u2.insert(1)
        self.u2.insert(5)        
        self.assertFalse(self.u1.isdisjoint(self.u2))    
    
    def test_isdisjoint_3(self):
        '''
        Test isdisjoint function with two empty multisets
        '''                
        self.u1.clear()
        self.assertTrue(self.u1.isdisjoint(self.u2))    

        
unittest.main(exit=False)
