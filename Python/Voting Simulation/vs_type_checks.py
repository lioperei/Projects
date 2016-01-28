''' This module should be used to test the parameter and return types of your
functions. Before submitting your assignment, run this type-checker on your
voting_systems.py. If errors occur when you run this module, fix them
before you submit your assignment.

When this module runs, if nothing is displayed and no errors occur, then the
type checks passed. This means that your functions take the proper number
of arguments and return the proper type of value.
This does not say anything else about your code's correctness.
Be sure to test your code before submitting.'''

import voting_systems as vs


def all_ints(lst):
    ''' (list) -> bool

    Return True iff all elements in lst are ints.

    >>> all_ints([1, 2])
    True
    '''
    ok = True
    for s in lst:
        if not isinstance(s, int):
            ok = False
    return ok


def is_tuple_of_str_list(t, func_name):
    ''' (object, str) -> NoneType

    Check whether t is a 2-element tuple where the first element is a string
    and the second element is a 4-element list of ints.
    '''
    assert (isinstance(t, tuple) and len(t) == 2 and isinstance(t[0], str) \
           and isinstance(t[1], list) and len(t[1]) == 4 \
           and all_ints(t[1])
           ), \
           '{0} should return tuple of (str, list of four ints), but returned \
           {1}.'.format(func_name, t)


def is_tuple_of_str_None(t, func_name):
    '''(object, str) -> NoneType

    Check whether t is a 2-element tuple where the first element is a string
    and the second element is None.
    '''
    assert isinstance(t, tuple) and len(t) == 2 and isinstance(t[0], str) \
           and t[1] is None, \
           '{0} should return tuple of (str, NoneType), but returned \
           {1}.'.format(func_name, t)


pre_constants = [vs.NDP_INDEX, vs.GREEN_INDEX, vs.LIBERAL_INDEX, vs.CPC_INDEX,
    vs.PARTY_INDICES, vs.NAME_TO_INDEX, vs.INDEX_TO_NAME]
