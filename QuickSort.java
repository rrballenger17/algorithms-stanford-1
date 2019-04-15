//package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class QuickSort {

	public	List<Integer> list = new ArrayList<Integer>();




	public void swap(int one, int two){

			System.out.println(one + " " + two);



				int temp = list.get(one);
				list.set(one, list.get(two));
				list.set(two, temp);	

				System.out.println("done");	
	}


	public int get(int index){
		return list.get(index);
	}

	public long count = 0;





	public int getMedian(int x, int y, int z){

		//if(one + 1 == two){
	//		return one;
	//	}

		int one = get(x);
		int two = get(y);
		int three = get(z);


		int smallest = one;

		if(two < smallest){
			smallest = two;
		}

		if(three < smallest){
			smallest = three;
		}



		int largest = one;

		if(two > largest){
			largest = two;
		}

		if(three > largest){
			largest = three;
		}


		if( one != smallest && one != largest){
			return x;
		}

		if(two != smallest && two != largest){
			return y;
		}

		if(three != smallest && three != largest){
			return z;
		}

		System.out.println("Median error "+ one + " " + two + " " + three);

		System.exit(1);

		return -1;

	}


	public void quickSort(int start, int length){


		if(length <= 1) return;

	//	if(start >= 10000) return;



		//System.out.println(start + " " + length);
		//System.out.println(last);
		 if(length > 2){

		 	int last = start + length - 1;

		 	int medianIndex = -1;


		 	if(length % 2 == 0){
		 		int x = length / 2 - 1;
		 		medianIndex = start + x;
		 	}else{
		 		medianIndex = start + length / 2;
		 	}


		 	int med = getMedian(start, last, medianIndex);

		 	swap(start, med);

		 }

		// 	if(length% 2 == 0){
		// 		medianIndex--;
		// 	}


		// 	int medianPiv = getMedian(start, last, medianIndex);


		//swap(start, start + length - 1);
		// }


		count += length-1;

		int wall = start + 1;

		int ptr = start + 1;

		int piv = start;


		//int 


		while(true){


			if(get(wall) < get(piv)){


				if(wall == start + length - 1){
					wall ++;
				 break;
				}



				wall++;
				ptr = wall;
				//ptr = wall;
				continue;
			}

			if(ptr == start + length){
				break;
			}


			if(get(ptr) > get(piv)){
				ptr++;
			}else{
				swap(ptr, wall);
			}
		}

		swap(wall - 1, piv);



		int set = wall - 1;


		int newLen = length - (set - start) - 1;
		quickSort(set  +1, newLen);

		newLen = set - start;
		quickSort(start, newLen);



		//System.out.println(list.toString());

	}






    public static void main (String[] args) {

    	QuickSort q = new QuickSort();


		try {
			File file = new File("quick.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				//stringBuffer.append(line);
				//stringBuffer.append("\n");
				
				int num = Integer.parseInt(line);

				q.list.add(num);

			}


			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println(list.size());


		//q.list = new ArrayList<Integer>(Arrays.asList(4,16,9,25, 21,3, 14,11, 27,24,1, 15,6, 18,5, 26,22,17, 8,28, 12,2,19, 7,29, 23, 20,30,13,10));


		q.quickSort(0, 10000);


		// check 
		for(int i = 0; i< 10000; i++){
			if(q.get(i) != i + 1){
				System.exit(1);
			}
		}


		System.out.println("CORRECT");

		System.out.println("checks " + q.count);




	}
}