import sys

pairs = int(sys.stdin.readline())
operators = ["+", "*", "-"]

questions = {}
output = ""

def circularChecker(value):
    if(value >= 3):
        return 0
    else:
        return value


opRef = 0
for i in range(pairs):
    line = sys.stdin.readline().split()
    loop = False
    loopOpRef = opRef
    while(not loop):
        if opRef == 0:
            key = str(int(line[0]) + int(line[1]))
        if opRef == 1:
            key = str(int(line[0]) * int(line[1]))
        if opRef == 2:
            key = str(int(line[0]) - int(line[1]))
        if(key not in questions):
            output += line[0] + " " +  operators[opRef] + " " + line[1] + " = " + key + "\n"
            questions[key] = line
            break
        opRef = circularChecker(opRef + 1)
        if(loopOpRef == opRef):
            print "impossible"
            exit()
    '''if (j >= 3):
        print "impossible"
        exit()'''

#for answer in questions.keys():  print questions.get(answer) + answer
print output[:-1]
