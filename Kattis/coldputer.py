import sys

numTemps = int(sys.stdin.readline())

temps = sys.stdin.readline().split()
coldTemps = 0

for i in range(numTemps):
    if int(temps[i]) < 0:
        coldTemps += 1

print coldTemps