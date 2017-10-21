    public boolean add(long value) {

        resetCapacity();

        if (endindex == list.length) {
            endindex = 0;
        }

        list[endindex] = value;

        elementCount++;
        endindex++;

        return true;
    }
