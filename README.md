# Lab 2 Report - Adia Romanescu (2A3)

## Homework:
For homework, the challenge was implementing my Greedy algorithm idea as clean as possible. My algorithm basically pairs depots with their first client (first in the path, as to connected in the chosen costs paths) by choosing the minimum cost and available client. Afterwards, in each iteration, for each path, I try to connect the last client to a new one by always choosing the minimum cost and available client.

# Lab 1 Report - Adia Romanescu (2A3)

## Homework:
For the homework assignment, I implemented a simple iteration over the [a,b] interval. I also developed a function to verify k-reducibility. The maximum number of iterations is set to 100, a somewhat arbitrary choice. However, it seems intuitive that either setting a maximum number of iterations or using a frequency vector would help in checking if a loop has been entered.

## Bonus:

As a bonus, I created a class for the Wheel Graph (WG). The WG is represented by an adjacency matrix, with cycles formed of (i, i+1, N) considered as a slice. Consequently, the WG has N-1 slices, all of which are adjacent. 

To print and calculate these cycles, I assigned each cycle a number of slices. Each cycle is composed of K adjacent cycles. I then set the beginning slice (B) and printed the list of nodes formed by the K slices (B, B+1, B+2, ..., B+K-1, applying modular arithmetic with %(N-1)).
