    public static int deDuplicate(Object[] array, int start, int limit,
                                  Comparator comparator) {

        int baseIndex    = start;
        int currentIndex = start + 1;

        if (array.length == 0) {
            return 0;
        }

        for (; currentIndex < limit; currentIndex++) {
            int compare = comparator.compare(array[baseIndex],
                                             array[currentIndex]);

            if (compare == 0) {
                continue;
            }

            baseIndex++;

            array[baseIndex] = array[currentIndex];
        }

        return baseIndex + 1;
    }
