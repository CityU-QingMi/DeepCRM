    public boolean add(Object o) {

        resetCapacity();

        if (endindex == list.length) {
            endindex = 0;
        }

        list[endindex] = o;

        elementCount++;
        endindex++;

        return true;
    }
