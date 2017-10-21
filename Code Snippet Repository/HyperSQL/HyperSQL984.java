    public static boolean haveEqualSets(int[] arra, int[] arrb, int count) {

        if (ArrayUtil.haveEqualArrays(arra, arrb, count)) {
            return true;
        }

        if (count > arra.length || count > arrb.length) {
            return false;
        }

        if (count == 1) {
            return arra[0] == arrb[0];
        }

        int[] tempa = (int[]) resizeArray(arra, count);
        int[] tempb = (int[]) resizeArray(arrb, count);

        sortArray(tempa);
        sortArray(tempb);

        for (int j = 0; j < count; j++) {
            if (tempa[j] != tempb[j]) {
                return false;
            }
        }

        return true;
    }
