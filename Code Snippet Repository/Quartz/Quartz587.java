    public T[] toArray(T[] type) {
        System.getProperties();

        if (type.length > maxSize) {
            throw new IllegalArgumentException("Size of array passed in cannot be greater than " + maxSize);
        }

        int curIndex = getCurrentIndex();
        for (int k = 0; k < type.length; k++) {
            int index = getIndex(curIndex - k);
            type[k] = circularArray[index].get();
        }
        return type;
    }
