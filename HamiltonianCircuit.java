
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class HamiltonianCircuit
{
    private int a, counter; // counter counts path
    private int[] array; //the path
    private int[][] array2d; // the graph

  // find circuit
    public void findCirc(int[][] g)
    {
        a = g.length;
        array = new int[a];

        Arrays.fill(array, -1);
        array2d = g;
        try
        {
            array[0] = 0;
            counter = 1;
            solve(0);
            System.out.println("No solution");
        }
        catch (Exception e)
        {
            System.out.println("Solution");
            print();
        }
    }
    // find path
    public void solve(int vert) throws Exception
    {
        if (array2d[vert][0] == 1 && counter == a)
            throw new Exception("Solution exists");
        if (counter == a)
            return;

        for (int v = 0; v < a; v++)
        {
            if (array2d[vert][v] == 1 ) // if it is connected
            {
                array[counter++] = v; // add to path
                array2d[vert][v] = 0;
                array2d[v][vert] = 0;

                if (!selected(v))
                    solve(v);

                array2d[vert][v] = 1;
                array2d[v][vert] = 1;
                array[--counter] = -1;
            }
        }
    }
    public boolean selected(int v) // is path selected or not selected
    {
        for (int i = 0; i < counter - 1; i++)
            if (array[i] == v)
                return true;
        return false;
    }
    public void print() // print solution
    {
        System.out.print("\nPath : ");
        for (int i = 0; i <= a; i++)
            System.out.print(array[i % a] +" ");
        System.out.println();
    }
    public static void main (String[] args)
    {
        File file = new File("input1.txt");

        // number of vertices
        HamiltonianCircuit hc = new HamiltonianCircuit(); // instance of HamiltonianCircuit class




        try{
            Scanner scan = new Scanner(file);
            int V = scan.nextInt();
            // enter matrix
            int[][] graph = new int[V][V];
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    graph[i][j] = scan.nextInt();
            hc.findCirc(graph);

        }catch(FileNotFoundException ex){
            System.out.println("File not found");
        }


    }
}