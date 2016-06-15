import sys


def swap(arr, p1, p2):
    temp = arr[p1]
    arr[p1] = arr[p2]
    arr[p2] = temp
    return arr


def record_move(cups, move):
    if move == 'A':
        cups = swap(cups, 0, 1)
    if move == 'B':
        cups = swap(cups, 1, 2)
    if move == 'C':
        cups = swap(cups, 0, 2)
    return cups


def find_ball(arr):
    for i in range(len(cups)):
        if cups[i] == 1:
            return i + 1


cups = [1,0,0]

moves = sys.stdin.readline()

for move in range(len(moves)):
    record_move(cups, moves[move])

print find_ball(cups)