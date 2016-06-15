import sys

def sum_digits(num):
    s = 0
    while num:
        s += num % 10
        num = num / 10
    return s

def get_N(L, D, X):
    mn = 0
    mx = 0
    for i in range(L, D + 1):
        if X == sum_digits(i):
            if mn == 0:
               mn = i
            if i > mx:
                mx = i
    print str(mn)
    print str(mx)

L = int(sys.stdin.readline())
D = int(sys.stdin.readline())
X = int(sys.stdin.readline())

get_N(L, D, X)

