    protected int getFirstColumnIndex(RangeVariable subRange) {

        if (subRange == this) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < rangeArray.length; i++) {
            int index = rangeArray[i].getFirstColumnIndex(subRange);

            if (index == -1) {
                count += rangeArray[i].rangeTable.getColumnCount();
            } else {
                return count + index;
            }
        }

        return -1;
    }
