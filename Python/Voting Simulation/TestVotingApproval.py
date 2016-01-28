import unittest
import voting_systems as vs

class TestVotingApproval(unittest.TestCase):

    def test_one_inner_list_ndp_win(self):
        param = [['YES','NO','NO','NO']]
        expected_param = [['YES','NO','NO','NO']]

        result = vs.voting_approval(param)
        expected_result = 'NDP',[1,0,0,0]

        self.assertEqual(
            result, expected_result,
            "Voting Approval should return {0}, but returned {1}.".format(
                expected_result,result))

        self.assertEqual(
            param, expected_param,
            "List parameter should be {0}, but was {1}.".format(
                expected_param,param))

        
    def test_two_inner_list_ndp_win(self):
        param = [['YES','NO','NO','NO'],['YES','NO','YES','YES']]
        expected_param = [['YES','NO','NO','NO'],['YES','NO','YES','YES']]

        result = vs.voting_approval(param)
        expected_result = 'NDP',[2,0,1,1]

        self.assertEqual(
            result, expected_result,
            "Voting Approval should return {0}, but returned {1}.".format(
                expected_result,result))

        self.assertEqual(
            param, expected_param,
            "List parameter should be {0}, but was {1}.".format(
                expected_param,param))

    def test_three_inner_list_ndp_win(self):
        param = [['YES','NO','NO','NO'],['YES','NO','YES','YES'],
                 ['YES','NO','YES','YES']]
        expected_param = [['YES','NO','NO','NO'],['YES','NO','YES','YES'],
                          ['YES','NO','YES','YES']]

        result = vs.voting_approval(param)
        expected_result = 'NDP',[3,0,2,2]

        self.assertEqual(
            result, expected_result,
            "Voting Approval should return {0}, but returned {1}.".format(
                expected_result,result))

        self.assertEqual(
            param, expected_param,
            "List parameter should be {0}, but was {1}.".format(
                expected_param,param))

    def test_mulitple_inner_list_green_win(self):
        param = [['YES','YES','NO','NO'],['NO','YES','YES','YES']]
        expected_param = [['YES','YES','NO','NO'],['NO','YES','YES','YES']]

        result = vs.voting_approval(param)
        expected_result = 'GREEN',[1,2,1,1]

        self.assertEqual(
            result, expected_result,
            "Voting Approval should return {0}, but returned {1}.".format(
                expected_result,result))

        self.assertEqual(
            param, expected_param,
            "List parameter should be {0}, but was {1}.".format(
                expected_param,param))

    def test_multiple_inner_list_liberal_win(self):
        param = [['YES','NO','YES','NO'],['NO','YES','YES','YES']]
        expected_param = [['YES','NO','YES','NO'],['NO','YES','YES','YES']]

        result = vs.voting_approval(param)
        expected_result = 'LIBERAL',[1,1,2,1]

        self.assertEqual(
            result, expected_result,
            "Voting Approval should return {0}, but returned {1}.".format(
                expected_result,result))

        self.assertEqual(
            param, expected_param,
            "List parameter should be {0}, but was {1}.".format(
                expected_param,param))

    def test_multiple_inner_list_cpc_win(self):
        param = [['YES','NO','NO','YES'],['NO','YES','YES','YES']]
        expected_param = [['YES','NO','NO','YES'],['NO','YES','YES','YES']]

        result = vs.voting_approval(param)
        expected_result = 'CPC',[1,1,1,2]

        self.assertEqual(
            result, expected_result,
            "Voting Approval should return {0}, but returned {1}.".format(
                expected_result,result))

        self.assertEqual(
            param, expected_param,
            "List parameter should be {0}, but was {1}.".format(
                expected_param,param))

unittest.main(exit=False)
