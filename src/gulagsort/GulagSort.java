/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gulagsort;

import java.io.*;
import java.util.*;

/**
 *
 * @author brentbutkow
 */
public class GulagSort {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
//        double[] nums = new double[]{45, 9, 64, 325.1, 653.1, 7, 124, 542, 12443, 325,-1,-10,7,-5,328};

		makeNums(100000);
		ArrayList<Double> tArray = new ArrayList<>();
		Scanner temp = new Scanner(new File("nums.txt"));
		while (temp.hasNext()) {
			tArray.add(Double.parseDouble(temp.next()));

		}
		double[] nums = new double[tArray.size()];

		for (int i = 0; i < tArray.size(); i++) {
			nums[i] = tArray.get(i);
		}

		nums = gulag(nums);

		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}

//double[] t1 = new double[]{1,2,3,4};
//double[] t2 = new double[]{5,6,7,8};
//
//double[][] fin = new double[][]{t1,t2};
//
//        System.out.println(fin[0][1]);
//        int numRepeats = 10;
//        int numNums = 10000;//100 000 is the max for the gulag sort, cause of recursion limits
//        System.out.println("Done with " + numNums + " random numbers");
//
//        long[] gulagTime = new long[numRepeats];
//        long[] sortTime = new long[numRepeats];
//        long[] parallelTime = new long[numRepeats];
//        long GulagTotal = 0;
//        long sortTotal = 0;
//        long parallelTotal = 0;
//        for (int z = 0; z < numRepeats; z++) {
//            // <editor-fold defaultstate="collapsed" desc="making and sorting">
//            long startTime;
//            long endTime;
////making the random numbers
//            startTime = System.nanoTime();
//
//            makeNums(numNums);
//
//            endTime = System.nanoTime();
//
////                System.out.println((endTime-startTime)+" - time for making nums");
////making origional aand duplicate arrays
//            ArrayList<Double> tArray = new ArrayList<>();
//            Scanner temp = new Scanner(new File("nums.txt"));
//            while (temp.hasNext()) {
//                tArray.add(Double.parseDouble(temp.next()));
//
//            }
//            double[] nums = new double[tArray.size()];
//
//            for (int i = 0; i < tArray.size(); i++) {
//                nums[i] = tArray.get(i);
//            }
//            double[] toTest1 = new double[tArray.size()];
//            double[] toTest2 = new double[tArray.size()];
//            System.arraycopy(nums, 0, toTest1, 0, nums.length);
//            System.arraycopy(nums, 0, toTest2, 0, nums.length);
//
//            startTime = System.nanoTime();
////gulag sort
//            nums = gulag(nums);
//
//            endTime = System.nanoTime();
//            gulagTime[z] = (endTime - startTime);
////                System.out.println(gulagTime[z]+" - time for gulag sort");
//            startTime = System.nanoTime();
////normal sort
//            Arrays.sort(toTest1);
//            endTime = System.nanoTime();
//            sortTime[z] = (endTime - startTime);
////                System.out.println(sortTime[z]+" - time for normal sort");
//            startTime = System.nanoTime();
////parallel sort
//            Arrays.parallelSort(toTest2);
//
//            endTime = System.nanoTime();
//            parallelTime[z] = (endTime - startTime);
////                System.out.println(parallelTime[z]+" - time for parallel sort");
//
//if(z==numRepeats-1){
//               for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
//System.out.println("\n\n");}
//            // </editor-fold>
//        }
//        System.out.println("\n\n");
//        for (int i = 0; i < numRepeats; i++) {
//            GulagTotal += gulagTime[i];
//            sortTotal += sortTime[i];
//            parallelTotal += parallelTime[i];
//        }
//        GulagTotal /= numRepeats;
//        sortTotal /= numRepeats;
//        parallelTotal /= numRepeats;
//
//        System.out.println("Averaged Times:\nGulag:  \t" + GulagTotal + "\nNormal:  \t" + sortTotal + "\nParallel:\t" + parallelTotal);
	}
//to optimise, try to join the sorted arrays before the bottom (Somehow) - join top and second level when the gulag is third level
//public static double[] gulag2(double[] nums) {
//
//    boolean done=false;
//        double[] sorted = new double[nums.length];
//do{
////        double[] sorted = new double[nums.length];
//        double[] notSorted = new double[nums.length];
//        int cS = 1, cNS = 0;//num of elements
//        sorted[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] >= sorted[cS - 1]) {
//                sorted[cS] = nums[i];
//                cS++;
//            } else {
//                notSorted[cNS] = nums[i];
//                cNS++;
//            }
//        }
//
//}while (!done);
//
//
//return sorted;
//}

	public static double[] gulag2(double[] nums) {//small to big

		double[][] temp = gullaghelp(nums);

		double[] sorted = temp[0];
		double[] notSorted = temp[1];

		double[] full = Arrays.copyOf(sorted, (int) sorted[sorted.length - 1]);
		double[] sort2 = Arrays.copyOf(notSorted, (int) notSorted[notSorted.length - 1]);

		boolean done = false;
		while (!done) {

			if (sort2.length == 0) {
				done = true;

			} else {

				temp = gullaghelp(sort2);

				sorted = temp[0];
				notSorted = temp[1];

				double[] sort1 = Arrays.copyOf(sorted, (int) sorted[sorted.length - 1]);//sorted part
				sort2 = Arrays.copyOf(notSorted, (int) notSorted[notSorted.length - 1]);//unsorted

			}

		}

		return nums;
	}

	public static double[][] gullaghelp(double[] nums) {

		double[] sorted = new double[nums.length + 1];
		double[] notSorted = new double[nums.length + 1];
		int cS = 1, cNS = 0;//num of elements
		sorted[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] >= sorted[cS - 1]) {
				sorted[cS] = nums[i];
				cS++;
			} else {
				notSorted[cNS] = nums[i];
				cNS++;
			}
		}
		sorted[nums.length] = cS;
		notSorted[nums.length] = cNS;

		return new double[][]{sorted, notSorted};//first is which array, seconf is which position

	}

	public static double[] gulag(double[] nums) {//small to big - proper one
		double[] sorted = new double[nums.length];
		double[] notSorted = new double[nums.length];
		int cS = 1, cNS = 0;//num of elements
		sorted[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] >= sorted[cS - 1]) {
				sorted[cS] = nums[i];
				cS++;
			} else {
				notSorted[cNS] = nums[i];
				cNS++;
			}
		}
		double[] sort1 = Arrays.copyOf(sorted, cS);
		double[] sort2 = Arrays.copyOf(notSorted, cNS);

		if (cNS == 0) {//start going back up
			return sort1;
		} else {
			sort2 = gulag(sort2);//it is now sorted

			int iS = 0, iNS = 0;//index sorted and not

			for (int i = 0; i < nums.length; i++) {
				if (iS == cS) {
					nums[i] = sort2[iNS];
					iNS++;
				} else if (iNS == cNS || sort1[iS] <= sort2[iNS]) {
					nums[i] = sort1[iS];
					iS++;
				} else {

					nums[i] = sort2[iNS];
					iNS++;
				}

			}

		}

		return nums;
	}

	public static void makeNums(int numNums) throws IOException {

		PrintWriter print = new PrintWriter(new FileWriter("nums.txt"), false);

		for (int i = 0; i < numNums; i++) {
			print.append(((double) Math.round((Math.random() * 10000 - 5000) * 1000)) / 1000 + "\n");//num from -5000 to +5000 with 3 dec
//            System.out.println(((double) Math.round((Math.random()*5000-2500)*1000))/1000+"");
		}
		print.close();

	}

}
