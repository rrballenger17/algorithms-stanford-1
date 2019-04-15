import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.*;

import java.util.*;

class MinCut
{
    public static Map<Integer, List<Integer> > nodes = new HashMap< Integer, List<Integer> >();



    public static List<Integer> removeEach(List<Integer> list, int val, int newVal){

        int size = list.size();

        list.removeAll(Collections.singleton(val));

        while(list.size() < size){
            list.add(newVal);
        }

        return list;
    }


    public static int minCutAlgorithm(){

        try {
            File file = new File("kargerMinCut.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {


                String[] parts = line.split("\\s");

                int size = parts.length;

                List<Integer> set = new ArrayList<Integer>();

                for(int i=1; i<size; i++){

                    set.add( Integer.parseInt(parts[i]) );

                }


                nodes.put( Integer.parseInt(parts[0]), set );
                //stringBuffer.append(line);
                //stringBuffer.append("\n");
            



            }
            //fileReader.close();
            //System.out.println("Contents of file:");
            //System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*List<Integer> conn = nodes.get(200);

        for(int j: conn){
            System.out.println(j);
        }*/

        // ArrayList<Integer> list = new ArrayList<Integer>();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.removeAll(Collections.singleton(2));

        //             System.out.println("start");

        // for(int i: list){
        //     System.out.println(i);
        // }
        //     System.out.println("stop");


        Random r = new Random();

        while(nodes.size() > 2){

            //System.out.println("here " + nodes.size());

            int n1 = r.nextInt(nodes.size()) + 1;

            int n2 = r.nextInt(nodes.size()) + 1;

            while(n1 == n2){
                n2 = r.nextInt(nodes.size()) + 1;
            }


            int newNum = n1 < n2 ? n1 : n2;
            int expired = n1 < n2 ? n2 : n1;

            int largest = nodes.size();

            boolean useLargest = true;
            if(largest == expired){
                useLargest = false;
            }


            for(int node: nodes.keySet()){
                List<Integer> edges = nodes.get(node);

                if(edges.contains(n1) || edges.contains(n2)){

                    edges = removeEach(edges, n1, newNum);
                   // edges.remove(n1);
                   // edges.remove(n2);
                    edges = removeEach(edges, n2, newNum);


                    //edges.add(newNum);
                }

                if(useLargest){
                    if(edges.contains(largest)){
                        edges = removeEach(edges, largest, expired);
                        //edges.remove(largest);
                        //edges.add(expired);
                    }
                }

                nodes.put(node, edges);
            }

            List<Integer> edgesN1 = nodes.get(n1);
            nodes.remove(n1);
            List<Integer> edgesN2 = nodes.get(n2);
            nodes.remove(n2);

            if(useLargest){
                List<Integer> edgesLargest = nodes.get(largest);
                nodes.remove(largest);
                nodes.put(expired, edgesLargest);
            }

            edgesN1.addAll(edgesN2);

            //FIX
            //edgesN1 = removeEach(edgesN1, newNum);
                    edgesN1.removeAll(Collections.singleton(newNum));

            //edgesN1.removeAll(Collections.singleton(newNum));
            //edgesN1.remove(newNum);

            nodes.put(newNum, edgesN1);

            

        }

        //System.out.println(nodes.get(1).size() + " " + nodes.get(2).size());

        return nodes.get(1).size();

    }



    public static void main(String[] args) {

        int low = 10000;


        while(true){
            int ret = minCutAlgorithm();

            if(ret < low){
                low = ret;

                System.out.println(low);
            }

        }
    }

}   