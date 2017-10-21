    public static int countTrueElements(boolean[] arra) {

        int count = 0;

        for (int i = 0; i < arra.length; i++) {
            if (arra[i]) {
                count++;
            }
        }

        return count;
    }
