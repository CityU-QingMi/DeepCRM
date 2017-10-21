    public static void adjustArray(int type, Object array, int usedElements,
                                   int index, int count) {

        if (index >= usedElements || count == 0) {
            return;
        }

        int newCount = usedElements + count;
        int source;
        int target;
        int size;

        if (count >= 0) {
            source = index;
            target = index + count;
            size   = usedElements - index;
        } else {
            source = index - count;
            target = index;
            size   = usedElements - index + count;
        }

        if (size > 0) {
            System.arraycopy(array, source, array, target, size);
        }

        if (count < 0) {
            clearArray(type, array, newCount, usedElements);
        }
    }
