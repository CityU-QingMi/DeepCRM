    private void resetCapacity() {

        if (elementCount < list.length) {
            return;
        }

        long[] newList = new long[list.length * 2];

        System.arraycopy(list, firstindex, newList, firstindex,
                         list.length - firstindex);

        if (endindex <= firstindex) {
            System.arraycopy(list, 0, newList, list.length, endindex);

            endindex = list.length + endindex;
        }

        list = newList;
    }
