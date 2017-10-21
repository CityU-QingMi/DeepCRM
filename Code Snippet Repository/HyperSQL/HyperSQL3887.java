    public void setRangeIterator(RangeIterator iterator) {

        int position = iterator.getRangePosition();

        if (position >= rangeIterators.length) {
            int size = (int) ArrayUtil.getBinaryNormalisedCeiling(position
                + 1);

            rangeIterators =
                (RangeIterator[]) ArrayUtil.resizeArray(rangeIterators, size);
        }

        rangeIterators[position] = iterator;
    }
