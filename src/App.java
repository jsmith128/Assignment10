import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        List<Integer> a;
        // attempt to generate list based on cmd args, otherwise make one that's 10 long
        if (args.length == 1) {
            a = genArray(Integer.parseInt(args[0]));
        } else {
            a = genArray(10);
        }
        
        System.out.println("Original List:");
        printList(a);
        System.out.println("Bubble Sorted List");
        printList(bubbleSort(a));
        System.out.println("Merge Sorted List");
        printList(mergeSort(a));
        
        
    }


    // Returns a random integer array n-length long, of value 0 .. n*2
    static List<Integer> genArray(int n) {
        List<Integer> arr = new ArrayList<Integer>(n);
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr.add(r.nextInt(n*2));
        }
        return arr;
    }


    // Main driver function for merge sort
    static <T extends Number & Comparable<T>> List<T> mergeSort(List<T> arr) {
        int L = arr.size();
        if (L == 1) 
            return arr;


        List<T> arr1 = arr.subList(0, L/2);
        List<T> arr2 = arr.subList(L/2, L);

        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);

        return merge(arr1, arr2);
    }

    // Takes two arrays and iterates through them, incrementally adding the 
    //   smallest number between the two to a new array. Returns this new array. 
    static <T extends Number & Comparable<T>> List<T> merge(List<T> arr1, List<T> arr2) {
        int L1 = arr1.size();
        int L2 = arr2.size();


        List<T> arr3 = new ArrayList<T>(L1+L2);

        int i1 = 0;
        int i2 = 0;

        // iterate through all possible elements of arr3
        for (int i = 0; i < L1+L2; i++) {

            

            if (i2 > L2 - 1) {  // Use the rest of arr1 because arr2 is "consumed"
                arr3.add(arr1.get(i1));
                i1++;
                continue;
            } else if (i1 > L1 - 1) {  // Use the rest of arr1 because arr2 is "consumed"
                arr3.add(arr2.get(i2));
                i2++;
                continue;
            }

            T a1 = arr1.get(i1);
            T a2 = arr2.get(i2);
            
            // (assuming arr1 & arr2 are sorted) 
            //   if first element of arr1 is smaller, send that to arr3
            //   and increment appropriate counter. Same true for arr2<arr1.
            if (a1.compareTo(a2) <= 0) {
                arr3.add(a1);
                i1++;
            } else {
                arr3.add(a2);
                i2++;
            }
        }

        return arr3;
    }

    static <T extends Number & Comparable<T>> List<T> bubbleSort(List<T> array)
    {
        List<T> arr = array;
        boolean swapped = false;
        // run this. then keep looping as long as we keep swapping numbers
        do {
            swapped = false;
            for (int i = 0; i < arr.size() -1; i++) {
                if (arr.get(i).compareTo(arr.get(i+1)) > 0) {
                    // swap i and i+1
                    T x = arr.get(i);
                    arr.set(i, arr.get(i+1));
                    arr.set(i+1, x);
        
                    swapped = true;
                }
            }
        } while (swapped);

        return arr;
    }

    // Print a number list
    static <T extends Number> void printList(List<T> list) {
        for (T i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
