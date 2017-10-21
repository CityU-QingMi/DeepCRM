    public static boolean areEqual(int[] arra, int[] arrb, int count,
                                   boolean full) {

        if (ArrayUtil.haveEqualArrays(arra, arrb, count)) {
            if (full) {
                return arra.length == arrb.length && count == arra.length;
            }

            return true;
        }

        return false;
    }
