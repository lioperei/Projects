'''
A single-candidate ballot: a voter selects exactly one candidate from a list.
  Example: 'LIBERAL'

An approval ballot: a voter indicates approval or disapproval for each
candidate.
  Example: ['YES', 'YES', 'NO', 'YES']

A range ballot: a voter assigns each candidate a number of points between
1 and 5 inclusive, where more points means more preferred.
  Example: [5, 4, 1, 4]

A rank ballot: a voter puts the candidates in ranked order of preference
from 1st to 4th choice.
  Example: ['LIBERAL', 'GREEN', 'CPC', 'NDP']
'''


#  The indices where each party's data appears in a 4-element list.
NDP_INDEX = 0
GREEN_INDEX = 1
LIBERAL_INDEX = 2
CPC_INDEX = 3

# A list of the indices where each party's data appears in a 4-element list.
PARTY_INDICES = [NDP_INDEX, GREEN_INDEX, LIBERAL_INDEX, CPC_INDEX]

# A dict where each key is a party name and each value is that party's index.
NAME_TO_INDEX = {
  'NDP': NDP_INDEX,
  'GREEN': GREEN_INDEX,
  'LIBERAL': LIBERAL_INDEX,
  'CPC': CPC_INDEX
}

# A dict where each key is a party's index and each value is that party's name.
INDEX_TO_NAME = {
  NDP_INDEX: 'NDP',
  GREEN_INDEX: 'GREEN',
  LIBERAL_INDEX: 'LIBERAL',
  CPC_INDEX: 'CPC'
}


def voting_plurality(votes):
    ''' (list of str) -> tuple of (str, list of int)

    Votes is a list of single-candidate ballots for a single riding. Based on
    votes, return a tuple where the first element is the name of the party
    winning the seat and the second is a list with the total votes for each
    party in the order specified in PARTY_INDICES.

    >>> voting_plurality(['GREEN', 'GREEN', 'NDP', 'GREEN', 'CPC'])
    ('GREEN', [1, 3, 0, 1])
    '''

    vote_tally = [0,0,0,0]
    
    for selection in votes:
        
        vote_tally[NAME_TO_INDEX[selection]] += 1
        
    return (INDEX_TO_NAME[vote_tally.index(max(vote_tally))], vote_tally)

def voting_approval(riding_ballots):
    ''' (list of list of str) -> tuple of (str, list of int)

    Riding ballots is a list of approval votes for a single riding. Return a
    tuple of the winning party and a list of the number of votes for each party
    specified by PARTY_INDICES.

    >>> voting_approval([['YES','NO','NO','YES'],['NO','NO','YES','YES']])
    ('CPC',[1,0,1,2])
    '''

    vote_tally = [0,0,0,0]
    
    for ballot in riding_ballots:
        
        for vote in range(len(ballot)):
            
            if ballot[vote] == 'YES':
                
                vote_tally[vote] += 1

    return (INDEX_TO_NAME[vote_tally.index(max(vote_tally))], vote_tally)

def voting_range(range_ballots):
    ''' (list of list of int) -> tuple of (str, list of int)

    Range ballots represents the votes of a single riding specified by
    PARTY_INDICES. Return a tuple of the winning party and a list of the
    accumulated votes for each party specified by PARTY_INDICES.

    >>> voting_range([[5,3,2,1],[3,4,5,1]])
    ('NDP',[8,7,7,2])
    '''

    vote_tally = [0,0,0,0]
    
    for ballot in range_ballots:
        
        for vote in range(len(ballot)):
            
            vote_tally[vote] += int(ballot[vote])

    return (INDEX_TO_NAME[vote_tally.index(max(vote_tally))], vote_tally)

def voting_borda(rank_ballots):
    ''' (list of list of str) -> tuple of (str, list of int)

    Rank ballots represents the ranked votes of a single riding. Return a
    tuple of the the name of the winning party and a list of the total
    points of each party specified by PARTY_INDICES.

    >>> voting_borda([['LIBERAL','CPC','GREEN','NDP'],
    ['NDP','LIBERAL','CPC','GREEN']])
    ('LIBERAL',[3,1,5,3])
    '''

    vote_tally = [0,0,0,0]

    for vote in rank_ballots:
        
        for preference in range(len(vote)):
            
            if preference == 0:
                vote_tally[NAME_TO_INDEX[vote[preference]]] += 3
                
            elif preference == 1:
                vote_tally[NAME_TO_INDEX[vote[preference]]] += 2
                
            elif preference == 2:
                vote_tally[NAME_TO_INDEX[vote[preference]]] += 1
    
    return (INDEX_TO_NAME[vote_tally.index(max(vote_tally))], vote_tally)

def voting_irv(riding_votes):
    ''' (dict of {tuple of (str, str, str, str): int}) ->
    tuple of (str, NoneType)

    Riding votes represents a dictionary for a single riding containing keys
    representing four element tuples whos values are common occurences of that
    ballot. Return a tuple consisting of the name of the winning party and
    NoneType.

    >>> voting_irv({('LIBERAL','CPC','GREEN','NDP'):2,
    ('NDP','LIBERAL','CPC','GREEN'):3})
    ('NDP', None)
    '''

    vote_tally = [0, 0, 0, 0]
    winner = ''
    votes = riding_votes.copy()
    # copy of original votes
    updated_votes = {}
    # new dictionary in the event a majority is not reached

    while winner == '':
        # loop does not end till variable winner is changed       
        for (ballot, occurence) in votes.items():
            # tally votes
            vote_tally[NAME_TO_INDEX[ballot[0]]] += occurence

        if ((max(vote_tally) / sum(vote_tally)) * 100) >= 50:
            # if majority occurs
            winner = INDEX_TO_NAME[vote_tally.index(max(vote_tally))]
            

        else:
            # remove party with least amount of votes
            eliminated_party = INDEX_TO_NAME[vote_tally.index(min(vote_tally))]
            
            for (ballot,occurence) in votes.items():
                
                if ballot[0] == eliminated_party:
                    
                    new_ballot = list(ballot)
                    
                    new_ballot.remove(eliminated_party)
                    
                    updated_votes[tuple(new_ballot)] = occurence
                    
        vote_tally = [0, 0, 0, 0]

        votes = updated_votes
        updated_votes = {} # reset
        
    return (winner,None)
                    
                    

 
