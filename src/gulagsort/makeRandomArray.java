/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gulagsort;

import java.io.*;

/**
 *
 * @author brentbutkow
 */
public class makeRandomArray {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		int numNums = 10000;
		PrintWriter print = new PrintWriter(new FileWriter("nums.txt"), false);

		for (int i = 0; i < numNums; i++) {
			print.append(((double) Math.round((Math.random() * 10000 - 5000) * 1000)) / 1000 + "\n");
//            System.out.println(((double) Math.round((Math.random()*5000-2500)*1000))/1000+"");
		}
		print.close();
	}

}
