    public static int find(Object[] array, Object object) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == object) {

                // handles both nulls
                return i;
            }

            if (object != null && object.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }
