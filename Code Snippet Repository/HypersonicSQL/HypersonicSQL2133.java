    public int getLargestIndex(OrderedHashSet other) {

        int max = -1;

        for (int i = 0, size = other.size(); i < size; i++) {
            int index = getIndex(other.get(i));

            if (index > max) {
                max = index;
            }
        }

        return max;
    }
