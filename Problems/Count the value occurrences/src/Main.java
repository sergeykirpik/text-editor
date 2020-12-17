class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        // implement the method
        int count = 0;
        for (int el : list1) {
            if (el == elem) {
                count++;
            }
        }
        for (int el : list2) {
            if (el == elem) {
                count--;
            }
        }
        return count == 0;
    }
}