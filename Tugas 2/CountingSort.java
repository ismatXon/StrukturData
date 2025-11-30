public class CountingSort {
    public static void countingSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }

        int[] count = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        int index = 0;
        // Untuk descending, iterasi dari max ke 0
        for (int i = max; i >= 0; i--) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {12, 5, 7, 20, 3, 15, 8, 10};

        System.out.println("Data sebelum diurutkan:");
        for (int d : data) System.out.print(d + " ");

        countingSort(data);

        System.out.println("\nData setelah diurutkan (Descending):");
        for (int d : data) System.out.print(d + " ");
    }
}
