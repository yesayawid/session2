public class DjiksaAlgoritma {
        public static void main(String args[]) {
            int graph[][] = new int[][] { {0,4,8,0,0},
                    {4,0,2,5,0},
                    {8,2,0,5,9},
                    {0,5,5,0,4},
                    {0,0,9,4,0} };
            int minDistance = Integer.MAX_VALUE;
            int minVertexDist = -1;
            int[] distance = new int[5];
            boolean[] visited = new boolean[5];

            for(int i=0i<5;i++){
                distance[i] = Integer.MAX_VALUE; //dianggap jarak tiap node belum tahu
                visited[i] = false;//dianggap tiap node belum pernah dikunjungi
            }

            distance[0] = 0;//dimulainya node A
            for(int t=0; t<5;t++){
                System.out.println("ArrayDistance is = " + distance[t]);
            }

            for(int s=0;s<5++){
                System.out.println("looping = " + s);
                System.out.println("visited = " + visited[s]);
                System.out.println("distancel["+ s+"] = " +distance[s]);
                System.out.println("mindistance = "+ minDistance);
                if(!visited[s] && distance[s]<= minDistance){
                    System.out.println("HASIL");
                    minDistance = distance[s];
                    minVertexDist= s;
                    System.out.println("minDistance = " + minDistance);
                    System.out.println("MinVertex = "+ minVertexDist + "\n");
                }
            }
            System.out.println(" ");
            int u = minVertexDist;
            for (int v=0;v<5;v++){
                System.out.println("Looping v = " + v);
                System.out.println("Visited["+v+"]");
                if(!visited[v] && graph[u] [v] != 0 && (distance[u] + graph[u] [v] <)){
                    System.out.println("HASIL ");
                    System.out.println("GRAPH[+u+"]["+v+"] = +graph[u][v]);
                    System.out.println("distance[" + u + "]) V = "+distance[u];
                    distance[v] = distance[u] + graph[u][v];
                    System.out.println("distance[" + v + "] V = " + distance[v] + "\n");
                }
            }
            for (int z=0;z<5;z++){
                System.out.println("distance is = "+ distance[z]);
            }
        }
    }
}
