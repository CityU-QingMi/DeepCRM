    public void toArray(Object[] array) {

        int tempCount = list.length - firstindex;

        if (tempCount > elementCount) {
            tempCount = elementCount;
        }

        System.arraycopy(list, firstindex, array, 0, tempCount);

        if (endindex <= firstindex) {
            System.arraycopy(list, 0, array, tempCount, endindex);

            endindex = list.length + endindex;
        }
    }
