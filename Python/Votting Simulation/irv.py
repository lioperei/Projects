def read_file(file):
    ''' (ioTextWrapper) -> object

    Read over the file and return a dict of list of strings over each
    line in the file. Each key of dict represents a riding with the values
    corresponding to the votes in each riding.
    '''
    title = file.readline()
    current_line = file.readline()
    next_line = file.readline()
    
    current_riding = ''
    riding_vote_list = []
    riding_vote_tuple = ()
    file_dict = {}
    
    while current_line != '':
        if 'Riding' in current_line:
            current_riding = current_line.strip()
        else:
            irv_dict = {}
            if next_line.strip() == '':
                file_dict[current_riding] = irv_dict
                current_riding = ''
                irv_dict = {}
            else:
                if current_line.strip() != '':
                    line = tuple(current_line.split())
                    if line in irv_dict.keys():
                        irv_dict[line] += 1
                    else:
                        irv_dict[line] = 0
                    
        current_line = next_line
        next_line = file.readline()

    return file_dict
