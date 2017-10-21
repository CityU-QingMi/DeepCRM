    private int minInSet(final TreeSet<Integer> set) {
        int previous = 0;
        int min = Integer.MAX_VALUE;
        boolean first = true;
        for (final int value : set) {
            if (first) {
                previous = value;
                first = false;
                continue;
            } else {
                final int diff = value - previous;
                if (diff < min) {
                    min = diff;
                }
            }
        }
        return min;
    }
