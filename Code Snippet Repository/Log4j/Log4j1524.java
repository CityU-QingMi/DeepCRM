    public synchronized T[] removeAll() {
        final T[] array = makeArray(clazz, numElems);
        int index = 0;
        while (numElems > 0) {
            numElems--;
            array[index++] = ring[first];
            ring[first] = null;
            if (++first == ring.length) {
                first = 0;
            }
        }
        return array;
    }
