    public RangeIterator getCheckIterator(RangeVariable rangeVariable) {

        int position = rangeVariable.rangePosition;

        if (position >= rangeIterators.length) {
            int size = (int) ArrayUtil.getBinaryNormalisedCeiling(position
                + 1);

            rangeIterators =
                (RangeIterator[]) ArrayUtil.resizeArray(rangeIterators, size);
        }

        rangeIterators[position] = checkIterator;

        return checkIterator;
    }
