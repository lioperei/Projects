import sys

correctPieces = [1, 1, 2, 2, 2, 8]
pieces = sys.stdin.readline().split()

for i in range(len(pieces)):
    pieces[i] = correctPieces[i] - int(pieces[i])

print str(pieces)[1:-1].replace(",", "")