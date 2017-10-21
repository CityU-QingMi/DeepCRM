    public int getSmallestIndex(OrderedHashSet other) {

        int min = -1;

        for (int i = 0, size = other.size(); i < size; i++) {
            int index = getIndex(other.get(i));

            if (index != -1) {
                if (min == -1 || index < min) {
                    min = index;
                }
            }
        }

        return min;
    }
