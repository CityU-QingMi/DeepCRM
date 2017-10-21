    public RangeIterator getRangeIterator(int position) {

        RangeIterator[] ranges = rangeIterators;

        if (stack != null) {
            for (int i = 0; i < stack.size(); i++) {
                Object o = stack.get(i);

                if (o instanceof RangeIterator[]) {
                    ranges = (RangeIterator[]) o;

                    break;
                }
            }
        }

        return ranges[position];
    }
