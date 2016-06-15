import sys

def getLeastUsed(points):
    p1 = 0
    p2 = 0
    if points[0] == points[2]:
        p1 += 1
    if points[1] == points[2]:
        p2 += 1
    if p1 < p2:
        return points[0]
    if p2 < p1:
        return points[1]
    else:
        return points[2]



p1 = sys.stdin.readline().split()
p2 = sys.stdin.readline().split()
p3 = sys.stdin.readline().split()

print(getLeastUsed([p1[0], p2[0], p3[0]]) + " " + getLeastUsed([p1[1], p2[1], p3[1]]))

