    private void ensureCapacity(int newSize) {

        if (newSize > limitPos) {
            if (!canChangeSize) {
                throw new ArrayStoreException("BitMap extend");
            }
        }

        if (newSize <= map.length * Integer.SIZE) {
            if (newSize > limitPos) {
                limitPos = newSize;
            }

            return;
        }

        int newMapLength = map.length;

        while (newSize > newMapLength * Integer.SIZE) {
            newMapLength *= 2;
        }

        int[] newmap = new int[newMapLength];

        System.arraycopy(map, 0, newmap, 0, map.length);

        map      = newmap;
        limitPos = newSize;
    }
