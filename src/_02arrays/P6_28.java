package _02arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelmeyer on 10/5/16.
 * This program process the merge step of a merge sort algorithm, merging two sorted lists together into one.
 */
public class P6_28 {

    /**
     * Main driver for the sort. This creates two sorted lists and passes them to the merge method
     * @param args unused for this program
     */
    public static void main(String[] args) {
        List<Integer> nSortedInts1 = new ArrayList<>();
        List<Integer> nSortedInts2 = new ArrayList<>();
        nSortedInts1.add(1);
        nSortedInts1.add(4);
        nSortedInts1.add(9);
        nSortedInts1.add(16);
        nSortedInts2.add(4);
        nSortedInts2.add(7);
        nSortedInts2.add(9);
        nSortedInts2.add(9);
        nSortedInts2.add(11);
        List<Integer> nSortedInts = mergeSorted(nSortedInts1, nSortedInts2);
        System.out.println(nSortedInts);
    }

    /**
     * Merges two sorted lists together to form one sorted list
     * @param nSortedInts1 first sorted list
     * @param nSortedInts2 second sorted list
     * @return combined sorted list
     */
    private static List<Integer> mergeSorted(List<Integer> nSortedInts1, List<Integer> nSortedInts2) {
        List<Integer> nSortedInts = new ArrayList<>();
        //using sentinels to mark the end of array
        nSortedInts1.add(Integer.MAX_VALUE);
        nSortedInts2.add(Integer.MAX_VALUE);
        int j = 0;
        int k = 0;
        for (int i = 0; i < (nSortedInts1.size() + nSortedInts2.size()); i++) {
            if (nSortedInts1.get(j) <= nSortedInts2.get(k) &&
                    nSortedInts1.get(j) != Integer.MAX_VALUE) {
                nSortedInts.add(nSortedInts1.get(j));
                j++;
            } else if (nSortedInts2.get(k) != Integer.MAX_VALUE) {
                nSortedInts.add(nSortedInts2.get(k));
                k++;
            }
        }
        return nSortedInts;
    }



}
