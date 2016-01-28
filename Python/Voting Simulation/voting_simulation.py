# Voting simulation code

# The following allows you to call functions from voting_systems using
# vs.function_name (for example, vs.voting_plurality).
import voting_systems as vs

# The filenames of the data files.
# When you submit your code for the final time, make sure
# that the constants are set to the large data files.
# To begin, you should develop and test your code with smaller files!
SINGLE_CHOICE_BALLOT_FILENAME = 'single_choice_ballots_large.txt'
APPROVAL_BALLOT_FILENAME = 'approval_ballots_large.txt'
RANGE_BALLOT_FILENAME = 'range_ballots_large.txt'
RANK_BALLOT_FILENAME = 'rank_ballots_large.txt'

# Helper functions that your code should call.

def prompt_for_riding(prompt, maximum):
    ''' (str, int) -> str

    Prompt the user to enter all or a value between 0 and maximum - 1
    inclusive, using prompt, and return the value entered.
    '''
    
    # Continuously prompt the user to enter a value until they
    # enter a value between 0 and maximum - 1, or 'all'.
    answer = input(prompt)
    while not (answer.isnumeric() and int(answer) in range(maximum))\
          and answer != 'all':
        print('Invalid choice. Enter a riding between 0 and ' \
              '{0}, or all.'.format(maximum - 1))
        answer = input(prompt)

    return answer


def print_list_items(L, delimiter):
    '''(list) -> NoneType

    Print the items of L separated by delimiter.  If len(L) >= 1, terminate
    the line with a newline.
    '''

    print('  ', end='')
    for item in L[:-1]:
        print(item, end=delimiter)

    if len(L) >= 1:
        print(L[-1])


def prompt_from_list(prompt, L):
    ''' (str, list) -> str

    Prompt the user to enter a value using prompt, present the user with
    the options from L, and return the value entered.
    '''

    print(prompt)
    print_list_items(L, ', ')

    # Continuously prompt the user to enter a value until they
    # enter a value that appears in L.
    answer = input()
    while answer not in L:
        print('Invalid choice. You must select from these options: ')
        print_list_items(L, ', ')
        answer = input()

    return answer


def print_country_results(seats):
    ''' (list of int) -> NoneType

    Given a four-element list representing the seats assigned to the parties
    in the order of PARTY_INDICES, print a formatted message.
    '''

    print("\nSeat Assignments for the Country")
    print("================================")
    for party in vs.PARTY_INDICES:
        party_formatted = (vs.INDEX_TO_NAME[party] + ':').ljust(10)
        print("{0}       {1}".format(party_formatted, seats[party]))



def print_riding_results(riding, winning_party, distribution):
    ''' (int, str, list of int) -> NoneType

    Print a formatted message showing that winning_party has won for
    this riding and, if applicable, also print the distribution of the votes
    in the order of PARTY_INDICES.
    '''

    print("\nResults for Riding {0}".format(riding))
    print("=====================")
    print("The seat is won by the {0} candidate.".format(winning_party))

    if distribution:
        print("The distribution of the results is as follows: ")
        for party in vs.PARTY_INDICES:
            party_formatted = (vs.INDEX_TO_NAME[party] + ':').ljust(10)
            print("{0}       {1}".format(party_formatted, distribution[party]))

# Add your code here.

