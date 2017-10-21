    public static void copyAdjustArray(Object source, Object dest,
                                       Object addition, int colindex,
                                       int adjust) {

        int length = Array.getLength(source);

        if (colindex < 0) {
            System.arraycopy(source, 0, dest, 0, length);

            return;
        }

        System.arraycopy(source, 0, dest, 0, colindex);

        if (adjust == 0) {
            int endcount = length - colindex - 1;

            Array.set(dest, colindex, addition);

            if (endcount > 0) {
                System.arraycopy(source, colindex + 1, dest, colindex + 1,
                                 endcount);
            }
        } else if (adjust < 0) {
            int endcount = length - colindex - 1;

            if (endcount > 0) {
                System.arraycopy(source, colindex + 1, dest, colindex,
                                 endcount);
            }
        } else {
            int endcount = length - colindex;

            Array.set(dest, colindex, addition);

            if (endcount > 0) {
                System.arraycopy(source, colindex, dest, colindex + 1,
                                 endcount);
            }
        }
    }
