import unittest
import voting_systems as vs

class TestVotingIrv(unittest.TestCase):

    def test_one_key_one_value_ndp_win(self):

        param = {('NDP','CPC','GREEN','LIBERAL'):1}
        param_expected = {('NDP','CPC','GREEN','LIBERAL'):1}

        result = vs.voting_irv(param)
        result_expected = ('NDP', None)

        self.assertEqual(param, param_expected,
                         "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
            "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))

    def test_multiple_keys_multiple_values_ndp_win(self):

        param = {('NDP','GREEN','CPC','LIBERAL'):3,
                 ('LIBERAL','NDP','GREEN','CPC'):2}
        param_expected = {('NDP','GREEN','CPC','LIBERAL'):3,
                         ('LIBERAL','NDP','GREEN','CPC'):2}

        result = vs.voting_irv(param)
        result_expected = ('NDP', None)

        self.assertEqual(param, param_expected,
                         "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
                "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))

    def test_one_key_one_value_green_win(self):

        param = {('GREEN','NDP','CPC','LIBERAL'):1}
        param_expected = {('GREEN','NDP','CPC','LIBERAL'):1}

        result = vs.voting_irv(param)
        result_expected = ('GREEN', None)

        self.assertEqual(param, param_expected,
                         "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
                "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))
        
    def test_multiple_keys_multiple_values_green_win(self):

        param = {('GREEN','NDP','CPC','LIBERAL'):5,
                 ('LIBERAL','CPC','GREEN','NDP'):2,
                 ('CPC','GREEN','NDP','LIBERAL'):3}
        param_expected = {('GREEN','NDP','CPC','LIBERAL'):5,
                     ('LIBERAL','CPC','GREEN','NDP'):2,
                     ('CPC','GREEN','NDP','LIBERAL'):3}

        result = vs.voting_irv(param)
        result_expected = ('GREEN', None)

        self.assertEqual(param, param_expected,
                         "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
                "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))

    
    def test_one_key_one_value_liberal_win(self):

        param = {('LIBERAL','NDP','CPC','GREEN'):1}
        param_expected = {('LIBERAL','NDP','CPC','GREEN'):1}

        result = vs.voting_irv(param)
        result_expected = ('LIBERAL', None)

        self.assertEqual(param, param_expected,
                         "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
                "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))

        
    def test_multiple_keys_multiple_values_liberal_win(self):

        param = {('LIBERAL','NDP','CPC','GREEN'):4,
                 ('CPC','GREEN','LIBERAL','NDP'):2}
        param_expected = {('LIBERAL','NDP','CPC','GREEN'):4,
                         ('CPC','GREEN','LIBERAL','NDP'):2}

        result = vs.voting_irv(param)
        result_expected = ('LIBERAL', None)

        self.assertEqual(param, param_expected,
                         "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
                "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))


    def test_one_key_one_value_cpc_win(self):
        param = {('CPC','GREEN','NDP','LIBERAL'):1}
        param_expected = {('CPC','GREEN','NDP','LIBERAL'):1}

        result = vs.voting_irv(param)
        result_expected = ('CPC', None)

        self.assertEqual(param, param_expected,
                         "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
                "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))
        
    def test_multiple_keys_multiple_values_cpc_win(self):

        param = {('CPC','GREEN','NDP','LIBERAL'):3,
                 ('LIBERAL','CPC','GREEN','NDP'):2}
        param_expected = {('CPC','GREEN','NDP','LIBERAL'):3,
                         ('LIBERAL','CPC','GREEN','NDP'):2}

        result = vs.voting_irv(param)
        result_expected = ('CPC', None)

        self.assertEqual(param, param_expected,
                "Dict parameter should be {0}, but is {1}".format(
                            param_expected, param))

        self.assertEqual(result, result_expected,
                "Voting Irv should return {0}, but returned {1}".format(
                            result_expected, result))

   
unittest.main(exit=False)
