//package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Inversions {

    public static void main (String[] args) {


		List<Integer> list = new ArrayList<Integer>();


		try {
			File file = new File("numbers.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				//stringBuffer.append(line);
				//stringBuffer.append("\n");
				
				int num = Integer.parseInt(line);

				list.add(num);

			}


			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(list.size());


		long count = 0;

		for(int i=0; i< 100000; i++){
			for(int j=i+1; j< 100000; j++){
				if(list.get(j) < list.get(i)){
					count++;
				}
			}
		}

		System.out.println(count);





	}
}