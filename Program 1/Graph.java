/**************************************************************/
/* Jeffrey Ni
/* Login ID: 015253589
/* CS 3310, Spring 2023 
/* Programming Assignment 1
/* Book class: information about a single library book 
/**************************************************************/

import java.io.*;

import java.util.*;

//Class Graph definition

class Graph

{

// Number of vertices

private int V;

// Adjacency List

private LinkedList<Integer> adj[];

// Parameterized constructor

Graph(int v)

{

// Assign vertices

V = v;

// Creates list based on vertices

adj = new LinkedList[v];

// Loops till end of vertices and allocates memory

for(int i=0; i<v; ++i)

adj[i] = new LinkedList();

}// End of constructor

// Method to add an edge into the graph

void addEdge(int v,int w)

{

adj[v].add(w);

adj[w].add(v);

}// End of method

// Method that uses visited[] and parent to detect cycle in subgraph reachable from vertex ve

Boolean isCyclicHelper(int ve, Boolean visited[], int parent)

{

// Mark the current node as visited

visited[ve] = true;

Integer co;

// Recur for all the vertices adjacent to this vertex

Iterator<Integer> it = adj[ve].iterator();

// Checks for value availability

while (it.hasNext())

{

// Extracts data

co = it.next();

// If an adjacent is not visited, then recur for that adjacent

if (!visited[co])

{

// Calls the method to check cycle

if (isCyclicHelper(co, visited, ve))

return true;

}// End of if

// If an adjacent is visited and not parent of current vertex, then there is a cycle.

else if (co != parent)

return true;

}// End of while

return false;

}// End of method

// Method to return true if the graph contains a cycle, else false.

Boolean isCyclic()

{

// Mark all the vertices as not visited and not part of recursion stack

Boolean visited[] = new Boolean[V];

// Loops till end of vertices and set all the nodes visited to false

for (int c = 0; c < V; c++)

visited[c] = false;

// Call the recursive helper function to detect cycle in different Depth First Search trees

for (int c = 0; c < V; c++)

// Don't recur for u if already visited

if (!visited[c])

// Calls the method to check cycle

if (isCyclicHelper(c, visited, -1))

return true;

return false;

}// End of method

// Method to print connected components in an undirected graph

void connectedComponents()

{

// Mark all the vertices as not visited

Boolean visited[] = new Boolean[V];

// Loops till end of vertices and set all the nodes visited to false

for(int c = 0; c < V; c++)

visited[c] = false;

System.out.print("\n Connected components: ");

// Loops till end of vertices

for (int c = 0; c < V; c++)

{

// Checks if the current index position value of visited array is false

if (visited[c] == false)

{

// Print all reachable vertices from v

System.out.print("{");

DFSHelper(c, visited);

System.out.print("}");

}// End of if condition

}// End of for loop

}// End of method

// Method to display the connected components

void DFSHelper(int v, Boolean visited[])

{

int no;

// Mark the current node as visited and print it

visited[v] = true;

System.out.print((v+1) + " ");

// Recursion for all the vertices adjacent to this vertex

Iterator<Integer> i = adj[v].iterator();

// Loops till data is available in adj array

while (i.hasNext())

{

// Extracts data and stores it in n

no = i.next();

// Checks whether visited array no index position contains not true

if (!visited[no])

// Recursively call the function DFSHelper()

DFSHelper(no, visited);

}// End of while loop

}// End of method

// main method definition

public static void main(String ss[])throws FileNotFoundException

{

// To store vertices and edges

int no, e1, e2;

// Creating File instance to reference text file in Java

File text = new File("GraphEdges.txt");

// Creating Scanner instance to read File in Java

Scanner scnr = new Scanner(text);

// Reading number of edges for first graph

no = scnr.nextInt();

// Displays edges

System.out.println("Edges = " + no);

// Create a graph as extracted number of edges

Graph g1 = new Graph(no);

// Loops till end of vertices

for(int x = 0; x < no-1; x++)

{

// Extracts vertices

e1 = scnr.nextInt()-1;

e2 = scnr.nextInt()-1;

// Displays vertices

System.out.println("Vertiex1 = " + (e1+1) + "\t Vertiex2 = " + (e2+1));

// Adds vertices to graph one

g1.addEdge(e1, e2);

}// End of for loop

// Calls the method to display connected components

g1.connectedComponents();

// Checks for cycle

if (g1.isCyclic())

System.out.println("\n Graph contains cycle");

else

System.out.println("\n The graph is acyclic.");

// Reading number of edges for second graph

no = scnr.nextInt();

// Displays edges

System.out.println("Edges = " + no);

// Create a graph as extracted number of edges

Graph g2 = new Graph(no);

// Loops till end of vertices

for(int x = 0; x < no-1; x++)

{

// Extracts vertices

e1 = scnr.nextInt()-1;

e2 = scnr.nextInt()-1;

// Displays vertices

System.out.println("Vertiex1 = " + (e1+1) + "\t Vertiex2 = " + (e2+1));

// Adds vertices to graph two

g2.addEdge(e1, e2);

}// End of for loop

// Calls the method to display connected components

g2.connectedComponents();

// Checks for cycle

if (g2.isCyclic())

System.out.println("\n Graph contains cycle");

else

System.out.println("\n The graph is acyclic.");

}// End of main method

}// End of class