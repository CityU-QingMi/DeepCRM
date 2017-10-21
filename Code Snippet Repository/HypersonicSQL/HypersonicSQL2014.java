    public void add(int i, Object o) throws IndexOutOfBoundsException {

        if (i == elementCount) {
            add(o);

            return;
        }

        resetCapacity();

        int index = getInternalIndex(i);

        if (index < endindex && endindex < list.length) {
            System.arraycopy(list, index, list, index + 1, endindex - index);

            endindex++;
        } else {
            System.arraycopy(list, firstindex, list, firstindex - 1,
                             index - firstindex);

            firstindex--;
            index--;
        }

        list[index] = o;

        elementCount++;
    }
