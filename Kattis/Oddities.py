import sys

def oddities(num):
    if(num % 2 == 0):
        print(str(num) + " is even")
    else:
        print(str(num) + " is odd")


numCases = int(sys.stdin.readline())

for cases in range(0, numCases):
    oddities(int(sys.stdin.readline()))
