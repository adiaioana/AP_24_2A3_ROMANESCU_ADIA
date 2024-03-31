# Lab 4 Report - Adia Romanescu (2A3)
## Homework:
For the problem class, I simply added a list of persons and a "helper" class. Using the  helper class, I manage the map of locations. For the greedy algorithm, I simply sorted the lists by adress and matched them if their adress was the same.
## Bonus: TBA

# Lab 3 Report - Adia Romanescu (2A3)

## Homework:
For homework, in order to pair each day from the travel plan with an attraction from the trip, I used the maximum bipartite matching algorithm. The first set is composed of the days and the second set of attractions, for each pair of nodes (day, attr), there is an edge if on that day attraction attr is possible to visit. In order to maximise the number of visited attractions, I run a BPM.
## Bonus:
First Greedy runs a dynamic programming algorithm (using configurations), it calculates for a configuration containing some types of attractions that end at a certain time, if it's possible basically. Time complexity is around O((M^2)*(2^T)), where M is the number of attraction and T is the number of types. 
Second Greedy simply runs a Greedy on the sorted array (with sorting criteria using the end of the visiting intervals).

# Lab 2 Report - Adia Romanescu (2A3)

## Homework:
For homework, the challenge was implementing my Greedy algorithm idea as clean as possible. My algorithm basically pairs depots with their first client (first in the path, as to connected in the chosen costs paths) by choosing the minimum cost and available client. Afterwards, in each iteration, for each path, I try to connect the last client to a new one by always choosing the minimum cost and available client.
## Bonus:
First version(BonusGraph) would be in BonusGraph, but this version considers that the graph is fully in a grid mode (which can be fixed by adding 'phantom' nodes for the nodes within the grid graph that are not present, the cost between phantom nodes and other nodes would be 0, so it would work, but pretty terrible to implement something like this).
Second version(BonusWorking) would be in BonusWorking. In this version, the input gained is the cost matrixes and by a DFS, the graph is positioned on the grid.
For version 3 (Bonus3), I simply considered taking the matrix as an input and runned a fill from each client to determine the minimum cost from that client to each depot.
# Lab 1 Report - Adia Romanescu (2A3)

## Homework:
For the homework assignment, I implemented a simple iteration over the [a,b] interval. I also developed a function to verify k-reducibility. The maximum number of iterations is set to 100, a somewhat arbitrary choice. However, it seems intuitive that either setting a maximum number of iterations or using a frequency vector would help in checking if a loop has been entered.

## Bonus:

As a bonus, I created a class for the Wheel Graph (WG). The WG is represented by an adjacency matrix, with cycles formed of (i, i+1, N) considered as a slice. Consequently, the WG has N-1 slices, all of which are adjacent. 

To print and calculate these cycles, I assigned each cycle a number of slices. Each cycle is composed of K adjacent cycles. I then set the beginning slice (B) and printed the list of nodes formed by the K slices (B, B+1, B+2, ..., B+K-1, applying modular arithmetic with %(N-1)).