def read_file(file,voting_system):
    ''' (ioTextWrapper, str) -> object

    Read over the file and return a dict of list of object that can be used
    to evaluate the given voting_system. Each key of the dict represents
    a riding with the values corresponding to the votes in each riding.
    '''
    
    title = file.readline() # So the program omits the first line
    current_line = file.readline()
    next_line = file.readline()
    
    current_riding = ''
    riding_vote_list = []
    file_dict = {}
    irv_dict = {}
    
    while current_line != '':# end of the file would have a value of ''
        if 'Riding' in current_line: 
            current_riding = current_line.strip()# Make note of current riding

        else:
            if voting_system == 'IRV':
            # IRV takes a dict of key tuple of str and value int
                if next_line.strip() == '':
                    # this analyzes either an seperater (ie. newline) or
                    # end of file
                    
                    file_dict[current_riding] = irv_dict
                    # adds dict of this ridings vote at current riding to
                    # the main dict
                    
                    current_riding,irv_dict = '' , {}
                    # resets values
                    
                else:
                    if current_line.strip() != '':
                        # evaluates lines that aren't empty
                        
                        line = current_line.split()
                        # makes list of elements in line
                        
                        if tuple([line[0],line[1],line[2],line[3]])\
                        in irv_dict.keys():
                            # IRV takes tuple of str, turns the line to a tuple
                            # checks if this vote has already occured and and
                            # adds to its occurence
                            irv_dict[tuple([line[0],line[1],line[2],line[3]])]\
                            += 1
                            
                        else:
                            # if this vote hasn't occurred it adds it with
                            # occurence 1
                            irv_dict[tuple([line[0],line[1],line[2],line[3]])]\
                            = 1
                            
            elif voting_system == 'Plurality':
                # Plurality takes a list of str
                party = current_line.rstrip()
                # takes note of str in file
                
                if next_line.strip() == '':
                    # this analyzes either an seperater (ie. newline) or
                    # end of file
                    
                    riding_vote_list.append(party)
                    # Appends the vote to the list
                    
                    file_dict[current_riding] = riding_vote_list
                    # Adds vote at the riding to dict
                    
                    current_riding, riding_vote_list = '' , []
                    # reset values of current riding and list of votes
                    # in that riding

                else:
                    if party != '':
                        # Adds the party in line to the list of vote
                        # for the riding
                        riding_vote_list.append(party)
                        
            elif voting_system == 'Approval' or 'Borda' or 'Range':
                # These voting systems take list of list of object

                if next_line.strip() == '':
                    # this analyzes either an seperater (ie. newline) or
                    # end of file
                    
                    riding_vote_list.append(current_line.split())
                    # Creates list of elements this last line as it has not
                    # been added yet
                    
                    file_dict[current_riding] = riding_vote_list
                    # Makes key of current riding equal to the votes
                    # in that riding
                    
                    current_riding, riding_vote_list = '',[]
                    # reset current riding and the list of vote for that riding

                else:
                    if current_line.strip() != '':
                        # Adds a list of str of the line to accumulated riding
                        # list
                        riding_vote_list.append(current_line.split())
                            
        current_line = next_line
        next_line = file.readline()

    file.close() # closes file for reopening
        
    return file_dict

def get_results(riding, vote_results):
    ''' (str,dict) -> 

    Given the riding, return the results of the election for the riding based on
    voting_system. The second element of the returned tuple repesents either a
    list of object or NoneType if voting_system is 'IRV'.
    '''    
    if riding == 'all':
        # Evaluates all ridings

        seat_assignment = [0,0,0,0]
        # counts distibution of riding

        for vote in vote_results.values():
            # Takes the votes of each riding

            winner = function(vote)
            # Evaluates each ridings vote
            
            seat_assignment[vs.NAME_TO_INDEX[winner[0]]] += 1
            # Takes the winner from the tuple returned by finction
            # then takes counts that riding as won by NAME_TO_INDEX in
            # seat assignments
            
        print_country_results(seat_assignment)
        # prints the results of all
            
    else:
       results = function(vote_results['Riding {0}'.format(riding)])
       # evalutes just the defined riding
       
       print_riding_results(riding,results[0],results[1])
       # prints the results of the riding 

name_to_file_to_function = {'Plurality':[SINGLE_CHOICE_BALLOT_FILENAME,
vs.voting_plurality],'Approval':[APPROVAL_BALLOT_FILENAME,vs.voting_approval],
'Range':[RANGE_BALLOT_FILENAME,vs.voting_range],'Borda':[RANK_BALLOT_FILENAME,
vs.voting_borda],'IRV':[RANK_BALLOT_FILENAME,vs.voting_irv]}
# constant of type dict that takes the votings system and return a list of
# the file associated with the file and the function it uses


if __name__ == '__main__':
    
    voting_system = prompt_from_list("Select a voting system or Q to quit:",\
    ['Approval','Range','Plurality','IRV','Borda','Q'])
    # prompts for voting system

    while voting_system != 'Q':
        # evaluates as long as user doesn't quit
        
        print('Running for {0}.'.format(voting_system))
        # prints the voting system that is currently running

        function = name_to_file_to_function[voting_system][1]
        # finds function that is to be used

        vote_results = read_file(open(
        name_to_file_to_function[voting_system][0]),voting_system)
        # finds dict of file        
        
        riding = prompt_for_riding(
'Which riding would you like to see results for?\
(Enter a number between 0 and 307, or all.):',len(vote_results))
        # prompts user for riding

        get_results(riding, vote_results)
        # evaluates and prints results based on riding and the file

        print('') # seperates results from prompt

        voting_system = prompt_from_list("Select a voting system or Q to quit:",\
    ['Approval','Range','Plurality','IRV','Borda','Q'])
        # asks user for next step


